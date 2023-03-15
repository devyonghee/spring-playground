package me.devyonghee.flywayorm.user.domain

import java.net.URI

class User(
    val username: String,
    val password: String,
    val email: Email,
    val bio: String = "",
    val image: URI? = null,
    val id: Long = 0,
) {
    init {
        require(username.isNotBlank()) { "username must not be blank" }
        require(password.isNotBlank()) { "password must not be blank" }
    }
}