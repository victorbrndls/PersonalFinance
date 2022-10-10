package com.victorbrndls.pfs.ui.chart.savingsrate

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
class SavingsRateChartViewModel @Inject constructor(
    private val getSummariesUseCase: GetSummariesUseCase,
    private val dateTranslator: DateTranslator,
) : ViewModel() {

    var entries: List<SavingsRateChartEntry> by mutableStateOf(emptyList())
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

    private fun Summary.toItem(): SavingsRateChartEntry {
        val rate = (1 - expenses.toFloat() / income.toFloat()) * 100f

        return SavingsRateChartEntry(
            date = dateTranslator.formatYYMMM(date),
            rate = if (rate >= 0 && rate.isFinite()) rate else 0f
        )
    }

}

data class SavingsRateChartEntry(
    val date: String,
    val rate: Float,
)