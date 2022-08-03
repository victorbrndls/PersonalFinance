package com.victorbrndls.pfs.core.income.entity

import java.math.BigDecimal
import java.util.*

data class Income(
    val id: Long,
    val description: String,
    val date: Date,
    val amount: BigDecimal
)