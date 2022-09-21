package com.victorbrndls.pfs.ui.ktx

import com.github.mikephil.charting.data.LineDataSet

fun LineDataSet.defaultStyle() {
    lineWidth = 3f
    setDrawCircles(false)
    mode = LineDataSet.Mode.CUBIC_BEZIER
}