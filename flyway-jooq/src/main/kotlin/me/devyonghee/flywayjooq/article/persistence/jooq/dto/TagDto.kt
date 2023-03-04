package me.devyonghee.flywayjooq.article.persistence.jooq.dto

import me.devyonghee.flywayjooq.article.domain.Tag

data class TagDto(
    private val name: String,
) {
    fun toDomain(): Tag = Tag(name)
}

fun List<TagDto>.toDomain(): List<Tag> = this.map { it.toDomain() }