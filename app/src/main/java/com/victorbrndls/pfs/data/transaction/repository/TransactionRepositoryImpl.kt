package com.victorbrndls.pfs.data.transaction.repository

import com.victorbrndls.pfs.core.transaction.entity.Transaction
import com.victorbrndls.pfs.core.transaction.repository.TransactionRepository
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.expense.usecase.ObserveExpensesUseCase
import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.core.income.usecase.ObserveIncomesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
    private val observeIncomesUseCase: ObserveIncomesUseCase,
    private val observeExpensesUseCase: ObserveExpensesUseCase
) : TransactionRepository {

    override suspend fun observe(): Flow<List<Transaction>> {
        return combine(
            observeIncomesUseCase.observe(),
            observeExpensesUseCase.observe()
        ) { incomes, expenses ->
            (incomes + expenses).mapNotNull {
                when (it) {
                    is Income -> Transaction.Income(it)
                    is Expense -> Transaction.Expense(it)
                    else -> null
                }
            }.sortedByDescending {
                when (it) {
                    is Transaction.Income -> it.income.date.time
                    is Transaction.Expense -> it.expense.date.time
                }
            }
        }
    }

}
