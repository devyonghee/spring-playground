package me.devyonghee.flywayjooq.article.persistence.jooq

import me.devyonghee.flywayjooq.article.domain.Article
import me.devyonghee.flywayjooq.article.persistence.jooq.dto.ArticleDto
import me.devyonghee.flywayjooq.article.persistence.jooq.dto.TagDto
import me.devyonghee.kotlinjooq.generated.Tables.TAG
import me.devyonghee.kotlinjooq.generated.tables.Article.ARTICLE
import org.jooq.DSLContext
import org.springframework.stereotype.Component

@Component
class JooqArticleRepository(
    private val dslContext: DSLContext
) {
    fun save(article: Article): String {
        return dslContext.insertInto(ARTICLE)
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
    }

    fun findBySlug(slug: String): Article? {
        return dslContext.select(
            ARTICLE.SLUG,
            ARTICLE.TITLE,
            ARTICLE.DESCRIPTION,
            ARTICLE.BODY,
        )
            .from(ARTICLE)
            .where(ARTICLE.SLUG.eq(slug))
            .fetchSingle {
                ArticleDto(
                    it.get(ARTICLE.SLUG),
                    it.get(ARTICLE.TITLE),
                    it.get(ARTICLE.BODY),
                    it.get(ARTICLE.DESCRIPTION),
                    findTagsBySlug(slug)
                ).toDomain()
            }
    }

    private fun findTagsBySlug(slug: String): MutableList<TagDto> =
        dslContext.select(TAG.NAME)
            .from(TAG)
            .where(TAG.ARTICLE_SLUG.eq(slug))
            .fetch()
            .map { TagDto(it.get(TAG.NAME)) }
}