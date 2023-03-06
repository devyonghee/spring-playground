package me.devyonghee.flywayorm.user.domain

interface UserRepository {

    fun save(user: User): String

    fun findByUsername(username: String): User?
}