package com.victorbrndls.pfs.ui.both.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.both.entity.Both
import com.victorbrndls.pfs.core.both.usecase.ObserveBothUseCase
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.income.entity.Income
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListBothViewModel @Inject constructor(
    private val observeBothUseCase: ObserveBothUseCase
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
            observeBothUseCase.observe().collect { both ->
                this@ListBothViewModel.both = both.map {
                    when (it) {
                        is Both.Income -> BothModel.IncomeModel(it.income)
                        is Both.Expense -> BothModel.ExpenseModel(it.expense)
                    }
                }
            }
        }
    }
}

sealed class BothModel(val id: Long) {
    data class IncomeModel(val income: Income) : BothModel(income.id)
    data class ExpenseModel(val expense: Expense) : BothModel(expense.id)
}