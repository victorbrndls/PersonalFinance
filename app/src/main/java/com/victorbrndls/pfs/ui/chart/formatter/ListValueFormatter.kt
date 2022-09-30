package com.victorbrndls.pfs.ui.chart.formatter

import com.github.mikephil.charting.formatter.ValueFormatter

class ListValueFormatter<T>(
    val list: List<T>,
    val converter: (T) -> String
) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val item = list.getOrNull(value.toInt()) ?: return ""
        return converter(item)
    }
}