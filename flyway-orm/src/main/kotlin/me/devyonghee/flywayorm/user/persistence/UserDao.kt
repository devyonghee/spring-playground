package me.devyonghee.flywayorm.user.persistence

import me.devyonghee.flywayorm.user.domain.User
import me.devyonghee.flywayorm.user.domain.UserRepository
import me.devyonghee.flywayorm.user.persistence.exposed.ExposedUserRepository
import me.devyonghee.flywayorm.user.persistence.jooq.UserJooqRepository
import me.devyonghee.flywayorm.user.persistence.jpa.JpaUserRepository
import me.devyonghee.flywayorm.user.persistence.jpa.model.UserEntity
import me.devyonghee.flywayorm.user.persistence.querydsl.QuerydslUserRepository
import org.springframework.stereotype.Repository

@Repository
class UserDao(
    private val userJooqRepository: UserJooqRepository,
    private val exposedUserRepository: ExposedUserRepository,
    private val jpaUserRepository: JpaUserRepository,
    private val querydslUserRepository: QuerydslUserRepository
) : UserRepository {

    override fun save(user: User): Long {
        return jpaUserRepository.save(UserEntity(user)).id
    }

    override fun findByUsername(username: String): User? {
        return querydslUserRepository.findByUsername(username)
    }
}