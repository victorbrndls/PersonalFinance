package com.victorbrndls.pfs.infrastructure.date

import java.util.*

private val utc = TimeZone.getTimeZone("UTC")

internal fun Date.isInRange(range: DateRange): Boolean {
    val thisTime = this.time
    val startTime = range.start.time
    val endTime = range.end?.time

    if (thisTime < startTime) return false
    if (endTime != null && thisTime > endTime) return false
    return true
}

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
        add(Calendar.MONTH, 1)
        add(Calendar.MILLISECOND, -1)
    }.time
}

internal fun Date.addMonths(months: Int): Date {
    return Calendar.getInstance(utc).apply {
        time = this@addMonths
        add(Calendar.MONTH, months)
    }.time
}