package com.victorbrndls.pfs.infrastructure.date

internal fun DateRange.addMonths(months: Int): DateRange {
    if (months == 0) return this
    return copy(
        start = start.addMonths(months).toMonthFirst(),
        end = end?.addMonths(months)?.toMonthLast()
    )
}