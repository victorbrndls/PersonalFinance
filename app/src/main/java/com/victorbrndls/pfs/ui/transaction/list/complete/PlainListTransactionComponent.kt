package com.victorbrndls.pfs.ui.transaction.list.complete

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorbrndls.pfs.ui.designsystem.component.progress.PfsHorizontalProgressIndicator

@Composable
fun PlainListTransactionComponent(
    modifier: Modifier = Modifier,
    viewModel: ListTransactionViewModel = hiltViewModel(),
) {
    PlainListTransactionUI(
        transactions = viewModel.transactions.value,
        isLoading = viewModel.isLoading,
        modifier = modifier
    )
}

@Composable
private fun PlainListTransactionUI(
    transactions: List<TransactionListItem>,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Box {
        if (isLoading) {
            PfsHorizontalProgressIndicator()
        }
        ListTransactionComponent(
            transactions = transactions,
            modifier = modifier
        )
    }
}