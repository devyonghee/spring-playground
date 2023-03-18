package me.devyonghee.flywayorm.article.persistence.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import me.devyonghee.flywayorm.article.domain.Article
import me.devyonghee.flywayorm.article.persistence.jpa.model.QArticleEntity.articleEntity
import me.devyonghee.flywayorm.article.persistence.jpa.model.QTagEntity.tagEntity
import org.springframework.stereotype.Component

@Component
class QuerydslArticleRepository(
    private val queryFactory: JPAQueryFactory,
) {
    fun findBySlug(slug: String): Article? {
        return queryFactory.selectFrom(articleEntity)
            .where(articleEntity.slug.eq(slug))
            .leftJoin(tagEntity).on(tagEntity.article.slug.eq(slug))
            .fetchJoin()
            .fetchOne()
            ?.let { article ->
                Article(
                    slug = article.slug,
                    title = article.title,
                    body = article.body,
                    description = article.description,
                    favoritesCount = article.favoritesCount,
                    tags = article.tags.map { it.toDomain() },
                    createdAt = article.createdAt,
                    updatedAt = article.updatedAt,
                )
            }
    }
}