package com.victorbrndls.pfs.ui.expense.edit

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.designsystem.component.ClickableOverlay
import com.victorbrndls.pfs.ui.designsystem.component.PfsTopAppBar
import java.util.*

private val textFieldSpacingModifier = Modifier
    .fillMaxWidth()
    .padding(horizontal = 16.dp, vertical = 10.dp)

@Composable
fun EditExpenseRoute(
    navController: NavController,
    viewModel: EditExpenseViewModel = hiltViewModel()
) {
    if (viewModel.closeScreen) LaunchedEffect(Unit) {
        navController.popBackStack()
    }

    EditExpenseScreen(
        description = viewModel.description,
        onDescriptionChanged = { viewModel.description = it },
        date = viewModel.date.value,
        onDateChanged = { viewModel.updateDate(it) },
        amount = viewModel.amount,
        onAmountChanged = { viewModel.amount = it },
        onSaveClicked = { viewModel.onSaveClicked() },
        onNavigateUp = { navController.popBackStack() }
    )
}

@OptIn(
    ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
private fun EditExpenseScreen(
    description: String,
    onDescriptionChanged: (String) -> Unit,
    date: String,
    onDateChanged: (Date) -> Unit,
    amount: String,
    onAmountChanged: (String) -> Unit,
    onSaveClicked: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            PfsTopAppBar(
                titleRes = R.string.title_edit_expense,
                onNavigationClick = { onNavigateUp() })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onSaveClicked) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = stringResource(id = R.string.content_description_save),
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
            val amountFocusRequester = remember { FocusRequester() }
            val keyboardController = LocalSoftwareKeyboardController.current

            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = textFieldSpacingModifier) {
                    CustomTextField(
                        value = description,
                        onValueChange = onDescriptionChanged,
                        labelRes = R.string.field_description_generic,
                        onImeAction = {
                            // TODO open calendar
                            defaultKeyboardAction(ImeAction.Next)
                        },
                        modifier = Modifier.focusProperties {
                            next = amountFocusRequester
                        }
                    )
                }
                Box(modifier = textFieldSpacingModifier) {
                    ClickableOverlay(
                        onClicked = {
                            // TODO open calendar
                        }
                    ) {
                        CustomTextField(
                            value = date,
                            onValueChange = {},
                            labelRes = R.string.field_date_generic
                        )
                    }
                }
                Box(modifier = textFieldSpacingModifier) {
                    CustomTextField(
                        value = amount,
                        onValueChange = onAmountChanged,
                        labelRes = R.string.field_amount_generic,
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done,
                        onImeAction = { keyboardController?.hide() },
                        modifier = Modifier.focusRequester(amountFocusRequester)
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
    @StringRes labelRes: Int,
    modifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onImeAction: KeyboardActionScope.() -> Unit = { defaultKeyboardAction(imeAction) }
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = labelRes)) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(onAny = { onImeAction() })
    )
}