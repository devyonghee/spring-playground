package me.devyonghee.kotlinjooq.domain

import java.util.*

class Article(
    val title: String,
    val description: String,
    val body: String,
    val tags: List<Tag>,
    val slug: String = title.lowercase(Locale.getDefault())
        .replace(" ", "-"),
) {
    init {
        require(title.isNotBlank()) { "title must not be blank" }
    }
}
