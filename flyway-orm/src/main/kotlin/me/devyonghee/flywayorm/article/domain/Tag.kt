package me.devyonghee.flywayorm.article.domain

import java.util.*

data class Tag(
    val name: String,
    val id: UUID = UUID.randomUUID(),
)
