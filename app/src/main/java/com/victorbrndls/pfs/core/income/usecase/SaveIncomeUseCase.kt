package com.victorbrndls.pfs.core.income.usecase

import com.victorbrndls.pfs.core.income.dto.EditIncomeData
import com.victorbrndls.pfs.core.income.repository.IncomeRepository
import javax.inject.Inject

interface SaveIncomeUseCase {
    suspend fun save(income: EditIncomeData)
}

class SaveIncomeUseCaseImpl @Inject constructor(
    private val incomeRepository: IncomeRepository
) : SaveIncomeUseCase {
    override suspend fun save(income: EditIncomeData) {
        incomeRepository.save(income)
    }
}