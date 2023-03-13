package me.devyonghee.springbatch.payment.domain

import org.springframework.data.jpa.repository.JpaRepository

interface SettlementRepository : JpaRepository<Settlement, Long>