package com.victorbrndls.pfs.infrastructure.date

import java.util.*

private val utc = TimeZone.getTimeZone("UTC")

internal fun Date.toLocalMidnight(): Date {
    return Calendar.getInstance(utc).apply {
        time = this@toLocalMidnight
        timeZone = TimeZone.getDefault()
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time
}

internal fun Date.toMonthFirst(): Date {
    return Calendar.getInstance(utc).apply {
        time = this@toMonthFirst
        timeZone = TimeZone.getDefault()
        set(Calendar.DATE, 1)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time
}

internal fun Date.toMonthLast(): Date {
    return Calendar.getInstance(utc).apply {
        time = this@toMonthLast.toMonthFirst()
        timeZone = TimeZone.getDefault()
        add(Calendar.MONTH, 1)
        add(Calendar.MILLISECOND, -1)
    }.time
}