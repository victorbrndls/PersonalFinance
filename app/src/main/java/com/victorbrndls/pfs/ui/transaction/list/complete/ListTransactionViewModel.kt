package com.victorbrndls.pfs.ui.transaction.list.complete

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.core.summary.entity.Summary
import com.victorbrndls.pfs.core.summary.usecase.ObserveSummariesUseCase
import com.victorbrndls.pfs.core.transaction.entity.Transaction
import com.victorbrndls.pfs.core.transaction.usecase.ObserveTransactionsUseCase
import com.victorbrndls.pfs.infrastructure.date.*
import com.victorbrndls.pfs.infrastructure.money.MoneyTranslator
import com.victorbrndls.pfs.ui.transaction.filter.BarPeriodFilterEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

private const val DATE_FILTER_PERIOD_MONTHS = 12

@HiltViewModel
class ListTransactionViewModel @Inject constructor(
    private val observeTransactionsUseCase: ObserveTransactionsUseCase,
    private val observeSummariesUseCase: ObserveSummariesUseCase,
    private val dateTranslator: DateTranslator,
    private val moneyTranslator: MoneyTranslator,
) : ViewModel() {

    var _transactions: List<Transaction> by mutableStateOf(emptyList())
        private set

    val transactions: State<List<TransactionListItem>> = derivedStateOf {
        _transactions
            .filter { transaction ->
                categoryType?.let { type ->
                    if (transaction.category.type != categoryType) return@filter false
                }

                val inAnyPeriod = periods.any { period ->
                    val range = period.data as DateRange
                    transaction.date.isInRange(range)
                }
                if (periods.isNotEmpty() && !inAnyPeriod) return@filter false

                true
            }
            .groupBy { it.date.toLocalMidnight() }
            .flatMap { (date, transactions) ->
                listOf(TransactionDate(dateTranslator.formatYYYYMMDD(date))) +
                        transactions.sortedBy { it.category.type }.map { it.toModel() }
            }
    }

    private var summaries: List<Summary> by mutableStateOf(emptyList())

    var categoryType: CategoryType? by mutableStateOf(null)
        private set

    val periodFilter: List<BarPeriodFilterEntry> by derivedStateOf {
        if (summaries.isEmpty()) return@derivedStateOf emptyList()
        createPeriodFilter(summaries, categoryType)
    }

    private var periods: List<BarPeriodFilterEntry> by mutableStateOf(emptyList())

    var isLoading: Boolean by mutableStateOf(false)
        private set

    init {
        observeTransactions()
        observeSummaries()
    }

    private fun observeTransactions() {
        viewModelScope.launch {
            isLoading = true
            delay(1000)
            observeTransactionsUseCase.observe().collect { transactions ->
                _transactions = transactions
                isLoading = false
            }
        }
    }

    private fun observeSummaries() {
        viewModelScope.launch {
            observeSummariesUseCase.observe().collect { summaries ->
                this@ListTransactionViewModel.summaries = summaries
            }
        }
    }

    fun updateCategoryType(type: CategoryType?) {
        categoryType = if (type == categoryType) null else type
    }

    fun updatePeriod(entries: List<BarPeriodFilterEntry>) {
        periods = entries.filter { it.isSelected }
    }

    private fun createPeriodFilter(
        summaries: List<Summary>,
        category: CategoryType?
    ): List<BarPeriodFilterEntry> {
        return (0 until DATE_FILTER_PERIOD_MONTHS).map { idx ->
            val range = currentMonth().addMonths(-idx)
            val summary = summaries.firstOrNull { it.date.isInRange(range) }

            val value = when (category) {
                CategoryType.INCOME ->
                    summary?.income ?: BigDecimal.ZERO
                CategoryType.EXPENSE ->
                    summary?.expenses ?: BigDecimal.ZERO
                null ->
                    (summary?.income ?: BigDecimal.ZERO) + (summary?.expenses ?: BigDecimal.ZERO)
            }

            BarPeriodFilterEntry(
                id = idx.toString(),
                label = dateTranslator.formatYYMMM(range.start),
                value = value.toFloat(),
                data = range,
            )
        }.reversed()
    }

    private fun Transaction.toModel() = when (this) {
        is Transaction.Income -> income.toModel()
        is Transaction.Expense -> expense.toModel()
    }

    private fun Income.toModel() = TransactionIncomeModel(
        id = id,
        description = description,
        category = category.label,
        date = dateTranslator.formatYYYYMMDD(date),
        amount = moneyTranslator.format(amount),
    )

    private fun Expense.toModel() = TransactionExpenseModel(
        id = id,
        description = description,
        category = category.label,
        date = dateTranslator.formatYYYYMMDD(date),
        amount = moneyTranslator.format(amount),
    )
}

sealed interface TransactionListItem {
    val id: Any
}

data class TransactionDate(
    val date: String
) : TransactionListItem {
    override val id = date
}

data class TransactionIncomeModel(
    override val id: Long,
    val description: String,
    val category: String,
    val date: String,
    val amount: String
) : TransactionListItem

data class TransactionExpenseModel(
    override val id: Long,
    val description: String,
    val category: String,
    val date: String,
    val amount: String
) : TransactionListItem
