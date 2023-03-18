package me.devyonghee.flywayorm.article.persistence.jpa.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import kotlinx.datetime.toKotlinLocalDateTime
import me.devyonghee.flywayorm.article.domain.Article
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity(name = "article")
class ArticleEntity(
    @Id
    val slug: String,
    val title: String,
    val description: String,
    val body: String,
    val favoritesCount: Int,
    @OneToMany(mappedBy = "article", orphanRemoval = true, cascade = [CascadeType.ALL])
    val tags: List<TagEntity> = listOf(),
    @CreatedDate
    val createdAt: LocalDateTime,
    @LastModifiedDate
    val updatedAt: LocalDateTime,
) {
    constructor(article: Article) : this(
        article.slug,
        article.title,
        article.description,
        article.body,
        article.favoritesCount,
        article.tags.map { TagEntity(it.id, it.name) },
        article.createdAt,
        article.updatedAt,
    )

    init {
        tags.forEach { it.article = this }
    }

    fun toDomain(): Article {
        return Article(
            title,
            description,
            body,
            tags.toDomain(),
            slug,
            favoritesCount,
            createdAt,
            updatedAt,
        )
    }
}