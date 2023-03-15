package me.devyonghee.flywayorm.user.application

import me.devyonghee.flywayorm.user.domain.User
import me.devyonghee.flywayorm.user.domain.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun register(user: User): Long {
        return userRepository.save(user)
    }

    @Transactional(readOnly = true)
    fun findUser(userName: String): User {
        return userRepository.findByUsername(userName)
            ?: throw IllegalArgumentException("user(username: '${userName}') not found")
    }


}