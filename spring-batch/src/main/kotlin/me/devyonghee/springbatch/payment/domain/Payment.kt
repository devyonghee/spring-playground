package me.devyonghee.springbatch.payment.domain

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class Payment(
    val amount: Long,
    @Enumerated(EnumType.STRING)
    val status: Status,

    val memberId: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {

    @CreatedDate
    private lateinit var createdAt: LocalDateTime;

    @LastModifiedDate
    private lateinit var updatedAt: LocalDateTime;

    enum class Status {
        READY, SUCCESS, FAIL
    }
}