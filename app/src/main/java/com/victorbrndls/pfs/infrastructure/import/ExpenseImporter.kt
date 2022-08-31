package com.victorbrndls.pfs.infrastructure.import

interface ExpenseImporter {
    suspend fun import()
}