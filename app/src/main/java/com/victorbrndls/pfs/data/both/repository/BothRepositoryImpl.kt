package com.victorbrndls.pfs.data.both.repository

import com.victorbrndls.pfs.core.both.entity.Both
import com.victorbrndls.pfs.core.both.repository.BothRepository
import javax.inject.Inject

class BothRepositoryImpl @Inject constructor() : BothRepository {

    override suspend fun getAll(): Both {
        return Both(emptyList(), emptyList())
    }

}
