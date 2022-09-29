package com.victorbrndls.pfs.di

import com.victorbrndls.pfs.core.transaction.repository.TransactionRepository
import com.victorbrndls.pfs.core.transaction.usecase.ObserveTransactionsUseCase
import com.victorbrndls.pfs.core.transaction.usecase.ObserveTransactionsUseCaseImpl
import com.victorbrndls.pfs.data.transaction.repository.TransactionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TransactionModule {

    @Binds
    abstract fun getTransactionUseCase(
        impl: ObserveTransactionsUseCaseImpl
    ): ObserveTransactionsUseCase

    @Binds
    @Singleton
    abstract fun transactionRepository(impl: TransactionRepositoryImpl): TransactionRepository
}