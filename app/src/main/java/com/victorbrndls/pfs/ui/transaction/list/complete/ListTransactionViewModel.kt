package com.victorbrndls.pfs.ui.transaction.list.complete

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.transaction.entity.Transaction
import com.victorbrndls.pfs.core.transaction.usecase.ObserveTransactionsUseCase
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import com.victorbrndls.pfs.infrastructure.date.toLocalMidnight
import com.victorbrndls.pfs.infrastructure.money.MoneyTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTransactionViewModel @Inject constructor(
    private val observeTransactionsUseCase: ObserveTransactionsUseCase,
    private val dateTranslator: DateTranslator,
    private val moneyTranslator: MoneyTranslator,
) : ViewModel() {

    var _transactions: List<Transaction> by mutableStateOf(emptyList())
        private set

    val transactions: State<List<TransactionListItem>> = derivedStateOf {
        _transactions
            .filter { transaction ->
                val categoryType = categoryType ?: return@filter true
                transaction.category.type == categoryType
            }
            .groupBy { it.date.toLocalMidnight() }
            .flatMap { (date, transactions) ->
                listOf(TransactionDate(dateTranslator.formatYYYYMMDD(date))) +
                        transactions.sortedBy { it.category.type }.map { it.toModel() }
            }
    }

    var categoryType: CategoryType? by mutableStateOf(null)
        private set

    var isLoading: Boolean by mutableStateOf(false)
        private set

    init {
        loadTransactions()
    }

    private fun loadTransactions() {
        viewModelScope.launch {
            isLoading = true
            delay(1000)
            observeTransactionsUseCase.observe().collect { transactions ->
                _transactions = transactions
                isLoading = false
            }
        }
    }

    fun updateCategoryType(type: CategoryType?) {
        categoryType = if (type == categoryType) null else type
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
