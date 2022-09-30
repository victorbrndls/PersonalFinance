package com.victorbrndls.pfs.ui.transaction.list.smaller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.core.transaction.entity.Transaction
import com.victorbrndls.pfs.core.transaction.usecase.ObserveTransactionsUseCase
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import com.victorbrndls.pfs.infrastructure.money.MoneyTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ListShortTransactionViewModel @Inject constructor(
    private val observeTransactionsUseCase: ObserveTransactionsUseCase,
    private val dateTranslator: DateTranslator,
    private val moneyTranslator: MoneyTranslator,
) : ViewModel() {

    var transactions: List<TransactionListItem> by mutableStateOf(emptyList())
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

            observeTransactionsUseCase.observe().collect { items ->
                val now = Date()

                transactions = items
                    .filter { it.date <= now }
                    .sortedBy { it.date }
                    .takeLast(8)
                    .reversed()
                    .map { it.toModel() }
                isLoading = false
            }
        }
    }

    private fun Transaction.toModel() = when (this) {
        is Transaction.Income -> income.toModel()
        is Transaction.Expense -> expense.toModel()
    }

    private fun Income.toModel() = TransactionIncomeModel(
        id = id,
        description = description,
        date = dateTranslator.formatMMMDD(date),
        amount = moneyTranslator.format(amount),
    )

    private fun Expense.toModel() = TransactionExpenseModel(
        id = id,
        description = description,
        date = dateTranslator.formatMMMDD(date),
        amount = moneyTranslator.format(amount),
    )
}

sealed interface TransactionListItem {
    val id: Any
}

data class TransactionIncomeModel(
    override val id: Long,
    val description: String,
    val date: String,
    val amount: String
) : TransactionListItem

data class TransactionExpenseModel(
    override val id: Long,
    val description: String,
    val date: String,
    val amount: String
) : TransactionListItem
