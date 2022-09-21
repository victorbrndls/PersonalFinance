package com.victorbrndls.pfs.ui.ktx

import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.LargeValueFormatter

fun LineChart.defaultStyle() {
    axisLeft.axisMinimum = 0f
    axisLeft.valueFormatter = LargeValueFormatter()
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
    legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
}