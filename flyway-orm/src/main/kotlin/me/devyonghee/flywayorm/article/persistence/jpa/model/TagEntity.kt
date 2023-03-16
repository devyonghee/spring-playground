package me.devyonghee.flywayorm.article.persistence.jpa.model

import jakarta.persistence.*
import me.devyonghee.flywayorm.article.domain.Tag
import java.util.*

@Entity
@Table(name = "tag")
class TagEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,
    val name: String,
    @ManyToOne(optional = false)
    var article: ArticleEntity? = null,
) {

    fun toDomain(): Tag {
        return Tag(name)
    }
}

fun List<TagEntity>.toDomain(): List<Tag> {
    return map { it.toDomain() }
}

