package me.devyonghee.springbatch.payment

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
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