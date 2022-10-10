package com.victorbrndls.pfs.core.expense.usecase

import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.expense.repository.ExpenseRepository
import com.victorbrndls.pfs.infrastructure.date.DateRange
import com.victorbrndls.pfs.infrastructure.date.last12Months
import javax.inject.Inject

interface GetExpensesUseCase {
    suspend fun getAll(
        range: DateRange = last12Months()
    ): List<Expense>
}

class GetExpensesUseCaseImpl @Inject constructor(
    private val expenseRepository: ExpenseRepository
) : GetExpensesUseCase {
    override suspend fun getAll(range: DateRange): List<Expense> {
        return expenseRepository.getAll(range)
    }
}