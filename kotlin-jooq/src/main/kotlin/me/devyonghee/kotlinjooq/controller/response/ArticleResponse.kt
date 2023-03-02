package me.devyonghee.kotlinjooq.controller.response

import me.devyonghee.kotlinjooq.domain.Article
import me.devyonghee.kotlinjooq.domain.Tag

class ArticleResponse(
    article: Article
) {
    val title: String = article.title
    val description: String = article.description
    val body: String = article.body
    val tags: List<String> = article.tags.strings
}

val List<Tag>.strings: List<String>
    get() = this.map { it.name }
