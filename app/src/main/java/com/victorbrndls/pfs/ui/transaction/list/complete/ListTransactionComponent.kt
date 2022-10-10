package com.victorbrndls.pfs.ui.transaction.list.complete

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorbrndls.pfs.ui.designsystem.theme.Black10
import com.victorbrndls.pfs.ui.designsystem.theme.Green40
import com.victorbrndls.pfs.ui.designsystem.theme.Red40
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun ListTransactionComponent(
    transactions: List<TransactionListItem>,
    modifier: Modifier = Modifier,
    scrollOffset: (Int) -> Unit = {},
) {
    ListTransactionUI(
        transactions = transactions,
        scrollOffset = scrollOffset,
        modifier = modifier
    )
}

@Composable
private fun ListTransactionUI(
    transactions: List<TransactionListItem>,
    scrollOffset: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = rememberLazyListState()

    LaunchedEffect(state) {
        snapshotFlow { state.firstVisibleItemScrollOffset }
            .collect { scrollOffset(it) }
    }

    LazyColumn(
        state = state,
        modifier = modifier
    ) {
        items(
            items = transactions,
            key = { it.id },
            contentType = { it::class }
        ) { item ->
            when (item) {
                is TransactionDate -> DateHeader(item.date)
                is TransactionExpenseModel -> ExpenseItem(item)
                is TransactionIncomeModel -> IncomeItem(item)
            }
        }
    }
}

@Composable
private fun DateHeader(date: String) {
    Surface(
        color = Black10,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = date,
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}

@Composable
private fun IncomeItem(
    income: TransactionIncomeModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.transactionDefault()
    ) {
        Text(
            text = income.description,
            fontSize = 16.sp
        )
        Text(
            text = income.amount,
            color = Green40
        )
    }
}

@Composable
private fun ExpenseItem(
    expense: TransactionExpenseModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.transactionDefault()
    ) {
        Text(
            text = expense.description,
            fontSize = 16.sp
        )
        Text(
            text = expense.amount,
            color = Red40
        )
    }
}

private fun Modifier.transactionDefault() =
    fillMaxWidth()
        .padding(vertical = 6.dp, horizontal = 12.dp)