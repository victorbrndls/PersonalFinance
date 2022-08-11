package com.victorbrndls.pfs.core.both.entity

import com.victorbrndls.pfs.core.category.entity.Category
import java.util.*
import com.victorbrndls.pfs.core.expense.entity.Expense as ExpenseEntity
import com.victorbrndls.pfs.core.income.entity.Income as IncomeEntity

sealed interface Both {
    data class Income(val income: IncomeEntity) : Both
    data class Expense(val expense: ExpenseEntity) : Both

    val category: Category
        get() = when (this) {
            is Expense -> expense.category
            is Income -> income.category
        }
    val date: Date
        get() = when (this) {
            is Expense -> expense.date
            is Income -> income.date
        }
}