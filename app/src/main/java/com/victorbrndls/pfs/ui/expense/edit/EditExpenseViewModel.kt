package com.victorbrndls.pfs.ui.expense.edit

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.core.category.usecase.ObserveCategoriesUseCase
import com.victorbrndls.pfs.core.expense.dto.EditExpenseData
import com.victorbrndls.pfs.core.expense.usecase.SaveExpenseUseCase
import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EditExpenseViewModel @Inject constructor(
    private val dateTranslator: DateTranslator,
    private val observeCategoriesUseCase: ObserveCategoriesUseCase,
    private val saveExpenseUseCase: SaveExpenseUseCase
) : ViewModel() {

    var description: String by mutableStateOf("")

    var categories: List<Category> by mutableStateOf(emptyList())
        private set
    var category: Category? by mutableStateOf(null)

    var date: Date? by mutableStateOf(null)
    val formattedDate: State<String> = derivedStateOf {
        date?.let { dateTranslator.formatYYYYMMDD(it) } ?: ""
    }

    var amount: String by mutableStateOf("")

    var closeScreen: Boolean by mutableStateOf(false)
        private set

    init {
        observeCategories()
    }

    private fun observeCategories() {
        viewModelScope.launch {
            observeCategoriesUseCase.observe(
                type = CategoryType.EXPENSE
            ).collect { cats ->
                categories = cats.sortedBy { it.label }
            }
        }
    }

    fun onSaveClicked() {
        val category = category ?: return

        viewModelScope.launch {
            val expense = EditExpenseData(
                id = null,
                description = description.trim(),
                category = category,
                date = date ?: Date(),
                amount = amount.toBigDecimalOrNull() ?: BigDecimal.ZERO
            )

            saveExpenseUseCase.save(expense)
            closeScreen = true
        }
    }
}