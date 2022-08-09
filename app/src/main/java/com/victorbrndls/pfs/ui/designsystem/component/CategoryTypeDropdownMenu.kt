package com.victorbrndls.pfs.ui.designsystem.component

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.ui.ktx.stringRes

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CategoryTypeDropdownMenu(
    type: CategoryType,
    onTypeChanged: (CategoryType) -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var expanded by remember { mutableStateOf(false) }

    ClickableOverlay(
        onClick = {
            expanded = !expanded
            keyboardController?.hide()
        }
    ) {
        TextField(
            value = when (type) {
                CategoryType.INCOME -> R.string.label_income
                CategoryType.EXPENSE -> R.string.label_expense
            }.let { stringResource(id = it) },
            onValueChange = {},
            label = { Text(text = stringResource(id = R.string.title_edit_category_type)) }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            CategoryType.values().forEach { type ->
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = type.stringRes)) },
                    onClick = {
                        expanded = false
                        onTypeChanged(type)
                    }
                )
            }
        }
    }
}