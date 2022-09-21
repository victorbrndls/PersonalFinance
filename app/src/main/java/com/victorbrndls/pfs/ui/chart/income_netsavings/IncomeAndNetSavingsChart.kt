package com.victorbrndls.pfs.ui.chart.income_netsavings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.LargeValueFormatter
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.chart.ListValueFormatter
import com.victorbrndls.pfs.ui.designsystem.theme.Green40
import com.victorbrndls.pfs.ui.designsystem.theme.Purple40
import com.victorbrndls.pfs.ui.ktx.defaultStyle

@Composable
fun IncomeAndNetSavingsChart(
    modifier: Modifier = Modifier,
    viewModel: IncomeAndNetSavingsChartViewModel = hiltViewModel()
) {
    HorizontalIncomeAndNetSavingsChart(
        entries = viewModel.entries,
        modifier = modifier
    )
}

@Composable
private fun HorizontalIncomeAndNetSavingsChart(
    entries: List<IncomeAndNetSavingsChartEntry>,
    modifier: Modifier = Modifier
) {
    val incomeColor = Green40.toArgb()
    val netSavingsColor = Purple40.toArgb()
    val valuesToShow = 6f

    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                defaultStyle()
                axisLeft.valueFormatter = LargeValueFormatter()
            }
        }, update = { view ->
            val context = view.context

            val income = LineDataSet(entries.mapIndexed { idx, entry ->
                Entry(idx.toFloat(), entry.income)
            }, context.getString(R.string.chart_income)).apply {
                defaultStyle()
                color = incomeColor
            }
            val netSavings = LineDataSet(entries.mapIndexed { idx, entry ->
                Entry(idx.toFloat(), entry.netSavings)
            }, context.getString(R.string.chart_net_savings)).apply {
                defaultStyle()
                color = netSavingsColor
            }

            view.xAxis.valueFormatter = ListValueFormatter(entries, converter = { it.date })

            LineData(income, netSavings).apply {
                defaultStyle()
                setValueFormatter(LargeValueFormatter())
            }.also { data ->
                view.data = data
                view.zoom(entries.size / valuesToShow, 1f, 0f, 0f)
                view.moveViewToX(data.xMax)
            }
        },
        modifier = modifier
    )
}