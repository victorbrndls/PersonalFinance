package com.victorbrndls.pfs.data.expense.repository

import com.victorbrndls.pfs.core.expense.dto.EditExpenseData
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.expense.repository.ExpenseRepository
import javax.inject.Inject
import kotlin.random.Random

class ExpenseRepositoryImpl @Inject constructor() : ExpenseRepository {
    private var expenses = mutableListOf<Expense>()

    override suspend fun getAll(): List<Expense> {
        return expenses
    }

    override suspend fun save(expense: EditExpenseData) {
        expenses += Expense(
            Random.nextLong(),
            expense.description,
            expense.date,
            expense.amount
        )
    }
}