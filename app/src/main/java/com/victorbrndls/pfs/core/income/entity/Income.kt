package com.victorbrndls.pfs.core.income.entity

import com.victorbrndls.pfs.core.category.entity.Category
import java.math.BigDecimal
import java.util.*

data class Income(
    val id: Long,
    val description: String,
    val category: Category,
    val date: Date,
    val amount: BigDecimal
)