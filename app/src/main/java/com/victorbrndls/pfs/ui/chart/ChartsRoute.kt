package com.victorbrndls.pfs.ui.chart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.victorbrndls.pfs.ui.chart.expense_category.ExpenseByCategoryChart
import com.victorbrndls.pfs.ui.chart.income_netsavings.IncomeAndNetSavingsChart
import com.victorbrndls.pfs.ui.chart.savingsrate.SavingsRateChart
import com.victorbrndls.pfs.ui.ktx.landscapeRatio

@Composable
fun ChartsRoute() {
    ChartsScreen()
}

@Composable
private fun ChartsScreen(
    modifier: Modifier = Modifier
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