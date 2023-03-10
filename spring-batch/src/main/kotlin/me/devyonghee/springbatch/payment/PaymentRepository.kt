package me.devyonghee.springbatch.payment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PaymentRepository : JpaRepository<Payment, Long> {

    @Query("select p from Payment p where p.status = :status")
    fun findByStatus(status: Payment.Status): List<Payment>
}