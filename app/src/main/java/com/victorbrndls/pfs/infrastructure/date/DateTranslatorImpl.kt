package com.victorbrndls.pfs.infrastructure.date

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateTranslatorImpl @Inject constructor() : DateTranslator {

    private val utc = TimeZone.getTimeZone("UTC")

    @SuppressLint("SimpleDateFormat")
    private val yyyyMMddFormatter = SimpleDateFormat("dd/MM/yyyy").apply {
        timeZone = utc
    }

    @SuppressLint("SimpleDateFormat")
    private val yyMMMFormatter = SimpleDateFormat("MMM/yy").apply {
        timeZone = utc
    }

    override fun parse(date: String): Date? {
        return yyyyMMddFormatter.parse(date)
    }

    override fun formatYYYYMMDD(date: Date): String {
        return yyyyMMddFormatter.format(date)
    }

    override fun formatYYMMM(date: Date): String {
        return yyMMMFormatter.format(date)
    }

    override fun toLocalMidnight(date: Date): Date {
        return Calendar.getInstance(utc).apply {
            time = date
            timeZone = TimeZone.getDefault()
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time
    }

    override fun toMonthFirst(date: Date): Date {
        return Calendar.getInstance(utc).apply {
            time = date
            timeZone = TimeZone.getDefault()
            set(Calendar.DATE, 1)
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.time
    }
}