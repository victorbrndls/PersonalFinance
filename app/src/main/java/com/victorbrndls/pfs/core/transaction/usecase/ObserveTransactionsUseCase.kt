package com.victorbrndls.pfs.core.transaction.usecase

import com.victorbrndls.pfs.core.transaction.entity.Transaction
import com.victorbrndls.pfs.core.transaction.repository.TransactionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ObserveTransactionsUseCase {
    suspend fun observe(): Flow<List<Transaction>>
}

class ObserveTransactionsUseCaseImpl @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ObserveTransactionsUseCase {
    override suspend fun observe(): Flow<List<Transaction>> {
        return transactionRepository.observe()
    }
}