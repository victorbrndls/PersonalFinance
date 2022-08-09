package com.victorbrndls.pfs.ui.designsystem.component

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import java.util.*

@Composable
fun DatePickerField(
    date: Date?,
    onDateChanged: (Date) -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    var isShown by remember { mutableStateOf(false) }

    ClickableOverlay(
        onClick = { isShown = true },
        modifier = modifier.onFocusChanged { state ->
            if (state.isFocused) isShown = true
        }
    ) {
        content()
    }

    if (isShown)
        DatePickerDialog(
            date = date,
            onDateChanged = onDateChanged,
            onDismissed = { isShown = false }
        )
}