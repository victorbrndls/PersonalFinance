package com.victorbrndls.pfs.ui.category.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.usecase.ObserveCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCategoriesViewModel @Inject constructor(
    private val observeCategoriesUseCase: ObserveCategoriesUseCase
) : ViewModel() {

    var categories: List<Category> by mutableStateOf(emptyList())
        private set

    var isLoading: Boolean by mutableStateOf(false)
        private set

    private val _isNavigateToAddCategory = Channel<Boolean>(Channel.CONFLATED)
    val isNavigateToAddCategory: Flow<Boolean> = _isNavigateToAddCategory.consumeAsFlow()

    var closeScreen: Boolean by mutableStateOf(false)
        private set

    init {
        observeCategories()
    }

    private fun observeCategories() {
        viewModelScope.launch {
            isLoading = true
            observeCategoriesUseCase.observe().collect { categories ->
                isLoading = false
                this@ListCategoriesViewModel.categories = categories.sortedBy { it.label }
            }
        }
    }

    fun addNewCategory() {
        _isNavigateToAddCategory.trySend(true)
    }

}