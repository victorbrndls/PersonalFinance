package com.victorbrndls.pfs.core.income.dto

import java.math.BigDecimal
import java.util.*

data class EditIncomeData(
    val id: Long?,
    val description: String,
    val date: Date,
    val amount: BigDecimal
)