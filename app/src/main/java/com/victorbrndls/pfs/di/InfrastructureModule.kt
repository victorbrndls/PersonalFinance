package com.victorbrndls.pfs.di

import com.victorbrndls.pfs.infrastructure.date.DateTranslator
import com.victorbrndls.pfs.infrastructure.date.DateTranslatorImpl
import com.victorbrndls.pfs.infrastructure.money.MoneyTranslator
import com.victorbrndls.pfs.infrastructure.money.MoneyTranslatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InfrastructureModule {

    @Binds
    @Singleton
    abstract fun dateTranslator(impl: DateTranslatorImpl): DateTranslator

    @Binds
    @Singleton
    abstract fun moneyTranslator(impl: MoneyTranslatorImpl): MoneyTranslator
}