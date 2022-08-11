package com.victorbrndls.pfs.core.both.usecase

import com.victorbrndls.pfs.core.both.entity.Both
import com.victorbrndls.pfs.core.both.repository.BothRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ObserveBothUseCase {
    suspend fun observe(): Flow<List<Both>>
}

class ObserveBothUseCaseImpl @Inject constructor(
    private val bothRepository: BothRepository
) : ObserveBothUseCase {
    override suspend fun observe(): Flow<List<Both>> {
        return bothRepository.observe()
    }
}