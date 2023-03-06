package me.devyonghee.flywayorm.user.persistence

import me.devyonghee.flywayorm.user.domain.User
import me.devyonghee.flywayorm.user.domain.UserRepository
import me.devyonghee.flywayorm.user.persistence.jooq.UserJooqRepository
import org.springframework.stereotype.Repository

@Repository
class UserDao(
    private val userJooqRepository: UserJooqRepository
) : UserRepository {

    override fun save(user: User): String {
        return userJooqRepository.save(user)
    }

    override fun findByUsername(username: String): User? {
        return userJooqRepository.findByUsername(username)
    }
}