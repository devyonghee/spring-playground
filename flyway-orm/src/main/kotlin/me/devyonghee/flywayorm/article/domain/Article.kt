package me.devyonghee.flywayorm.article.domain

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toKotlinLocalDateTime
import java.util.*

class Article(
    val title: String,
    val description: String,
    val body: String,
    val tags: List<Tag>,
    val slug: String = title.lowercase(Locale.getDefault())
        .replace(" ", "-"),
    val favoritesCount: Int = 0,
    val createdAt: LocalDateTime = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
    val updatedAt: LocalDateTime = java.time.LocalDateTime.now().toKotlinLocalDateTime(),
) {
    init {
        require(title.isNotBlank()) { "title must not be blank" }
    }
}
