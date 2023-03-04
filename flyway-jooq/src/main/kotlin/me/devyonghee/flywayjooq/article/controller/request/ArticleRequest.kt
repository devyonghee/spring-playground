package me.devyonghee.flywayjooq.article.controller.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import me.devyonghee.flywayjooq.article.domain.Article
import me.devyonghee.flywayjooq.article.domain.Tag


data class ArticleRequest(
    @NotBlank
    val title: String,
    @NotNull
    val description: String,
    @NotNull
    val body: String,
    @NotNull
    val tags: List<String>,
) {
    fun toDomain(): Article = Article(title, description, body, tags.toDomain())
}

fun List<String>.toDomain(): List<Tag> = this.map { Tag(it) }