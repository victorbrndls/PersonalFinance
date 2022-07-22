@file:OptIn(ExperimentalMaterial3Api::class)

package com.victorbrndls.pfs.expense

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.designsystem.component.ClickableOverlay
import com.victorbrndls.pfs.designsystem.component.PfsTopAppBar

val textFieldSpacingModifier = Modifier
    .fillMaxWidth()
    .padding(horizontal = 16.dp, vertical = 10.dp)

@Composable
fun ModifyExpenseRoute(
    modifier: Modifier = Modifier,
    viewModel: ModifyExpenseViewModel = hiltViewModel()
) {
    ModifyExpenseScreen()
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ModifyExpenseScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            PfsTopAppBar(titleRes = R.string.title_modify_expense)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* todo */ }) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = textFieldSpacingModifier) {
                    CustomTextField(
                        value = "",
                        onValueChange = {},
                        labelRes = R.string.field_description_generic
                    )
                }
                Box(modifier = textFieldSpacingModifier) {
                    ClickableOverlay {
                        CustomTextField(
                            value = "",
                            onValueChange = {},
                            labelRes = R.string.field_date_generic
                        )
                    }
                }
                Box(modifier = textFieldSpacingModifier) {
                    CustomTextField(
                        value = "",
                        onValueChange = {},
                        labelRes = R.string.field_amount_generic
                    )
                }
            }
        }
    }
}

@Composable
private fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    @StringRes labelRes: Int,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = labelRes)) },
        modifier = modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun ModifyExpenseScreenPreview() {
    ModifyExpenseScreen()
}