package com.victorbrndls.pfs.infrastructure.date

import java.util.*

/**
 * Parses and formats dates depending on the user location
 */
interface DateTranslator {
    fun parse(date: String): Date?

    /** 02/04/2010 */
    fun formatYYYYMMDD(date: Date): String

    /** Jan/22 */
    fun formatYYMMM(date: Date): String

    /** Feb/02 */
    fun formatMMMDD(date: Date): String

    fun toLocalMidnight(date: Date): Date
    fun toMonthFirst(date: Date): Date
}