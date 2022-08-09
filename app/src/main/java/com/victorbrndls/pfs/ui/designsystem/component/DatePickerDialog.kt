package com.victorbrndls.pfs.ui.designsystem.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.FragmentActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.victorbrndls.pfs.R
import java.util.*

@Composable
fun DatePickerDialog(
    date: Date?,
    onDateChanged: (Date) -> Unit,
    onDismissed: () -> Unit
) {
    val fragmentManager = (LocalContext.current as FragmentActivity).supportFragmentManager
    val title = stringResource(id = R.string.date_picker_select_date)

    val picker = remember(fragmentManager) {
        MaterialDatePicker.Builder.datePicker()
            .setTitleText(title)
            .setSelection(date?.time ?: Date().time)
            .build()
            .apply {
                addOnDismissListener { onDismissed() }
                addOnPositiveButtonClickListener { onDateChanged(Date(it)) }
            }
    }

    DisposableEffect(picker) {
        picker.show(fragmentManager, null)

        onDispose { picker.dismiss() }
    }
}