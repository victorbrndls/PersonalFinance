package com.victorbrndls.pfs.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.both.list.ListBothComponent
import com.victorbrndls.pfs.ui.chart.income_netsavings.IncomeAndSavingsRateChart
import com.victorbrndls.pfs.ui.designsystem.component.ExpandableFloatingActionButton
import com.victorbrndls.pfs.ui.designsystem.component.IconTextButton
import com.victorbrndls.pfs.ui.designsystem.theme.White
import com.victorbrndls.pfs.ui.route.Routes
import com.victorbrndls.pfs.ui.summary.SummaryComponent

@Composable
fun HomeRoute(
    navController: NavController,
) {
    HomeScreen(
        onNavigateToListCategories = { navController.navigate(Routes.LIST_CATEGORIES) },
        onNavigateToAddExpense = { navController.navigate(Routes.EDIT_EXPENSE) },
        onNavigateToAddIncome = { navController.navigate(Routes.EDIT_INCOME) },
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun HomeScreen(
    onNavigateToListCategories: () -> Unit,
    onNavigateToAddExpense: () -> Unit,
    onNavigateToAddIncome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        floatingActionButton = {
            HomeFab(
                onNavigateToAddExpense = onNavigateToAddExpense,
                onNavigateToAddIncome = onNavigateToAddIncome,
            )
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))

                SummaryComponent()

                Spacer(modifier = Modifier.height(16.dp))

                IncomeAndSavingsRateChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9.toFloat())
                )

                Spacer(modifier = Modifier.height(16.dp))

                ListBothComponent(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun HomeFab(
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
