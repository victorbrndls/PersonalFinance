package com.victorbrndls.pfs.data.expense.repository

import com.victorbrndls.pfs.core.expense.dto.EditExpenseData
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.expense.repository.ExpenseRepository
import com.victorbrndls.pfs.infrastructure.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.random.Random

class ExpenseRepositoryImpl @Inject constructor() : ExpenseRepository {

    private val expenses = MutableStateFlow(emptyList<Expense>())

    override suspend fun getAll(): List<Expense> {
        return expenses.value
    }

    override suspend fun observe(): Flow<List<Expense>> {
        return expenses
    }

    override suspend fun save(expense: EditExpenseData) {
        expenses.value += Expense(
            Random.nextLong(),
            expense.description,
            expense.category,
            expense.date,
            expense.amount
        ).also {
            Logger.d("Saved expense | $it")
        }
    }
}