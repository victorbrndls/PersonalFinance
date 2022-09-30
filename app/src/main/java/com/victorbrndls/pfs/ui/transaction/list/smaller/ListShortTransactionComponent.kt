package com.victorbrndls.pfs.ui.transaction.list.smaller

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorbrndls.pfs.ui.designsystem.component.progress.PfsCircularProgressIndicatorBox
import com.victorbrndls.pfs.ui.designsystem.theme.Green40
import com.victorbrndls.pfs.ui.designsystem.theme.Red40

@Composable
fun ListShortTransactionComponent(
    modifier: Modifier = Modifier,
    viewModel: ListShortTransactionViewModel = hiltViewModel(),
) {
    ListShortTransactionUI(
        transactions = viewModel.transactions,
        isLoading = viewModel.isLoading,
        modifier = modifier
    )
}

@Composable
private fun ListShortTransactionUI(
    transactions: List<TransactionListItem>,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        if (isLoading) {
            PfsCircularProgressIndicatorBox()
        }
        Column(modifier = Modifier.fillMaxWidth()) {
            transactions.forEach { transaction ->
                when (transaction) {
                    is TransactionExpenseModel -> ExpenseItem(transaction)
                    is TransactionIncomeModel -> IncomeItem(transaction)
                }
            }
        }
    }
}

@Composable
private fun IncomeItem(
    income: TransactionIncomeModel,
    modifier: Modifier = Modifier
) {
    TransactionItem(
        date = income.date,
        description = income.description,
        amount = income.amount,
        amountColor = Green40,
        modifier = modifier
    )
}

@Composable
private fun ExpenseItem(
    expense: TransactionExpenseModel,
    modifier: Modifier = Modifier
) {
    TransactionItem(
        date = expense.date,
        description = expense.description,
        amount = expense.amount,
        amountColor = Red40,
        modifier = modifier
    )
}

@Composable
private fun TransactionItem(
    date: String,
    description: String,
    amount: String,
    amountColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.transactionDefault()
    ) {
        Text(
            text = date,
            maxLines = 1,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(end = 6.dp)
                .alpha(0.6f)
        )
        Text(
            text = description,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = amount,
            maxLines = 1,
            color = amountColor,
            modifier = Modifier.padding(start = 6.dp)
        )
    }
}

private fun Modifier.transactionDefault() =
    fillMaxWidth()
        .padding(vertical = 6.dp, horizontal = 12.dp)