package com.victorbrndls.pfs.ui.summary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.designsystem.component.progress.PfsHorizontalProgressIndicator

@Composable
fun SummaryComponent(
    modifier: Modifier = Modifier,
    viewModel: SummaryViewModel = hiltViewModel(),
) {
    SummaryUI(
        items = viewModel.items,
        isLoading = viewModel.isLoading,
        modifier = modifier
    )
}

@Composable
private fun SummaryUI(
    items: List<SummaryItem>,
    isLoading: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        val localDensity = LocalDensity.current

        var rowHeight by remember { mutableStateOf(0.dp) }
        val rowHeightMeasurement = remember {
            Modifier.onGloballyPositioned {
                rowHeight = with(localDensity) { it.size.height.toDp() }
            }
        }

        if (isLoading) {
            PfsHorizontalProgressIndicator()
        }
        LazyRow {
            item(key = "header") {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .padding(horizontal = 6.dp)
                        .width(IntrinsicSize.Max)
                ) {
                    Spacer(modifier = Modifier.height(rowHeight))

                    Text(text = stringResource(id = R.string.title_summary_income))
                    Text(text = stringResource(id = R.string.title_summary_expenses))
                    Text(text = stringResource(id = R.string.title_summary_net_savings))
                    Text(text = stringResource(id = R.string.title_summary_ending_balance))
                }
            }

            itemsIndexed(items) { idx, item ->
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .padding(horizontal = 6.dp)
                        .width(IntrinsicSize.Max)
                ) {
                    Text(
                        text = item.date,
                        textAlign = TextAlign.End,
                        modifier = if (idx == 0) rowHeightMeasurement else Modifier
                    )

                    Divider()

                    Text(text = item.income)
                    Text(text = item.expenses)
                    Divider()
                    Text(text = item.netSavings)
                    Text(text = item.endingBalance)
                }
            }
        }
    }
}


