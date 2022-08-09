package com.victorbrndls.pfs.di

import com.victorbrndls.pfs.core.category.repository.CategoryRepository
import com.victorbrndls.pfs.core.category.usecase.*
import com.victorbrndls.pfs.data.category.repository.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CategoryModule {

    @Binds
    abstract fun saveCategoryUseCase(impl: SaveCategoryUseCaseImpl): SaveCategoryUseCase

    @Binds
    abstract fun observeCategoriesUseCase(
        impl: ObserveCategoriesUseCaseImpl
    ): ObserveCategoriesUseCase

    @Binds
    abstract fun getCategoriesUseCase(impl: GetCategoriesUseCaseImpl): GetCategoriesUseCase

    @Binds
    @Singleton
    abstract fun expenseRepository(impl: CategoryRepositoryImpl): CategoryRepository
}