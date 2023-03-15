package me.devyonghee.flywayorm.user.persistence

import me.devyonghee.flywayorm.user.domain.User
import me.devyonghee.flywayorm.user.domain.UserRepository
import me.devyonghee.flywayorm.user.persistence.exposed.ExposedUserRepository
import me.devyonghee.flywayorm.user.persistence.jooq.UserJooqRepository
import org.springframework.stereotype.Repository

@Repository
class UserDao(
    private val userJooqRepository: UserJooqRepository,
    private val exposedUserRepository: ExposedUserRepository,
) : UserRepository {

    override fun save(user: User): Long {
        return exposedUserRepository.save(user)
    }

    override fun findByUsername(username: String): User? {
        return exposedUserRepository.findByUsername(username)
    }
}