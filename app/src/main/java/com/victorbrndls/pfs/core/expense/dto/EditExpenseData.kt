package com.victorbrndls.pfs.core.expense.dto

import com.victorbrndls.pfs.core.category.entity.Category
import java.math.BigDecimal
import java.util.*

data class EditExpenseData(
    val id: Long?,
    val description: String,
    val category: Category,
    val date: Date,
    val amount: BigDecimal
)