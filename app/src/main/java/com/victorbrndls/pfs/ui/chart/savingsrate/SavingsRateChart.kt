package com.victorbrndls.pfs.ui.chart.savingsrate

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
import com.github.mikephil.charting.formatter.PercentFormatter
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.chart.ListValueFormatter
import com.victorbrndls.pfs.ui.designsystem.theme.Blue40
import com.victorbrndls.pfs.ui.ktx.defaultStyle

@Composable
fun SavingsRateChart(
    modifier: Modifier = Modifier,
    viewModel: SavingsRateChartViewModel = hiltViewModel()
) {
    HorizontalSavingsRateChart(
        entries = viewModel.entries,
        modifier = modifier
    )
}

@Composable
private fun HorizontalSavingsRateChart(
    entries: List<SavingsRateChartEntry>,
    modifier: Modifier = Modifier
) {
    val savingsRateColor = Blue40.toArgb()
    val valuesToShow = 6f

    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                defaultStyle()
                axisLeft.valueFormatter = PercentFormatter()
            }
        }, update = { view ->
            val context = view.context

            val savingsRate = LineDataSet(entries.mapIndexed { idx, entry ->
                Entry(idx.toFloat(), entry.rate)
            }, context.getString(R.string.chart_savings_rate)).apply {
                defaultStyle()
                color = savingsRateColor
            }

            view.xAxis.valueFormatter = ListValueFormatter(entries, converter = { it.date })

            LineData(savingsRate).apply {
                defaultStyle()
                setValueFormatter(PercentFormatter())
            }.also { data ->
                view.data = data
                view.zoom(entries.size / valuesToShow, 1f, 0f, 0f)
                view.moveViewToX(data.xMax)
            }
        },
        modifier = modifier
    )
}