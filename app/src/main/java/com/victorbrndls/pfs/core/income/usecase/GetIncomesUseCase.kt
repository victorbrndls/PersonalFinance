package com.victorbrndls.pfs.core.income.usecase

import com.victorbrndls.pfs.core.income.entity.Income
import com.victorbrndls.pfs.core.income.repository.IncomeRepository
import javax.inject.Inject

interface GetIncomesUseCase {
    suspend fun getAll(): List<Income>
}

class GetIncomesUseCaseImpl @Inject constructor(
    private val incomeRepository: IncomeRepository
) : GetIncomesUseCase {
    override suspend fun getAll(): List<Income> {
        return incomeRepository.getAll()
    }
}