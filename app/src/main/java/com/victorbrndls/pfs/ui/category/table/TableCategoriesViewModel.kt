package com.victorbrndls.pfs.ui.category.table

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.core.category.usecase.ObserveCategoriesUseCase
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.expense.usecase.ObserveExpensesUseCase
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import com.victorbrndls.pfs.infrastructure.date.toMonthFirst
import com.victorbrndls.pfs.infrastructure.money.MoneyTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class TableCategoriesViewModel @Inject constructor(
    private val observeCategoriesUseCase: ObserveCategoriesUseCase,
    private val observeExpensesUseCase: ObserveExpensesUseCase,
    private val dateTranslator: DateTranslator,
    private val moneyTranslator: MoneyTranslator
) : ViewModel() {

    private var _categories: List<Category> by mutableStateOf(emptyList())
    private var _expenses: List<Expense> by mutableStateOf(emptyList())

    val categoryItems: List<CategoryItem> by derivedStateOf {
        _categories.map {
            CategoryItem(it.label, 50)
        }
    }

    val monthlyItems: List<CategoryMonth> by derivedStateOf {
        val expensesByCategory = _expenses.groupBy { it.category }
        val months = _expenses.map { it.date.toMonthFirst() }.toSet()

        expensesByCategory.map { (category, expenses) ->
            val expensesByMonth = expenses.groupBy { it.date.toMonthFirst() }

            val transactionItem = months.map { month ->
                val amount = expensesByMonth[month]?.sumOf { it.amount } ?: BigDecimal.ZERO
                CategoryTransactionItem(
                    moneyTranslator.formatWhole(amount),
                    when {
                        amount < BigDecimal.valueOf(100) -> 50
                        amount < BigDecimal.valueOf(500) -> 90
                        else -> 150
                    }
                )
            }

            category to CategoryMonth(transactionItem)
        }
            .sortedBy { (category, _) -> categoryItems.indexOfFirst { it.label == category.label } }
            .map { it.second }
    }


    val months: List<String> by derivedStateOf {
        val expensesByMonth = _expenses.map { it.date.toMonthFirst() }.toSet()
        expensesByMonth.map { dateTranslator.formatYYMMM(it) }
    }

    var isLoading: Boolean by mutableStateOf(false)
        private set

    init {
        observeCategories()
        observeExpenses()
    }

    private fun observeCategories() {
        viewModelScope.launch {
            observeCategoriesUseCase.observe(type = CategoryType.EXPENSE).collect { categories ->
                _categories = categories
                    .sortedBy { it.label }
            }
        }
    }

    private fun observeExpenses() {
        viewModelScope.launch {
            observeExpensesUseCase.observe().collect { expenses ->
                _expenses = expenses
                    .sortedByDescending { it.date }
            }
        }
    }

}

data class CategoryItem(
    val label: String,
    val percentage: Int, // 0..+100
)

data class CategoryMonth(
    val items: List<CategoryTransactionItem>
)

data class CategoryTransactionItem(
    val amount: String,
    val percentage: Int, // 0..+100
)