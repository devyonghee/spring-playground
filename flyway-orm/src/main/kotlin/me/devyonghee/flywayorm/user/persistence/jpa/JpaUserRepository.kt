package me.devyonghee.flywayorm.user.persistence.jpa

import me.devyonghee.flywayorm.user.persistence.jpa.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserRepository : JpaRepository<UserEntity, Long> {

    fun findByUsername(username: String): UserEntity?
}