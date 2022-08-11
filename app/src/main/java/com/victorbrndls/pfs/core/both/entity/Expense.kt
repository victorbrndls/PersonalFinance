package com.victorbrndls.pfs.core.both.entity

import com.victorbrndls.pfs.core.expense.entity.Expense as ExpenseEntity
import com.victorbrndls.pfs.core.income.entity.Income as IncomeEntity

sealed interface Both {
    data class Income(val income: IncomeEntity) : Both
    data class Expense(val expense: ExpenseEntity) : Both
}