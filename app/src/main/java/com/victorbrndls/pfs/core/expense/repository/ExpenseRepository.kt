package com.victorbrndls.pfs.core.expense.repository

import com.victorbrndls.pfs.core.expense.dto.EditExpenseData
import com.victorbrndls.pfs.core.expense.entity.Expense
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    suspend fun getAll(): List<Expense>
    suspend fun observe(): Flow<List<Expense>>

    suspend fun save(expense: EditExpenseData)
}