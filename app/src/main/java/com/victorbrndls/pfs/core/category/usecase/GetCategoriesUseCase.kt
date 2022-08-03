package com.victorbrndls.pfs.core.category.usecase

import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.repository.CategoryRepository
import javax.inject.Inject

interface GetCategoriesUseCase {
    suspend fun getAll(): List<Category>
}

class GetCategoriesUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository
) : GetCategoriesUseCase {
    override suspend fun getAll(): List<Category> {
        return categoryRepository.getAll()
    }
}