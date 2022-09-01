package com.victorbrndls.pfs.ui.summary

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.summary.entity.Summary
import com.victorbrndls.pfs.core.summary.usecase.GetSummariesUseCase
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import com.victorbrndls.pfs.infrastructure.money.MoneyTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val getSummariesUseCase: GetSummariesUseCase,
    private val dateTranslator: DateTranslator,
    private val moneyTranslator: MoneyTranslator,
) : ViewModel() {

    var items: List<SummaryItem> by mutableStateOf(emptyList())
        private set

    var isLoading: Boolean by mutableStateOf(false)
        private set

    init {
        loadSummary()
    }

    private fun loadSummary() {
        viewModelScope.launch {
            isLoading = true
            items = getSummariesUseCase.getAll().map { summary -> summary.toItem() }
            isLoading = false
        }
    }

    private fun Summary.toItem() = SummaryItem(
        date = dateTranslator.formatYYMMM(date),
        income = moneyTranslator.formatWhole(income),
        expenses = moneyTranslator.formatWhole(expenses),
        netSavings = moneyTranslator.formatWhole(netSavings),
        endingBalance = moneyTranslator.formatWhole(endingBalance),
    )

}

data class SummaryItem(
    val date: String,
    val income: String,
    val expenses: String,
    val netSavings: String,
    val endingBalance: String,
)