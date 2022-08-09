package com.victorbrndls.pfs.infrastructure.date

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DateTranslatorImpl @Inject constructor() : DateTranslator {

    @SuppressLint("SimpleDateFormat")
    private val formatter = SimpleDateFormat("dd/MM/yyyy").apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }

    override fun parse(date: String): Date? {
        return formatter.parse(date)
    }

    override fun format(date: Date): String {
        return formatter.format(date)
    }
}