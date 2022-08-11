package com.victorbrndls.pfs.ui.both.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorbrndls.pfs.ui.designsystem.component.PfsHorizontalProgressBar
import com.victorbrndls.pfs.core.expense.entity.Expense as ExpenseEntity
import com.victorbrndls.pfs.core.income.entity.Income as IncomeEntity

@Composable
fun ListBothComponent(
    modifier: Modifier = Modifier,
    viewModel: ListBothViewModel = hiltViewModel(),
) {
    ListBothUI(
        both = viewModel.both,
        isLoading = viewModel.isLoading,
        modifier = modifier
    )
}

@Composable
private fun ListBothUI(
    both: List<BothModel>,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        if (isLoading) {
            PfsHorizontalProgressBar()
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(
                items = both,
                key = { it.id },
                contentType = { it::class }
            ) { item ->
                when (item) {
                    is BothModel.ExpenseModel -> ExpenseItem(expense = item.expense)
                    is BothModel.IncomeModel -> IncomeItem(income = item.income)
                }
            }
        }
    }
}

@Composable
private fun IncomeItem(
    income: IncomeEntity,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 12.dp)
    ) {
        Text(
            text = income.description,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun ExpenseItem(
    expense: ExpenseEntity,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 12.dp)
    ) {
        Text(
            text = expense.description,
            fontSize = 16.sp
        )
    }
}