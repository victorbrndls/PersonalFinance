package com.victorbrndls.pfs.ui.summary.singleperiod

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.summary.usecase.ObserveSummariesUseCase
import com.victorbrndls.pfs.infrastructure.date.currentMonth
import com.victorbrndls.pfs.infrastructure.money.MoneyTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SinglePeriodSummaryViewModel @Inject constructor(
    private val observeSummariesUseCase: ObserveSummariesUseCase,
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

            observeSummariesUseCase.observe(
                range = currentMonth()
            ).collect { summaries ->
                isLoading = false
                item = SinglePeriodSummaryItem(
                    income = "+" + moneyTranslator.formatWhole(summaries.sumOf { it.income }),
                    expenses = "-" + moneyTranslator.formatWhole(summaries.sumOf { it.expenses })
                )
            }
        }
    }

}

data class SinglePeriodSummaryItem(
    val income: String,
    val expenses: String,
)