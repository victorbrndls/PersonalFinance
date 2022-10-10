package com.victorbrndls.pfs.core.income.repository

import com.victorbrndls.pfs.core.income.dto.EditIncomeData
import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.infrastructure.date.DateRange
import kotlinx.coroutines.flow.Flow

interface IncomeRepository {
    suspend fun getAll(): List<Income>
    suspend fun observe(range: DateRange): Flow<List<Income>>

    suspend fun save(income: EditIncomeData)
}