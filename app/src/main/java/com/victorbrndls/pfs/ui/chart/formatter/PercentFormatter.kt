package com.victorbrndls.pfs.ui.chart.formatter

import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat

class PercentFormatter : ValueFormatter() {

    private val format = DecimalFormat("###")

    override fun getFormattedValue(value: Float): String {
        if (value < 0f) return ""
        return getPercent(value) + "%"
    }

    private fun getPercent(value: Float): String {
        return format.format(value)
    }
}