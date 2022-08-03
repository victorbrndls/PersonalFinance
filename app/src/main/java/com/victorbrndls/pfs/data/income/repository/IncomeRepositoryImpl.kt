package com.victorbrndls.pfs.data.income.repository

import com.victorbrndls.pfs.core.income.dto.EditIncomeData
import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.core.income.repository.IncomeRepository
import com.victorbrndls.pfs.infrastructure.logger.Logger
import javax.inject.Inject
import kotlin.random.Random

class IncomeRepositoryImpl @Inject constructor() : IncomeRepository {
    private var expenses = mutableListOf<Income>()

    override suspend fun getAll(): List<Income> {
        return expenses
    }

    override suspend fun save(expense: EditIncomeData) {
        expenses += Income(
            Random.nextLong(),
            expense.description,
            expense.date,
            expense.amount
        ).also {
            Logger.d("Saved income | $it")
        }
    }
}