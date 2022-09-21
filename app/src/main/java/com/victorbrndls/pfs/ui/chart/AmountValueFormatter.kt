package com.victorbrndls.pfs.ui.chart

import com.github.mikephil.charting.formatter.LargeValueFormatter

class AmountValueFormatter(short: Boolean) : LargeValueFormatter() {
    init {
        if (short) setMaxLength(3)
    }
}