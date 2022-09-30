package com.victorbrndls.pfs.ui.summary.singleperiod

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.summary.usecase.GetSummariesUseCase
import com.victorbrndls.pfs.infrastructure.date.currentMonth
import com.victorbrndls.pfs.infrastructure.money.MoneyTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SinglePeriodSummaryViewModel @Inject constructor(
    private val getSummariesUseCase: GetSummariesUseCase,
    private val moneyTranslator: MoneyTranslator,
) : ViewModel() {

    var item: SinglePeriodSummaryItem? by mutableStateOf(null)
        private set

    var isLoading: Boolean by mutableStateOf(false)
        private set

    init {
        loadSummary()
    }

    private fun loadSummary() {
        viewModelScope.launch {
            isLoading = true

            item = getSummariesUseCase.getAll(
                range = currentMonth()
            ).let { summaries ->
                SinglePeriodSummaryItem(
                    income = "+" + moneyTranslator.formatWhole(summaries.sumOf { it.income }),
                    expenses = "-" + moneyTranslator.formatWhole(summaries.sumOf { it.expenses })
                )
            }

            isLoading = false
        }
    }

}

data class SinglePeriodSummaryItem(
    val income: String,
    val expenses: String,
)