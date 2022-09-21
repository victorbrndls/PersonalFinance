package com.victorbrndls.pfs.ui.ktx

import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.LargeValueFormatter

fun LineData.defaultStyle() {
    setValueTextSize(14f)
    setValueFormatter(LargeValueFormatter())
}