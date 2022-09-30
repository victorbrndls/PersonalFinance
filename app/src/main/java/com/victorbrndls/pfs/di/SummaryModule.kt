package com.victorbrndls.pfs.di

import com.victorbrndls.pfs.core.summary.usecase.GetSummariesUseCase
import com.victorbrndls.pfs.core.summary.usecase.GetSummariesUseCaseImpl
import com.victorbrndls.pfs.core.summary.usecase.ObserveSummariesUseCase
import com.victorbrndls.pfs.core.summary.usecase.ObserveSummariesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SummaryModule {

    @Binds
    abstract fun getSummariesUseCase(impl: GetSummariesUseCaseImpl): GetSummariesUseCase

    @Binds
    abstract fun observeSummariesUseCase(impl: ObserveSummariesUseCaseImpl): ObserveSummariesUseCase

}