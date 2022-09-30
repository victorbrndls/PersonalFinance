package com.victorbrndls.pfs.core.summary.usecase

import com.victorbrndls.pfs.core.expense.usecase.GetExpensesUseCase
import com.victorbrndls.pfs.core.income.usecase.GetIncomesUseCase
import com.victorbrndls.pfs.core.summary.entity.Summary
import com.victorbrndls.pfs.infrastructure.date.DateRange
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import com.victorbrndls.pfs.infrastructure.date.rangeLast12Months
import com.victorbrndls.pfs.infrastructure.date.toMonthFirst
import java.math.BigDecimal
import javax.inject.Inject

interface GetSummariesUseCase {
    /**
     * Summaries are returned ordered from newest to oldest
     */
    suspend fun getAll(
        range: DateRange = rangeLast12Months()
    ): List<Summary>
}

class GetSummariesUseCaseImpl @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase,
    private val getExpensesUseCase: GetExpensesUseCase,
    private val dateTranslator: DateTranslator,
) : GetSummariesUseCase {

    override suspend fun getAll(
        range: DateRange
    ): List<Summary> {
        val incomes = getIncomesUseCase.getAll()
        val expenses = getExpensesUseCase.getAll()

        val dates = (incomes.map { it.date } + expenses.map { it.date })
            .filter { date ->
                date.after(range.start) && range.end?.let { end -> date.before(end) } ?: true
            }
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

        return summaries.asReversed().map { summary ->
            summary.copy(endingBalance = endingBalance + summary.netSavings).also {
                endingBalance = it.endingBalance
            }
        }.asReversed()
    }

}
