package com.victorbrndls.pfs.ui.chart.income_netsavings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.summary.entity.Summary
import com.victorbrndls.pfs.core.summary.usecase.GetSummariesUseCase
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import com.victorbrndls.pfs.infrastructure.date.last12Months
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IncomeAndNetSavingsChartViewModel @Inject constructor(
    private val getSummariesUseCase: GetSummariesUseCase,
    private val dateTranslator: DateTranslator,
) : ViewModel() {

    var entries: List<IncomeAndNetSavingsChartEntry> by mutableStateOf(emptyList())
        private set

    init {
        loadSummary()
    }

    private fun loadSummary() {
        viewModelScope.launch {
            // TODO what if summaries is empty?
            entries = getSummariesUseCase.getAll(
                range = last12Months()
            )
                .map { summary -> summary.toItem() }
                .reversed() // show oldest to newest
        }
    }

    private fun Summary.toItem() = IncomeAndNetSavingsChartEntry(
        date = dateTranslator.formatYYMMM(date),
        income = income.toFloat(),
        netSavings = netSavings.toFloat(),
    )

}

data class IncomeAndNetSavingsChartEntry(
    val date: String,
    val income: Float,
    val netSavings: Float
)