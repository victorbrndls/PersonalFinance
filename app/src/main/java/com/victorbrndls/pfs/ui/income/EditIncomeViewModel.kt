package com.victorbrndls.pfs.ui.income

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.expense.dto.EditExpenseData
import com.victorbrndls.pfs.core.income.dto.EditIncomeData
import com.victorbrndls.pfs.core.income.usecase.SaveIncomeUseCase
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditIncomeViewModel @Inject constructor(
    private val dateTranslator: DateTranslator,
    private val saveIncomeUseCase: SaveIncomeUseCase
) : ViewModel() {

    var description: String by mutableStateOf("")

    private var backingDate: Date? by mutableStateOf(Date())
    val date: State<String> = derivedStateOf {
        backingDate?.let { dateTranslator.format(it) } ?: ""
    }

    var amount: String by mutableStateOf("")

    var closeScreen: Boolean by mutableStateOf(false)
        private set

    fun updateDate(date: Date) {
        backingDate = date
    }

    fun onSaveClicked() {
        viewModelScope.launch {
            val income = EditIncomeData(
                id = null,
                description = description.trim(),
                date = backingDate ?: Date(),
                amount = amount.toBigDecimalOrNull() ?: BigDecimal.ZERO
            )

            saveIncomeUseCase.save(income)
            closeScreen = true
        }
    }
}