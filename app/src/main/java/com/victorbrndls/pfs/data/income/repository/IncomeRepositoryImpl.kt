package com.victorbrndls.pfs.data.income.repository

import com.victorbrndls.pfs.core.income.dto.EditIncomeData
import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.core.income.repository.IncomeRepository
import com.victorbrndls.pfs.infrastructure.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.random.Random

class IncomeRepositoryImpl @Inject constructor() : IncomeRepository {

    private val incomes = MutableStateFlow(emptyList<Income>())

    override suspend fun getAll(): List<Income> {
        return incomes.value
    }

    override suspend fun observe(): Flow<List<Income>> {
        return incomes
    }

    override suspend fun save(expense: EditIncomeData) {
        incomes.value += Income(
            Random.nextLong(),
            expense.description,
            expense.date,
            expense.amount
        ).also {
            Logger.d("Saved income | $it")
        }
    }
}