package com.victorbrndls.pfs.infrastructure.date

import java.util.*

data class DateRange(
    val start: Date,
    val end: Date?
)

private const val TWELVE_MONTHS_MILLIS = 31557600000L

internal fun rangeLast12Months() = DateRange(
    start = Date(Date().time - TWELVE_MONTHS_MILLIS),
    end = Date()
)

internal fun currentMonth() = DateRange(
    start = Date().toMonthFirst(),
    end = Date().toMonthLast()
)