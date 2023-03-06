package me.devyonghee.flywayorm.user.application

import me.devyonghee.flywayorm.user.domain.User
import me.devyonghee.flywayorm.user.domain.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun register(user: User): String {
        return userRepository.save(user)
    }

    fun findUser(userName: String): User {
        return userRepository.findByUsername(userName)
            ?: throw IllegalArgumentException("user(username: '${userName}') not found")
    }


}