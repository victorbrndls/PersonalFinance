package com.victorbrndls.pfs.ui.chart.income_netsavings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.summary.entity.Summary
import com.victorbrndls.pfs.core.summary.usecase.GetSummariesUseCase
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import com.victorbrndls.pfs.infrastructure.date.rangeLast12Months
import com.victorbrndls.pfs.infrastructure.money.MoneyTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncomeAndSavingsRateChartViewModel @Inject constructor(
    private val getSummariesUseCase: GetSummariesUseCase,
    private val dateTranslator: DateTranslator,
) : ViewModel() {

    var entries: List<IncomeAndSavingsRateChartEntry> by mutableStateOf(emptyList())
        private set

    init {
        loadSummary()
    }

    private fun loadSummary() {
        viewModelScope.launch {
            // TODO what if summaries is empty?
            entries = getSummariesUseCase.getAll(
                range = rangeLast12Months()
            )
                .map { summary -> summary.toItem() }
                .reversed().let { it + it } // show oldest to newest
        }
    }

    private fun Summary.toItem() = IncomeAndSavingsRateChartEntry(
        date = dateTranslator.formatYYMMM(date),
        income = income.toFloat(),
        netSavings = netSavings.toFloat(),
    )

}

data class IncomeAndSavingsRateChartEntry(
    val date: String,
    val income: Float,
    val netSavings: Float
)