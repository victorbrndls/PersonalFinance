package com.victorbrndls.pfs.core.expense.repository

import com.victorbrndls.pfs.core.expense.dto.EditExpenseData
import com.victorbrndls.pfs.core.expense.entity.Expense

interface ExpenseRepository {
    suspend fun getAll(): List<Expense>
    suspend fun save(expense: EditExpenseData)
}