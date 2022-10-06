package com.victorbrndls.pfs.ui.transaction.list.complete

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.ui.designsystem.component.progress.PfsHorizontalProgressIndicator
import com.victorbrndls.pfs.ui.transaction.filter.BarPeriodFilter
import com.victorbrndls.pfs.ui.transaction.filter.BarPeriodFilterState
import com.victorbrndls.pfs.ui.transaction.filter.CategoryTypeFilter
import com.victorbrndls.pfs.ui.transaction.filter.rememberBarPeriodFilterState

@Composable
fun FilterableListTransactionComponent(
    modifier: Modifier = Modifier,
    viewModel: ListTransactionViewModel = hiltViewModel(),
) {
    val periodFilterState = rememberBarPeriodFilterState(
        items = viewModel.periodFilter
    )
    LaunchedEffect(periodFilterState) {
        snapshotFlow { periodFilterState.items.value }.collect { viewModel.updatePeriod(it) }
    }

    FilterableListTransactionUI(
        transactions = viewModel.transactions.value,
        isLoading = viewModel.isLoading,
        filteredCategoryType = viewModel.categoryType,
        onCategoryTypeSelected = { viewModel.updateCategoryType(it) },
        periodFilterState = periodFilterState,
        modifier = modifier
    )
}

@Composable
private fun FilterableListTransactionUI(
    transactions: List<TransactionListItem>,
    isLoading: Boolean,
    filteredCategoryType: CategoryType?,
    onCategoryTypeSelected: (CategoryType) -> Unit,
    periodFilterState: BarPeriodFilterState,
    modifier: Modifier = Modifier,
) {
    Column {
        CategoryTypeFilter(
            selected = filteredCategoryType,
            onSelected = onCategoryTypeSelected
        )
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            BarPeriodFilter(
                state = periodFilterState,
                modifier = Modifier.height(120.dp)
            )
            if (isLoading) {
                PfsHorizontalProgressIndicator()
            }
        }
        ListTransactionComponent(
            transactions = transactions,
            modifier = modifier
        )
    }
}