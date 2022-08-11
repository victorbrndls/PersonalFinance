package com.victorbrndls.pfs.core.expense.usecase

import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.expense.repository.ExpenseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ObserveExpensesUseCase {
    suspend fun observe(): Flow<List<Expense>>
}

class ObserveExpensesUseCaseImpl @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ObserveExpensesUseCase {
    override suspend fun observe(): Flow<List<Expense>> {
        return expenseRepository.observe()
    }
}