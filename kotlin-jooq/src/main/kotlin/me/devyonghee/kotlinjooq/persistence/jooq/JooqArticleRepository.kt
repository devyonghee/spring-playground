package me.devyonghee.kotlinjooq.persistence.jooq

import me.devyonghee.kotlinjooq.domain.Article
import me.devyonghee.kotlinjooq.generated.tables.Article.ARTICLE
import me.devyonghee.kotlinjooq.generated.tables.Tag.TAG
import me.devyonghee.kotlinjooq.persistence.jooq.dto.ArticleDto
import org.jooq.DSLContext
import org.jooq.impl.DSL.multiset
import org.jooq.impl.DSL.select
import org.springframework.stereotype.Component

@Component
class JooqArticleRepository(
    private val dslContext: DSLContext
) {
    fun save(article: Article): String {
        return dslContext.insertInto(ARTICLE)
            .columns(ARTICLE.SLUG, ARTICLE.TITLE, ARTICLE.DESCRIPTION, ARTICLE.BODY)
            .values(article.slug, article.title, article.description, article.body)
            .returningResult()
            .fetchOne(ARTICLE.SLUG)!!
    }

    fun findById(slug: String): Article? {
        return dslContext.select(
            ARTICLE.SLUG,
            ARTICLE.TITLE,
            ARTICLE.DESCRIPTION,
            ARTICLE.BODY,
            multiset(
                select(TAG.NAME)
                    .from(TAG)
                    .where(ARTICLE.SLUG.eq(TAG.ARTICLE_SLUG))
            ).`as`("tags"),
        )
            .from(ARTICLE)
            .where(ARTICLE.SLUG.eq(slug))
            .fetchOneInto(ArticleDto::class.java)?.domain
    }
}