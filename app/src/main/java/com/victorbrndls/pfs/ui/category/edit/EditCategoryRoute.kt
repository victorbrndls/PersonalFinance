package com.victorbrndls.pfs.ui.category.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.victorbrndls.pfs.R

@Composable
fun EditCategoryRoute(
    navController: NavController,
    viewModel: EditCategoryViewModel = hiltViewModel()
) {
    if (viewModel.closeScreen) LaunchedEffect(Unit) {
        navController.popBackStack()
    }

    EditCategoryDialog(
        value = viewModel.label,
        onValueChanged = { value -> viewModel.label = value },
        onSaveClicked = viewModel::onSaveClicked,
        onDismissRequest = { navController.popBackStack() }
    )
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun EditCategoryDialog(
    value: String,
    onValueChanged: (String) -> Unit,
    onSaveClicked: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = ShapeDefaults.Medium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                val keyboardController = LocalSoftwareKeyboardController.current

                Text(text = stringResource(id = R.string.title_add_category))

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    value = value,
                    onValueChange = onValueChanged,
                    label = { Text(text = stringResource(id = R.string.title_edit_category_label)) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(onAny = { keyboardController?.hide() })
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = onSaveClicked, modifier = Modifier.align(Alignment.End)) {
                    Text(text = stringResource(id = R.string.content_description_save))
                }
            }
        }
    }
}