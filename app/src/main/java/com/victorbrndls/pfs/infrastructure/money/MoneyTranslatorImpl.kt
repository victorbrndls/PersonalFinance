package com.victorbrndls.pfs.infrastructure.money

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import javax.inject.Inject

class MoneyTranslatorImpl @Inject constructor() : MoneyTranslator {

    private val numberFormatter = NumberFormat.getCurrencyInstance()

    override fun parse(amount: String): BigDecimal? {
        return null
    }

    override fun format(amount: BigDecimal): String {
        amount.setScale(2, RoundingMode.HALF_DOWN)
        return numberFormatter.format(amount)
    }
}