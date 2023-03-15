package me.devyonghee.flywayorm.user.persistence.jooq

import me.devyonghee.flywayorm.user.domain.Email
import me.devyonghee.flywayorm.user.domain.User
import me.devyonghee.kotlinjooq.generated.tables.JUsers.USERS
import org.jooq.DSLContext
import org.springframework.stereotype.Component
import java.net.URI

@Component
class UserJooqRepository(
    private val dslContext: DSLContext,
) {
    fun save(user: User): Long {
        return dslContext.insertInto(USERS)
            .values(
                user.username,
                user.email.value,
                user.password,
                user.bio,
                user.image?.toString(),
            )
            .returningResult(USERS.ID)
            .fetchOneInto(Long::class.java)!!
    }

    fun findByUsername(username: String): User? {
        return dslContext.select(
            USERS.ID,
            USERS.USERNAME,
            USERS.PASSWORD,
            USERS.EMAIL,
            USERS.BIO,
            USERS.IMAGE
        ).from(USERS)
            .where(USERS.USERNAME.eq(username))
            .fetchSingleInto(UserDto::class.java)?.toDomain()
    }
}

class UserDto(
    private val id: Int,
    private val username: String,
    private val email: String,
    private val password: String,
    private val bio: String,
    private val image: String?,
) {
    fun toDomain(): User {
        return User(username, password, Email(email), bio, image?.let { URI(it) })
    }
}
