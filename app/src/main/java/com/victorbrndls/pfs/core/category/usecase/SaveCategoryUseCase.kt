package com.victorbrndls.pfs.core.category.usecase

import com.victorbrndls.pfs.core.category.dto.EditCategoryData
import com.victorbrndls.pfs.core.category.repository.CategoryRepository
import javax.inject.Inject

interface SaveCategoryUseCase {
    suspend fun save(category: EditCategoryData)
}

class SaveCategoryUseCaseImpl @Inject constructor(
    private val categoryRepository: CategoryRepository
) : SaveCategoryUseCase {
    override suspend fun save(category: EditCategoryData) {
        categoryRepository.save(category)
    }
}