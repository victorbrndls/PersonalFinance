package com.victorbrndls.pfs.ui.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.core.category.entity.Category

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CategoryDropdownMenu(
    categories: List<Category>,
    category: Category?,
    onCategoryChanged: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var expanded by remember { mutableStateOf(false) }

    ClickableOverlay(
        onClick = {
            expanded = !expanded
            keyboardController?.hide()
        },
    ) {
        TextField(
            value = category?.label ?: "",
            onValueChange = {},
            label = { Text(text = stringResource(id = R.string.field_category_generic)) },
            modifier = Modifier.fillMaxWidth()
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            categories.forEach { cat ->
                DropdownMenuItem(
                    text = { Text(text = cat.label) },
                    onClick = {
                        expanded = false
                        onCategoryChanged(cat)
                    }
                )
            }
        }
    }
}