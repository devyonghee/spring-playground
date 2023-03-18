package me.devyonghee.flywayorm.user.persistence.jpa.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import me.devyonghee.flywayorm.user.domain.Email
import me.devyonghee.flywayorm.user.domain.User
import java.net.URI

@Entity(name = "users")
class UserEntity(
    var username: String,
    var email: String,
    var password: String,
    var bio: String,
    var image: String,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
) {
    constructor(user: User) : this(
        user.username,
        user.emailString,
        user.password,
        user.bio,
        user.imageString
    )

    fun toDomain(): User {
        return User(username, password, Email(email), bio, URI(image), id)
    }
}