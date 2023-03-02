package me.devyonghee.flywayjooq.persistence.jooq.dto

import me.devyonghee.flywayjooq.domain.Tag

data class TagDto(
    private val name: String,
) {
    val domain: Tag
        get() = Tag(name)
}

val List<TagDto>.domain: List<Tag>
    get() = this.map { it.domain }