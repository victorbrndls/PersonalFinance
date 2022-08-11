package com.victorbrndls.pfs.core.income.usecase

import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.core.income.repository.IncomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ObserveIncomesUseCase {
    suspend fun observe(): Flow<List<Income>>
}

class ObserveIncomesUseCaseImpl @Inject constructor(
    private val incomeRepository: IncomeRepository
) : ObserveIncomesUseCase {
    override suspend fun observe(): Flow<List<Income>> {
        return incomeRepository.observe()
    }
}