package com.victorbrndls.pfs.ui.chart.formatter

import com.github.mikephil.charting.formatter.ValueFormatter

class ListValueFormatter<T>(
    val list: List<T>,
    val converter: (T) -> String
) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        if (value < 0f) return ""
        return converter(list[value.toInt()])
    }
}