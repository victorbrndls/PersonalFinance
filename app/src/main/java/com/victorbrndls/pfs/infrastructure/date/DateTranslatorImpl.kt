package com.victorbrndls.pfs.infrastructure.date

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateTranslatorImpl @Inject constructor() : DateTranslator {

    private val utc = TimeZone.getTimeZone("UTC")

    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat("dd/MM/yyyy").apply {
        timeZone = utc
    }

    override fun parse(date: String): Date? {
        return formatter.parse(date)
    }

    override fun format(date: Date): String {
        return formatter.format(date)
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
}