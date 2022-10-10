package com.victorbrndls.pfs.core.expense.usecase

import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.expense.repository.ExpenseRepository
import com.victorbrndls.pfs.infrastructure.date.DateRange
import com.victorbrndls.pfs.infrastructure.date.last12Months
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ObserveExpensesUseCase {
    suspend fun observe(range: DateRange = last12Months()): Flow<List<Expense>>
}

class ObserveExpensesUseCaseImpl @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : ObserveExpensesUseCase {
    override suspend fun observe(range: DateRange): Flow<List<Expense>> {
        return expenseRepository.observe(range)
    }
}