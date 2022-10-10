package com.victorbrndls.pfs.core.expense.repository

import com.victorbrndls.pfs.core.expense.dto.EditExpenseData
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.infrastructure.date.DateRange
import com.victorbrndls.pfs.infrastructure.date.last12Months
import kotlinx.coroutines.flow.Flow

interface ExpenseRepository {
    suspend fun getAll(range: DateRange = last12Months()): List<Expense>
    suspend fun observe(range: DateRange = last12Months()): Flow<List<Expense>>

    suspend fun save(expense: EditExpenseData)
}