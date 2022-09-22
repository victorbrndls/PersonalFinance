package com.victorbrndls.pfs.ui.transaction.add

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.designsystem.component.ExpandableFloatingActionButton
import com.victorbrndls.pfs.ui.designsystem.component.IconTextButton
import com.victorbrndls.pfs.ui.designsystem.theme.White

@Composable
fun AddTransactionsFab(
    onNavigateToAddExpense: () -> Unit,
    onNavigateToAddIncome: () -> Unit,
) {
    ExpandableFloatingActionButton(
        fabContent = {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = stringResource(id = R.string.content_description_add),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }
    ) {
        IconTextButton(
            onClick = { onNavigateToAddIncome().also { close() } },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_add_24_black),
                    tint = White,
                    contentDescription = stringResource(id = R.string.title_add_income)
                )
            },
            text = {
                Text(text = stringResource(id = R.string.title_add_income))
            }
        )

        IconTextButton(
            onClick = { onNavigateToAddExpense().also { close() } },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_remove_24_black),
                    tint = White,
                    contentDescription = stringResource(id = R.string.title_add_expense)
                )
            },
            text = {
                Text(text = stringResource(id = R.string.title_add_expense))
            }
        )
    }
}
