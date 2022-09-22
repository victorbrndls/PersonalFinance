package com.victorbrndls.pfs.ui.transaction.list

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.both.entity.Both
import com.victorbrndls.pfs.core.both.usecase.ObserveBothUseCase
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import com.victorbrndls.pfs.infrastructure.money.MoneyTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListBothViewModel @Inject constructor(
    private val observeBothUseCase: ObserveBothUseCase,
    private val dateTranslator: DateTranslator,
    private val moneyTranslator: MoneyTranslator,
) : ViewModel() {

    var _both: List<Both> by mutableStateOf(emptyList())
        private set

    val both: State<List<BothListItem>> = derivedStateOf {
        _both
            .filter { both ->
                val categoryType = categoryType ?: return@filter true
                both.category.type == categoryType
            }
            .groupBy { dateTranslator.toLocalMidnight(it.date) }
            .flatMap { (date, boths) ->
                listOf(BothDate(dateTranslator.formatYYYYMMDD(date))) +
                        boths.sortedBy { it.category.type }.map { it.toModel() }
            }
    }

    var categoryType: CategoryType? by mutableStateOf(null)
        private set

    var isLoading: Boolean by mutableStateOf(false)
        private set

    init {
        loadBoth()
    }

    private fun loadBoth() {
        viewModelScope.launch {
            observeBothUseCase.observe().collect { both -> _both = both }
        }
    }

    fun updateCategoryType(type: CategoryType?) {
        categoryType = if (type == categoryType) null else type
    }

    private fun Both.toModel() = when (this) {
        is Both.Income -> income.toModel()
        is Both.Expense -> expense.toModel()
    }

    private fun Income.toModel() = BothIncomeModel(
        id = id,
        description = description,
        category = category.label,
        date = dateTranslator.formatYYYYMMDD(date),
        amount = moneyTranslator.format(amount),
    )

    private fun Expense.toModel() = BothExpenseModel(
        id = id,
        description = description,
        category = category.label,
        date = dateTranslator.formatYYYYMMDD(date),
        amount = moneyTranslator.format(amount),
    )
}

sealed interface BothListItem {
    val id: Any
}

data class BothDate(
    val date: String
) : BothListItem {
    override val id = date
}

data class BothIncomeModel(
    override val id: Long,
    val description: String,
    val category: String,
    val date: String,
    val amount: String
) : BothListItem

data class BothExpenseModel(
    override val id: Long,
    val description: String,
    val category: String,
    val date: String,
    val amount: String
) : BothListItem
