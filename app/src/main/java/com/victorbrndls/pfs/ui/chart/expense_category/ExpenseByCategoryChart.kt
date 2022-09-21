package com.victorbrndls.pfs.ui.chart.expense_category

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.victorbrndls.pfs.ui.chart.AmountValueFormatter
import com.victorbrndls.pfs.ui.chart.ListValueFormatter
import com.victorbrndls.pfs.ui.designsystem.theme.Blue40
import com.victorbrndls.pfs.ui.designsystem.theme.Green40
import com.victorbrndls.pfs.ui.designsystem.theme.Purple40
import com.victorbrndls.pfs.ui.designsystem.theme.Red40

@Composable
fun ExpenseByCategoryChart(
    modifier: Modifier = Modifier,
    viewModel: ExpenseByCategoryChartViewModel = hiltViewModel()
) {
    HorizontalExpenseByCategoryChart(
        entries = viewModel.entries,
        modifier = modifier
    )
}

@Composable
private fun HorizontalExpenseByCategoryChart(
    entries: List<ExpenseByCategoryChartEntry>,
    modifier: Modifier = Modifier
) {
    val colors = listOf(
        Green40.toArgb(),
        Purple40.toArgb(),
        Blue40.toArgb(),
        Red40.toArgb(),
    )
    val valuesToShow = 6f

    AndroidView(
        factory = { context ->
            BarChart(context).apply {
                //defaultStyle()
                axisLeft.axisMinimum = 0f
                axisLeft.valueFormatter = AmountValueFormatter(short = true)
                axisRight.isEnabled = false

                xAxis.position = XAxis.XAxisPosition.BOTTOM
                xAxis.setDrawGridLines(false)
                xAxis.textSize = 11f

                isHighlightPerTapEnabled = false
                isHighlightPerDragEnabled = false
                isDoubleTapToZoomEnabled = false
                setPinchZoom(false)
                isDragYEnabled = false

                description = Description().apply { isEnabled = false }

                legend.isEnabled = false
            }
        }, update = { view ->
            val expensesByDate = entries.mapIndexed { idx, entry ->
                var previousAmount = 0f

                BarDataSet(
                    entry.categoryEntries.map { catEntry ->
                        previousAmount += catEntry.amount
                        BarEntry(idx.toFloat(), previousAmount)
                    }.reversed(), ""
                ).apply {
                    //defaultStyle()
                    setColors(colors)
                    setDrawValues(false)
                }
            }

            view.xAxis.valueFormatter = ListValueFormatter(entries, converter = { it.date })

            BarData(expensesByDate).apply {
                // defaultStyle()
                setValueFormatter(AmountValueFormatter(short = false))
            }.also { data ->
                view.data = data
                view.zoom(entries.size / valuesToShow, 1f, 0f, 0f)
                view.moveViewToX(data.xMax)
            }
        },
        modifier = modifier
    )
}