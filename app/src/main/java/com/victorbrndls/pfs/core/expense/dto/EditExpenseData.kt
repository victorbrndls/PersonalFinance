package com.victorbrndls.pfs.core.expense.dto

import java.math.BigDecimal
import java.util.*

data class EditExpenseData(
    val id: Long?,
    val description: String,
    val date: Date,
    val amount: BigDecimal
)