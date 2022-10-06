package com.victorbrndls.pfs.infrastructure.date

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.util.*

private val RANGE = DateRange(
    start = Date(1601510400000), // 2020 Oct 01 00:00:00 UTC
    end = Date(1604188799999) // 2020 Oct 31 23:59:59 UTC
)

internal class DateRangeExtKtTest {

    @Test
    fun addDateRangePositiveValue() {
        val start = Date(1604188800000) // 2020 Nov 01 00:00:00 UTC
        val end = Date(1606780799999) // 2020 Nov 30 23:59:59 UTC

        val result = RANGE.addMonths(1)

        assertThat(result.start).isEqualTo(start)
        assertThat(result.end).isEqualTo(end)
    }

    @Test
    fun addDateRangeNegativeValue() {
        val start = Date(1598918400000) // 2020 Sep 01 00:00:00 UTC
        val end = Date(1601510399999) // 2020 Sep 30 23:59:59 UTC

        val result = RANGE.addMonths(-1)

        assertThat(result.start).isEqualTo(start)
        assertThat(result.end).isEqualTo(end)
    }

}