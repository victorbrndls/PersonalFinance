package com.victorbrndls.pfs.ui.chart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Column(
                modifier = modifier.verticalScroll(state = rememberScrollState())
            ) {
                ChartTitle(text = stringResource(id = R.string.chart_title_income_and_net_savings))
                IncomeAndNetSavingsChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .landscapeRatio()
                        .padding(bottom = 12.dp)
                )

                ChartTitle(text = stringResource(id = R.string.chart_title_savings_rate))
                SavingsRateChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .landscapeRatio()
                        .padding(bottom = 12.dp)
                )

                ExpenseByCategoryChart(
                    modifier = Modifier
                        .fillMaxWidth()
                        .landscapeRatio()
                        .padding(bottom = 12.dp)
                )
            }
        }
    }
}

@Composable
private fun ChartTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium,
        modifier = modifier.padding(horizontal = 12.dp)
    )
}