package com.victorbrndls.pfs.core.both.repository

import com.victorbrndls.pfs.core.both.entity.Both

interface BothRepository {
    suspend fun getAll(): Both
}