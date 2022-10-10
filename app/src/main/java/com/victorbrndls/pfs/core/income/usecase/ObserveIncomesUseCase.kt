package com.victorbrndls.pfs.core.income.usecase

import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.core.income.repository.IncomeRepository
import com.victorbrndls.pfs.infrastructure.date.DateRange
import com.victorbrndls.pfs.infrastructure.date.last12Months
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ObserveIncomesUseCase {
    suspend fun observe(range: DateRange = last12Months()): Flow<List<Income>>
}

class ObserveIncomesUseCaseImpl @Inject constructor(
    private val incomeRepository: IncomeRepository
) : ObserveIncomesUseCase {
    override suspend fun observe(range: DateRange): Flow<List<Income>> {
        return incomeRepository.observe(range)
    }
}