package com.victorbrndls.pfs.di

import com.victorbrndls.pfs.core.both.repository.BothRepository
import com.victorbrndls.pfs.core.both.usecase.ObserveBothUseCase
import com.victorbrndls.pfs.core.both.usecase.ObserveBothUseCaseImpl
import com.victorbrndls.pfs.data.both.repository.BothRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BothModule {

    @Binds
    abstract fun getBothUseCase(impl: ObserveBothUseCaseImpl): ObserveBothUseCase

    @Binds
    @Singleton
    abstract fun bothRepository(impl: BothRepositoryImpl): BothRepository
}