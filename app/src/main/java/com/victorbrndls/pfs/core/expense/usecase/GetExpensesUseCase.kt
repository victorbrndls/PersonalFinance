package com.victorbrndls.pfs.core.expense.usecase

import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.expense.repository.ExpenseRepository
import javax.inject.Inject

interface GetExpensesUseCase {
    suspend fun getAll(): List<Expense>
}

class GetExpensesUseCaseImpl @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : GetExpensesUseCase {
    override suspend fun getAll(): List<Expense> {
        return expenseRepository.getAll()
    }
}