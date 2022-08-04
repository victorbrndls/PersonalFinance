package com.victorbrndls.pfs.core.category.usecase

import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ObserveCategoriesUseCase {
    suspend fun observe(): Flow<List<Category>>
}

class ObserveCategoriesUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ObserveCategoriesUseCase {
    override suspend fun observe(): Flow<List<Category>> {
        return categoryRepository.observe()
    }
}