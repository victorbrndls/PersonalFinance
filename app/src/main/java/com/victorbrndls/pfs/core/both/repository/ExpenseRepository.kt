package com.victorbrndls.pfs.core.both.repository

import com.victorbrndls.pfs.core.both.entity.Both
import kotlinx.coroutines.flow.Flow

interface BothRepository {
    suspend fun observe(): Flow<List<Both>>
}