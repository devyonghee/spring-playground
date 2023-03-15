package me.devyonghee.flywayorm.user.persistence.exposed.model

import me.devyonghee.flywayorm.user.domain.Email
import me.devyonghee.flywayorm.user.domain.User
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.net.URI

class UserEntity(id: EntityID<Long>) : Entity<Long>(id) {
    companion object : EntityClass<Long, UserEntity>(UserTable)

    var username by UserTable.username
    var email by UserTable.email
    var password by UserTable.password
    var bio by UserTable.bio
    var image by UserTable.image

    fun toDomain(): User {
        return User(
            username,
            password,
            Email(email),
            bio,
            image?.let { URI(it) },
            id.value
        )
    }
}