package me.devyonghee.kotlinjooq.controller.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import me.devyonghee.kotlinjooq.domain.Article
import me.devyonghee.kotlinjooq.domain.Tag

data class ArticleRequest(
    @NotBlank
    val title: String,
    @NotNull
    val description: String,
    @NotNull
    val body: String,
    @NotNull
    val tagList: List<String>
) {
    val domain: Article
        get() = Article(title, description, body, tagList.domain)
}

val List<String>.domain: List<Tag>
    get() = this.map { Tag(it) }