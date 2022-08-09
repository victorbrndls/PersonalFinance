package com.victorbrndls.pfs.core.both.usecase

import com.victorbrndls.pfs.core.both.entity.Both
import com.victorbrndls.pfs.core.both.repository.BothRepository
import javax.inject.Inject

interface GetBothUseCase {
    suspend fun getAll(): Both
}

class GetBothUseCaseImpl @Inject constructor(
    private val bothRepository: BothRepository
) : GetBothUseCase {
    override suspend fun getAll(): Both {
        return bothRepository.getAll()
    }
}