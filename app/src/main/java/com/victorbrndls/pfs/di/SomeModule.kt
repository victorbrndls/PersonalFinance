package com.victorbrndls.pfs.di

import com.victorbrndls.pfs.some.DateTranslator
import com.victorbrndls.pfs.some.DateTranslatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SomeModule {

    @Binds
    abstract fun dateTranslator(impl: DateTranslatorImpl): DateTranslator
}