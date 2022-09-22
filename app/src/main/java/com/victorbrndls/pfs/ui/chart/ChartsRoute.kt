package com.victorbrndls.pfs.ui.chart

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.chart.expense_category.ExpenseByCategoryChart
import com.victorbrndls.pfs.ui.chart.income_netsavings.IncomeAndNetSavingsChart
import com.victorbrndls.pfs.ui.chart.savingsrate.SavingsRateChart
import com.victorbrndls.pfs.ui.designsystem.component.PfsTopAppBar
import com.victorbrndls.pfs.ui.ktx.landscapeRatio

@Composable
fun ChartsRoute(navController: NavController) {
    ChartsScreen(
        onNavigateUp = { navController.navigateUp() },
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ChartsScreen(
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            PfsTopAppBar(
                titleRes = R.string.title_charts,
                onNavigationClick = { onNavigateUp() })
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            Column(modifier = modifier) {
                IncomeAndNetSavingsChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .landscapeRatio()
                )

                SavingsRateChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .landscapeRatio()
                )

                ExpenseByCategoryChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .landscapeRatio()
                )
            }
        }
    }
}