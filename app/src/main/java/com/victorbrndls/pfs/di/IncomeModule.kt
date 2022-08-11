package com.victorbrndls.pfs.di

import com.victorbrndls.pfs.core.income.repository.IncomeRepository
import com.victorbrndls.pfs.core.income.usecase.*
import com.victorbrndls.pfs.data.income.repository.IncomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class IncomeModule {

    @Binds
    abstract fun saveIncomeUseCase(impl: SaveIncomeUseCaseImpl): SaveIncomeUseCase

    @Binds
    abstract fun getIncomesUseCase(impl: GetIncomesUseCaseImpl): GetIncomesUseCase

    @Binds
    abstract fun observeIncomesUseCase(impl: ObserveIncomesUseCaseImpl): ObserveIncomesUseCase

    @Binds
    @Singleton
    abstract fun incomeRepository(impl: IncomeRepositoryImpl): IncomeRepository
}