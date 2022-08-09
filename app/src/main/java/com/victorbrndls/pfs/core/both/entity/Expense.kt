package com.victorbrndls.pfs.core.both.entity

import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.income.entity.Income

data class Both(
    val incomes: List<Income>,
    val expenses: List<Expense>,
)