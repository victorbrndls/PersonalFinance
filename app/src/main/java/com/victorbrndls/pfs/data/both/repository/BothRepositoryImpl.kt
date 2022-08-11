package com.victorbrndls.pfs.data.both.repository

import com.victorbrndls.pfs.core.both.entity.Both
import com.victorbrndls.pfs.core.both.repository.BothRepository
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.expense.usecase.ObserveExpensesUseCase
import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.core.income.usecase.ObserveIncomesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class BothRepositoryImpl @Inject constructor(
    private val observeIncomesUseCase: ObserveIncomesUseCase,
    private val observeExpensesUseCase: ObserveExpensesUseCase
) : BothRepository {

    override suspend fun observe(): Flow<List<Both>> {
        return combine(
            observeIncomesUseCase.observe(),
            observeExpensesUseCase.observe()
        ) { incomes, expenses ->
            (incomes + expenses).mapNotNull {
                when (it) {
                    is Income -> Both.Income(it)
                    is Expense -> Both.Expense(it)
                    else -> null
                }
            }.sortedBy {
                when (it) {
                    is Both.Income -> it.income.date.time
                    is Both.Expense -> it.expense.date.time
                }
            }
        }
    }

}
