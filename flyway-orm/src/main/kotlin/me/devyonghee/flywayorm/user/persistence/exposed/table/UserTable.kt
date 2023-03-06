package me.devyonghee.flywayorm.user.persistence.exposed.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Column

object UserTable : LongIdTable("users") {

    val username: Column<String> = varchar("username", 255).uniqueIndex()
    val email: Column<String> = varchar("email", 255).uniqueIndex()
    val password: Column<String> = varchar("password", 255)
    val bio: Column<String> = varchar("bio", 255)
    val image: Column<String?> = varchar("image", 255).nullable()
    val following: Column<Boolean> = bool("following")
}