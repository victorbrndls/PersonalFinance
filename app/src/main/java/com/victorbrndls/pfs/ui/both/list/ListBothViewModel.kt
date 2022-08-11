package com.victorbrndls.pfs.ui.both.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.both.usecase.GetBothUseCase
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.income.entity.Income
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListBothViewModel @Inject constructor(
    private val getBothUseCase: GetBothUseCase
) : ViewModel() {

    var both: List<BothModel> by mutableStateOf(emptyList())
        private set

    var isLoading: Boolean by mutableStateOf(false)
        private set

    init {
        loadBoth()
    }

    private fun loadBoth() {
        viewModelScope.launch {
            getBothUseCase.getAll().let { both ->
                val expenses = both.expenses.map { BothModel.ExpenseModel(it) }
                val incomes = both.incomes.map { BothModel.IncomeModel(it) }
                expenses + incomes
            }.let { both = it }
        }
    }

}

sealed class BothModel(val id: Long) {
    data class IncomeModel(val income: Income) : BothModel(income.id)
    data class ExpenseModel(val expense: Expense) : BothModel(expense.id)
}