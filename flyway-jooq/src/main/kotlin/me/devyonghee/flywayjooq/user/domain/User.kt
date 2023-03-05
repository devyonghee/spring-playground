package me.devyonghee.flywayjooq.user.domain

import java.net.URI

class User(
    val username: String,
    val password: String,
    val email: Email,
    val bio: String = "",
    val image: URI? = null,
    val following: Boolean = false,
) {
    init {
        require(username.isNotBlank()) { "username must not be blank" }
        require(password.isNotBlank()) { "password must not be blank" }
    }
}