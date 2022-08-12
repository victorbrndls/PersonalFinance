package com.victorbrndls.pfs.core.category.usecase

import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.core.category.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ObserveCategoriesUseCase {
    suspend fun observe(
        type: CategoryType? = null
    ): Flow<List<Category>>
}

class ObserveCategoriesUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository
) : ObserveCategoriesUseCase {
    override suspend fun observe(
        type: CategoryType?
    ): Flow<List<Category>> {
        return categoryRepository.observe(type)
    }
}