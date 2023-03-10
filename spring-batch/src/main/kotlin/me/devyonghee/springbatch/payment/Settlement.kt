package me.devyonghee.springbatch.payment

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class Settlement(
    private val amount: Int,
    private val memberId: Int,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
) {
    @CreatedDate
    private lateinit var createdAt: LocalDateTime
}