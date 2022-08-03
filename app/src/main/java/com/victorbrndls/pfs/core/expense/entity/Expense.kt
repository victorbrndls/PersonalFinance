package com.victorbrndls.pfs.core.expense.entity

import java.util.*

data class Expense(
    val id: Long,
    val description: String,
    val date: Date,
    val amount: Long
)