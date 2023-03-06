package me.devyonghee.flywayorm.article.persistence.exposed.table

import me.devyonghee.flywayorm.article.domain.Article
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class TagEntity(id: EntityID<UUID>) : Entity<UUID>(id) {
    companion object : EntityClass<UUID, TagEntity>(TagTable)

    var name by TagTable.name
    var article by ArticleEntity referencedOn TagTable.article
}