package com.victorbrndls.pfs.di

import com.victorbrndls.pfs.core.summary.usecase.GetSummariesUseCase
import com.victorbrndls.pfs.core.summary.usecase.GetSummariesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SummaryModule {

    @Binds
    abstract fun getSummariesUseCase(impl: GetSummariesUseCaseImpl): GetSummariesUseCase

}