package me.devyonghee.flywayorm.article.persistence.exposed.model

import me.devyonghee.flywayorm.article.domain.Tag
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class TagEntity(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, TagEntity>(TagTable)

    var name by TagTable.name
    var article by ArticleEntity referencedOn TagTable.article

    fun toDomain(): Tag {
        return Tag(name)
    }
}