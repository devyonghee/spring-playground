package me.devyonghee.springbatch.payment

import org.apache.ibatis.annotations.Mapper

@Mapper
interface PaymentGroupRepository {

    fun findPaymentGroupByStatus(status: Payment.Status): List<PaymentGroup>
}