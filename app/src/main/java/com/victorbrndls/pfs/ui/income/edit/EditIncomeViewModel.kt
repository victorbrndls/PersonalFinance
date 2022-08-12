package com.victorbrndls.pfs.ui.income.edit

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.core.category.usecase.ObserveCategoriesUseCase
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
    private val observeCategoriesUseCase: ObserveCategoriesUseCase,
    private val saveIncomeUseCase: SaveIncomeUseCase
) : ViewModel() {

    var description: String by mutableStateOf("")

    var categories: List<Category> by mutableStateOf(emptyList())
        private set
    var category: Category? by mutableStateOf(null)

    var date: Date? by mutableStateOf(null)
    val formattedDate: State<String> = derivedStateOf {
        date?.let { dateTranslator.format(it) } ?: ""
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
                type = CategoryType.INCOME
            ).collect { cats ->
                categories = cats.sortedBy { it.label }
            }
        }
    }

    fun onSaveClicked() {
        val category = category ?: return

        viewModelScope.launch {
            val income = EditIncomeData(
                id = null,
                description = description.trim(),
                category = category,
                date = date ?: Date(),
                amount = amount.toBigDecimalOrNull() ?: BigDecimal.ZERO
            )

            saveIncomeUseCase.save(income)
            closeScreen = true
        }
    }
}