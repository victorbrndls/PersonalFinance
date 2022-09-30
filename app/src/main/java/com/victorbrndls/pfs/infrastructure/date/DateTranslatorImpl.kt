package com.victorbrndls.pfs.infrastructure.date

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@SuppressLint("SimpleDateFormat")
class DateTranslatorImpl @Inject constructor() : DateTranslator {

    private val utc = TimeZone.getTimeZone("UTC")

    private val yyyyMMddFormatter = SimpleDateFormat("dd/MM/yyyy").apply {
        timeZone = utc
    }

    private val yyMMMFormatter = SimpleDateFormat("MMM/yy").apply {
        timeZone = utc
    }

    private val mmmDDFormatter = SimpleDateFormat("MMM dd").apply {
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

    override fun formatMMMDD(date: Date): String {
        return mmmDDFormatter.format(date)
    }

}