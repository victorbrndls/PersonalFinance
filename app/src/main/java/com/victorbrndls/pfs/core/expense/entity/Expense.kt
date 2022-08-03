package com.victorbrndls.pfs.core.expense.entity

import java.math.BigDecimal
import java.util.*

data class Expense(
    val id: Long,
    val description: String,
    val date: Date,
    val amount: BigDecimal
)