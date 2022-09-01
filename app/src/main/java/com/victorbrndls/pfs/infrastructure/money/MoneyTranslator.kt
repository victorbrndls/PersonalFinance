package com.victorbrndls.pfs.infrastructure.money

import java.math.BigDecimal

/**
 * Parses and formats money depending on the user location
 */
interface MoneyTranslator {
    fun parse(amount: String): BigDecimal?
    fun format(amount: BigDecimal): String
    fun formatWhole(amount: BigDecimal): String
}