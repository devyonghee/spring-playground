package me.devyonghee.flywayorm.user.controller.response

import me.devyonghee.flywayorm.user.domain.User

class UserResponse(
    user: User
) {
    val email: String = user.email.toString()
    val token: String = user.username
    val username: String = user.username
    val bio: String = user.bio
    val image: String = user.image.toString()
}
