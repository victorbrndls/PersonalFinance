package com.victorbrndls.pfs.core.summary.entity

import java.math.BigDecimal
import java.util.*

data class Summary(
    val date: Date,
    val income: BigDecimal,
    val expenses: BigDecimal,
    val endingBalance: BigDecimal
) {
    val netSavings = income - expenses
}