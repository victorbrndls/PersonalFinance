package com.victorbrndls.pfs.data.income.repository

import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.core.income.dto.EditIncomeData
import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.core.income.repository.IncomeRepository
import com.victorbrndls.pfs.data.category.repository.fakeCategories
import com.victorbrndls.pfs.infrastructure.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextLong

class IncomeRepositoryImpl @Inject constructor() : IncomeRepository {

    private val incomes = MutableStateFlow(fakeIncomes)

    override suspend fun getAll(): List<Income> {
        return incomes.value
    }

    override suspend fun observe(): Flow<List<Income>> {
        return incomes
    }

    override suspend fun save(income: EditIncomeData) {
        incomes.value += Income(
            Random.nextLong(),
            income.description,
            income.category,
            income.date,
            income.amount
        ).also {
            Logger.d("Saved income | $it")
        }
    }
}

private const val ONE_MONTH_MILLIS = 2629800000

private val fakeIncomes = (0..10).map { c ->
    Income(
        id = Random.nextLong(),
        description = "Salary",
        category = fakeCategories.filter { it.type == CategoryType.INCOME }.random(),
        date = Date(Date().time - c * ONE_MONTH_MILLIS),
        amount = BigDecimal.valueOf(Random.nextLong(6000, 9000))
    )
}