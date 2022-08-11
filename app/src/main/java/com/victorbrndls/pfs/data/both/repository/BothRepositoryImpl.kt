package com.victorbrndls.pfs.data.both.repository

import com.victorbrndls.pfs.core.both.entity.Both
import com.victorbrndls.pfs.core.both.repository.BothRepository
import com.victorbrndls.pfs.core.expense.usecase.GetExpensesUseCase
import com.victorbrndls.pfs.core.income.usecase.GetIncomesUseCase
import javax.inject.Inject

class BothRepositoryImpl @Inject constructor(
    private val getIncomesUseCase: GetIncomesUseCase,
    private val getExpensesUseCase: GetExpensesUseCase
) : BothRepository {

    override suspend fun getAll(): Both {
        return Both(getIncomesUseCase.getAll(), getExpensesUseCase.getAll())
    }

}
