package com.victorbrndls.pfs.core.summary.usecase

import com.victorbrndls.pfs.core.expense.usecase.GetExpensesUseCase
import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.core.income.usecase.GetIncomesUseCase
import com.victorbrndls.pfs.core.summary.entity.Summary
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import java.math.BigDecimal
import javax.inject.Inject

interface GetSummariesUseCase {
    suspend fun getAll(): List<Summary>
}

class GetSummariesUseCaseImpl @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase,
    private val getExpensesUseCase: GetExpensesUseCase,
    private val dateTranslator: DateTranslator,
) : GetSummariesUseCase {

    override suspend fun getAll(): List<Summary> {
        val incomes = getIncomesUseCase.getAll()
        val expenses = getExpensesUseCase.getAll()

        val dates = (incomes.map { it.date } + expenses.map { it.date })
            .map { dateTranslator.toMonthFirst(it) }
            .toSet()
            .sortedDescending()

        val aggIncomes = incomes.groupBy { dateTranslator.toMonthFirst(it.date) }
        val aggExpenses = expenses.groupBy { dateTranslator.toMonthFirst(it.date) }

        return dates.map { date ->
            Summary(
                date = date,
                income = aggIncomes[date]?.sumOf { it.amount } ?: BigDecimal.ZERO,
                expenses = aggExpenses[date]?.sumOf { it.amount } ?: BigDecimal.ZERO,
                endingBalance = BigDecimal.ZERO,
            )
        }
    }

}
