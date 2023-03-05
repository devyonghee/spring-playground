package me.devyonghee.flywayjooq.user.persistence

import me.devyonghee.flywayjooq.user.domain.User
import me.devyonghee.flywayjooq.user.domain.UserRepository
import me.devyonghee.flywayjooq.user.persistence.jooq.UserJooqRepository
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