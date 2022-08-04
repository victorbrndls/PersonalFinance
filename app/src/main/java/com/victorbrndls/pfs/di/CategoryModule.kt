package com.victorbrndls.pfs.di

import com.victorbrndls.pfs.core.category.repository.CategoryRepository
import com.victorbrndls.pfs.core.category.usecase.ObserveCategoriesUseCase
import com.victorbrndls.pfs.core.category.usecase.ObserveCategoriesUseCaseImpl
import com.victorbrndls.pfs.core.category.usecase.SaveCategoryUseCase
import com.victorbrndls.pfs.core.category.usecase.SaveCategoryUseCaseImpl
import com.victorbrndls.pfs.data.category.repository.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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
    abstract fun expenseRepository(impl: CategoryRepositoryImpl): CategoryRepository
}