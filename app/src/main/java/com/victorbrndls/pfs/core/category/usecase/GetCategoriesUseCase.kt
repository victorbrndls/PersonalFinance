package com.victorbrndls.pfs.core.category.usecase

import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.core.category.repository.CategoryRepository
import javax.inject.Inject

interface GetCategoriesUseCase {
    suspend fun getAll(
        type: CategoryType? = null
    ): List<Category>
}

class GetCategoriesUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository
) : GetCategoriesUseCase {
    override suspend fun getAll(
        type: CategoryType?
    ): List<Category> {
        return categoryRepository.getAll(type = type)
    }
}