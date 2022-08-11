package com.victorbrndls.pfs.ui.both.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorbrndls.pfs.ui.designsystem.component.PfsHorizontalProgressBar
import com.victorbrndls.pfs.ui.designsystem.theme.Black10
import com.victorbrndls.pfs.ui.designsystem.theme.Green40
import com.victorbrndls.pfs.ui.designsystem.theme.Red40

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
    both: List<BothListItem>,
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
                    is BothDate -> DateHeader(item.date)
                    is BothExpenseModel -> ExpenseItem(item)
                    is BothIncomeModel -> IncomeItem(item)
                }
            }
        }
    }
}

@Composable
fun DateHeader(date: String) {
    Surface(
        color = Black10,
        modifier = Modifier.fillMaxWidth()
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
    income: BothIncomeModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 12.dp)
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
    expense: BothExpenseModel,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 12.dp)
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