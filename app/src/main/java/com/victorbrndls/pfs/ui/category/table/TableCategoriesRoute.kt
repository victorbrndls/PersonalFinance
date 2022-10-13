package com.victorbrndls.pfs.ui.category.table

import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.designsystem.component.PfsTopAppBar
import com.victorbrndls.pfs.ui.designsystem.component.progress.PfsHorizontalProgressIndicator
import com.victorbrndls.pfs.ui.designsystem.theme.LightGreen40
import com.victorbrndls.pfs.ui.designsystem.theme.Red40
import com.victorbrndls.pfs.ui.designsystem.theme.Yellow40

@Composable
fun TableCategoriesRoute(
    navController: NavController,
    viewModel: TableCategoriesViewModel = hiltViewModel()
) {
    TableCategoriesScreen(
        categories = viewModel.categoryItems,
        months = viewModel.months,
        categoriesByMonth = viewModel.monthlyItems,
        isLoading = viewModel.isLoading,
        onNavigateUp = { navController.popBackStack() }
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun TableCategoriesScreen(
    categories: List<CategoryItem>,
    months: List<String>,
    categoriesByMonth: List<CategoryMonth>,
    isLoading: Boolean,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    val leftColumnWidth = 120.dp
    val rowWidth = 64.dp
    val rowHeight = 40.dp

    val verticalScroll = rememberScrollState()
    val horizontalScroll = rememberScrollState()

    Scaffold(
        topBar = {
            PfsTopAppBar(
                titleRes = R.string.title_list_categories,
                onNavigationClick = { onNavigateUp() })
        },
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            if (isLoading) {
                PfsHorizontalProgressIndicator()
            }

            Column {
                // Months
                Row {
                    Box(modifier = Modifier.width(leftColumnWidth))
                    Row(
                        modifier = Modifier.horizontalScroll(horizontalScroll)
                    ) {
                        months.forEach { month ->
                            MonthBox(
                                month = month,
                                modifier = Modifier.width(64.dp)
                            )
                        }
                    }
                }

                Row {
                    // Category Labels
                    Column(
                        modifier = Modifier
                            .width(leftColumnWidth)
                            .verticalScroll(verticalScroll)
                    ) {
                        categories.forEach { category ->
                            CategoryBox(
                                item = category,
                                modifier = Modifier.height(rowHeight)
                            )
                        }
                    }

                    // Expenses/Category/Month
                    Column(
                        modifier = Modifier
                            .verticalScroll(verticalScroll)
                            .horizontalScroll(horizontalScroll)
                    ) {
                        categoriesByMonth.forEach {
                            TableRow(
                                items = it.items,
                                itemModifier = Modifier
                                    .width(rowWidth)
                                    .height(rowHeight)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryBox(
    item: CategoryItem,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = item.label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun MonthBox(
    month: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = month,
        maxLines = 1,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

@Composable
private fun TableRow(
    items: List<CategoryTransactionItem>,
    modifier: Modifier = Modifier,
    itemModifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        items.forEach { item ->
            TableItem(
                item = item,
                modifier = itemModifier
            )
        }
    }
}

@Composable
private fun TableItem(
    item: CategoryTransactionItem,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(item.percentage.percentageToColor())
    ) {
        Text(
            text = item.amount,
            maxLines = 1
        )
    }
}

private fun Int.percentageToColor() = when {
    this < 90 -> LightGreen40
    this < 99 -> Yellow40
    else -> Red40
}