package me.devyonghee.flywayorm.article.persistence.jooq

import me.devyonghee.flywayorm.article.domain.Article
import me.devyonghee.flywayorm.article.persistence.jooq.dto.ArticleDto
import me.devyonghee.flywayorm.article.persistence.jooq.dto.TagDto
import me.devyonghee.kotlinjooq.generated.Tables.ARTICLE
import me.devyonghee.kotlinjooq.generated.Tables.TAG
import org.jooq.DSLContext
import org.jooq.Field
import org.jooq.Records
import org.jooq.impl.DSL
import org.springframework.stereotype.Component

@Component
class JooqArticleRepository(
    private val dslContext: DSLContext
) {
    fun save(article: Article): String {
        val savedSlug = dslContext.insertInto(ARTICLE)
            .values(
                article.slug,
                article.title,
                article.description,
                article.body,
                article.createdAt,
                article.updatedAt
            )
            .returningResult(ARTICLE.SLUG)
            .fetchOne(ARTICLE.SLUG)!!
        dslContext.insertInto(TAG)
            .columns(TAG.ID, TAG.ARTICLE_SLUG, TAG.NAME)
            .valuesOfRecords(article.tags.map {
                dslContext.newRecord(TAG).values(DSL.uuid().toString(), article.slug, it.name)
            })
            .execute()
        return savedSlug
    }

    fun findBySlug(targetSlug: String): Article? {
        return dslContext.select(
            ARTICLE.SLUG, ARTICLE.TITLE, ARTICLE.DESCRIPTION, ARTICLE.BODY, tagsMultiset(targetSlug),
        ).from(ARTICLE)
            .where(ARTICLE.SLUG.eq(targetSlug))
            .fetchSingle(Records.mapping { slug, title, description, body, tags ->
                ArticleDto(slug, title, description, body, tags).toDomain()
            })
    }

    private fun tagsMultiset(slug: String): Field<List<TagDto>> {
        return DSL.multiset(
            DSL.select(TAG.ID, TAG.NAME)
                .from(TAG)
                .where(TAG.ARTICLE_SLUG.eq(slug))
        ).convertFrom { result ->
            result.map { TagDto(it.get(TAG.ID), it.get(TAG.NAME)) }
        }
    }
}