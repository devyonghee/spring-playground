package me.devyonghee.flywayorm.article.persistence.jooq.dto

import me.devyonghee.flywayorm.article.domain.Tag

data class TagDto(
    private val id: String,
    private val name: String,
) {
    fun toDomain(): Tag = Tag(name)
}

fun List<TagDto>.toDomain(): List<Tag> = this.map { it.toDomain() }