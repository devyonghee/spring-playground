package me.devyonghee.springbatch.payment.domain

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import java.time.LocalDateTime

@Mapper
interface PaymentGroupRepository {

    fun findPaymentGroupByStatus(
        @Param("fromDateTime") fromDateTime: LocalDateTime,
        @Param("toDateTime") toDateTime: LocalDateTime,
        ): List<PaymentGroup>
}