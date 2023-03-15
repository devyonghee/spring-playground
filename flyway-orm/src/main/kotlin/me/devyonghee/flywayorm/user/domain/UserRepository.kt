package me.devyonghee.flywayorm.user.domain

interface UserRepository {

    fun save(user: User): Long

    fun findByUsername(username: String): User?
}