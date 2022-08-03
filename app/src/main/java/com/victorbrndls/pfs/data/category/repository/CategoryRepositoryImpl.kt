package com.victorbrndls.pfs.data.category.repository

import com.victorbrndls.pfs.core.category.dto.EditCategoryData
import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.repository.CategoryRepository
import com.victorbrndls.pfs.infrastructure.logger.Logger
import javax.inject.Inject
import kotlin.random.Random

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {
    private var categories = mutableListOf<Category>()

    override suspend fun getAll(): List<Category> {
        return categories
    }

    override suspend fun save(expense: EditCategoryData) {
        categories += Category(
            Random.nextLong(),
            expense.description,
        ).also {
            Logger.d("Saved category | $it")
        }
    }
}