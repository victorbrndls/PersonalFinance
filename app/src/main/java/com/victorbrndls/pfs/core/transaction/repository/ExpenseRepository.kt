package com.victorbrndls.pfs.core.transaction.repository

import com.victorbrndls.pfs.core.transaction.entity.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {
    suspend fun observe(): Flow<List<Transaction>>
}