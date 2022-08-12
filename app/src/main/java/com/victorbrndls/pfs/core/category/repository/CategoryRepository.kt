package com.victorbrndls.pfs.core.category.repository

import com.victorbrndls.pfs.core.category.dto.EditCategoryData
import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.entity.CategoryType
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun getAll(): List<Category>
    suspend fun observe(
        type: CategoryType? = null
    ): Flow<List<Category>>

    suspend fun save(expense: EditCategoryData)
}