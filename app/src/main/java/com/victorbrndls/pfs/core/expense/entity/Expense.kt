package com.victorbrndls.pfs.core.expense.entity

import com.victorbrndls.pfs.core.category.entity.Category
import java.math.BigDecimal
import java.util.*

data class Expense(
    val id: Long,
    val description: String,
    val category: Category,
    val date: Date,
    val amount: BigDecimal
)