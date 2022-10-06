package com.victorbrndls.pfs.infrastructure.date

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import java.util.*


internal class DateExtKtTest {

    @Test
    fun addMonthPositiveValue() {
        val start = Date(1601510400000) // 2020 Oct 01 00:00:00 UTC
        val oneMonthLater = Date(1604188800000) // 2020 Nov 01 00:00:00 UTC

        val result = start.addMonths(1)

        assertThat(result.time).isEqualTo(oneMonthLater.time)
    }

    @Test
    fun addMonthNegativeValue() {
        val start = Date(1604188800000) // 2020 Nov 01 00:00:00 UTC
        val oneMonthLater = Date(1601510400000) // 2020 Oct 01 00:00:00 UTC

        val result = start.addMonths(-1)

        assertThat(result.time).isEqualTo(oneMonthLater.time)
    }


}