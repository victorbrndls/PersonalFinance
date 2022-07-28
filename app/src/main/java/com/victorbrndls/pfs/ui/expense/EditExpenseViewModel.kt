package com.victorbrndls.pfs.ui.expense

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.victorbrndls.pfs.b.DateTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditExpenseViewModel @Inject constructor(
    private val dateTranslator: DateTranslator
) : ViewModel() {

    var description: String by mutableStateOf("")

    private var backingDate: Date? by mutableStateOf(Date())
    val date: State<String> = derivedStateOf {
        backingDate?.let { dateTranslator.format(it) } ?: ""
    }

    var amount: String by mutableStateOf("")

    fun updateDate(date: String) {
        backingDate = dateTranslator.parse(date)
    }

    fun onSaveClicked() {

    }
}