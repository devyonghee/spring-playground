package me.devyonghee.flywayorm.user.controller.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import me.devyonghee.flywayorm.user.domain.Email
import me.devyonghee.flywayorm.user.domain.User

data class UserRequest(
    @NotEmpty
    val username: String,
    @NotEmpty
    val password: String,
    @NotEmpty
    @Pattern(regexp = Email.REGEX_PATTERN)
    val email: String,
) {
    fun toDomain(): User = User(username, password, Email(email))
}
