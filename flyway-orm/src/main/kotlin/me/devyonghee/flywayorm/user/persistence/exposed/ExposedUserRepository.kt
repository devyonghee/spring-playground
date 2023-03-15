package me.devyonghee.flywayorm.user.persistence.exposed

import me.devyonghee.flywayorm.user.domain.User
import me.devyonghee.flywayorm.user.persistence.exposed.model.UserEntity
import me.devyonghee.flywayorm.user.persistence.exposed.model.UserTable
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class ExposedUserRepository {

    fun save(user: User): Long {
        return UserEntity.new {
            username = user.username
            email = user.email.value
            password = user.password
            bio = user.bio
            image = user.image?.toString()
        }.id.value
    }

    fun findByUsername(username: String): User? {
        return transaction() {
            UserEntity.find {
                UserTable.username eq username
            }.firstOrNull()?.toDomain()
        }
    }
}