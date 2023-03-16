package me.devyonghee.flywayorm.article.persistence.jpa

import me.devyonghee.flywayorm.article.persistence.jpa.model.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaArticleRepository : JpaRepository<ArticleEntity, String> {

}