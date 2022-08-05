package com.victorbrndls.pfs.ui.category.edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorbrndls.pfs.core.category.dto.EditCategoryData
import com.victorbrndls.pfs.core.category.usecase.SaveCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCategoryViewModel @Inject constructor(
    private val saveCategoryUseCase: SaveCategoryUseCase
) : ViewModel() {

    var label: String by mutableStateOf("")

    var closeScreen: Boolean by mutableStateOf(false)
        private set

    fun onSaveClicked() {
        if (label.isBlank()) return

        viewModelScope.launch {
            val expense = EditCategoryData(
                id = null,
                label = label.trim(),
            )

            saveCategoryUseCase.save(expense)
            closeScreen = true
        }
    }
}