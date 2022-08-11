package com.victorbrndls.pfs.di

import com.victorbrndls.pfs.core.expense.repository.ExpenseRepository
import com.victorbrndls.pfs.core.expense.usecase.*
import com.victorbrndls.pfs.data.expense.repository.ExpenseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ExpenseModule {

    @Binds
    abstract fun saveExpenseUseCase(impl: SaveExpenseUseCaseImpl): SaveExpenseUseCase

    @Binds
    abstract fun getExpensesUseCase(impl: GetExpensesUseCaseImpl): GetExpensesUseCase

    @Binds
    abstract fun observeExpensesUseCase(impl: ObserveExpensesUseCaseImpl): ObserveExpensesUseCase

    @Binds
    @Singleton
    abstract fun expenseRepository(impl: ExpenseRepositoryImpl): ExpenseRepository
}