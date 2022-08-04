package com.victorbrndls.pfs.data.category.repository

import com.victorbrndls.pfs.core.category.dto.EditCategoryData
import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.repository.CategoryRepository
import com.victorbrndls.pfs.infrastructure.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import kotlin.random.Random

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {

    private val categories = MutableStateFlow(emptyList<Category>())

    override suspend fun getAll(): List<Category> {
        return categories.value
    }

    override suspend fun observe(): Flow<List<Category>> {
        return categories
    }

    override suspend fun save(expense: EditCategoryData) {
        categories.value += Category(
            Random.nextLong(),
            expense.label,
        ).also {
            Logger.d("Saved category | $it")
        }
    }
}