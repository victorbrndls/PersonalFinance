package com.victorbrndls.pfs.ui.category.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.victorbrndls.pfs.core.category.usecase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListCategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    var description: String by mutableStateOf("")

    var closeScreen: Boolean by mutableStateOf(false)
        private set

    fun addNewCategory() {

    }

}