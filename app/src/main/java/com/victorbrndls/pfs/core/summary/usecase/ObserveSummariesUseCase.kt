package com.victorbrndls.pfs.core.summary.usecase

import com.victorbrndls.pfs.core.expense.usecase.ObserveExpensesUseCase
import com.victorbrndls.pfs.core.income.usecase.ObserveIncomesUseCase
import com.victorbrndls.pfs.core.summary.entity.Summary
import com.victorbrndls.pfs.infrastructure.date.DateRange
import com.victorbrndls.pfs.infrastructure.date.isInRange
import com.victorbrndls.pfs.infrastructure.date.rangeLast12Months
import com.victorbrndls.pfs.infrastructure.date.toMonthFirst
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.math.BigDecimal
import javax.inject.Inject

interface ObserveSummariesUseCase {
    /**
     * Summaries are returned ordered from newest to oldest
     */
    suspend fun observe(
        range: DateRange = rangeLast12Months()
    ): Flow<List<Summary>>
}

class ObserveSummariesUseCaseImpl @Inject constructor(
    private val observeIncomesUseCase: ObserveIncomesUseCase,
    private val observeExpensesUseCase: ObserveExpensesUseCase,
) : ObserveSummariesUseCase {

    override suspend fun observe(
        range: DateRange
    ): Flow<List<Summary>> {
        return combine(
            observeIncomesUseCase.observe(),
            observeExpensesUseCase.observe()
        ) { incomes, expenses ->
            val dates = (incomes.map { it.date } + expenses.map { it.date })
                .filter { date -> date.isInRange(range) }
                .map { it.toMonthFirst() }
                .toSet()
                .sortedDescending()

            val aggIncomes = incomes.groupBy { it.date.toMonthFirst() }
            val aggExpenses = expenses.groupBy { it.date.toMonthFirst() }

            val summaries = dates.map { date ->
                Summary(
                    date = date,
                    income = aggIncomes[date]?.sumOf { it.amount } ?: BigDecimal.ZERO,
                    expenses = aggExpenses[date]?.sumOf { it.amount } ?: BigDecimal.ZERO,
                    endingBalance = BigDecimal.ZERO,
                )
            }

            var endingBalance = BigDecimal.ZERO

            summaries.asReversed().map { summary ->
                summary.copy(endingBalance = endingBalance + summary.netSavings).also {
                    endingBalance = it.endingBalance
                }
            }.asReversed()
        }
    }

}
