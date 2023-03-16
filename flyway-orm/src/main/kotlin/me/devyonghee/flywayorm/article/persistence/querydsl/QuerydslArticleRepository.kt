package me.devyonghee.flywayorm.article.persistence.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component

@Component
class QuerydslArticleRepository(
    private val queryFactory: JPAQueryFactory
) {
}