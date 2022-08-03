package com.victorbrndls.pfs.some

import java.util.*

/**
 * Parses and formats dates depending on the user location
 */
interface DateTranslator {
    fun parse(date: String): Date?
    fun format(date: Date): String
}