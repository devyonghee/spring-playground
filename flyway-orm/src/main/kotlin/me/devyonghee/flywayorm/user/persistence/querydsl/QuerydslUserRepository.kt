package me.devyonghee.flywayorm.user.persistence.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import me.devyonghee.flywayorm.user.domain.Email
import me.devyonghee.flywayorm.user.domain.User
import me.devyonghee.flywayorm.user.persistence.jpa.model.QUserEntity.userEntity
import org.springframework.stereotype.Component
import java.net.URI

@Component
class QuerydslUserRepository(
    private val queryFactory: JPAQueryFactory
) {
    fun findByUsername(username: String): User? {
        return queryFactory.selectFrom(userEntity)
            .where(userEntity.username.eq(username))
            .fetchOne()
            ?.let { user ->
                User(
                    email = Email(user.email),
                    username = user.username,
                    password = user.password,
                    bio = user.bio,
                    image = URI(user.image)
                )
            }
    }
}