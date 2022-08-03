package com.victorbrndls.pfs.ui.expense

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import com.victorbrndls.pfs.core.expense.dto.EditExpenseData
import com.victorbrndls.pfs.core.expense.usecase.SaveExpenseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditExpenseViewModel @Inject constructor(
    private val dateTranslator: DateTranslator,
    private val saveExpenseUseCase: SaveExpenseUseCase
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
            val expense = EditExpenseData(
                id = null,
                description = "",
                date = Date(),
                amount = 1000
            )

            saveExpenseUseCase.save(expense)
            closeScreen = true
        }
    }
}