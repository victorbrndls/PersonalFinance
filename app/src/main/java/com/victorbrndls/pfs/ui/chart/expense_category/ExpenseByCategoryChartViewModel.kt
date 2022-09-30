package com.victorbrndls.pfs.ui.chart.expense_category

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.expense.usecase.GetExpensesUseCase
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import com.victorbrndls.pfs.infrastructure.date.toMonthFirst
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseByCategoryChartViewModel @Inject constructor(
    private val getExpensesUseCase: GetExpensesUseCase,
    private val dateTranslator: DateTranslator,
) : ViewModel() {

    var entries: List<ExpenseByCategoryChartEntry> by mutableStateOf(emptyList())
        private set

    init {
        loadExpenses()
    }

    private fun loadExpenses() {
        viewModelScope.launch {
            // TODO what if expenses is empty?
            entries = getExpensesUseCase.getAll()
                .sortedByDescending { it.date }
                .groupBy { it.date.toMonthFirst() }
                .map {
                    val (date, expenses) = it

                    val categoriesByMonth = expenses.groupBy { exp -> exp.category }
                    val categoryEntries = categoriesByMonth.map { (category, expenses) ->
                        CategoryEntry(
                            category.label,
                            expenses.sumOf { exp -> exp.amount }.toFloat()
                        )
                    }.sortedByDescending { entry -> entry.amount }
                    ExpenseByCategoryChartEntry(dateTranslator.formatYYMMM(date), categoryEntries)
                }
        }
    }

}


data class ExpenseByCategoryChartEntry(
    val date: String,
    val categoryEntries: List<CategoryEntry>,
)

data class CategoryEntry(
    val category: String,
    val amount: Float,
)