package com.victorbrndls.pfs.data.category.repository

import com.victorbrndls.pfs.core.category.dto.EditCategoryData
import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.core.category.repository.CategoryRepository
import com.victorbrndls.pfs.infrastructure.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.random.Random

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {

    private val categories = MutableStateFlow(fakeCategories)

    override suspend fun getAll(type: CategoryType?): List<Category> {
        return categories.value.let { list ->
            if (type == null) return list
            list.filter { it.type == type }
        }
    }

    override suspend fun observe(
        type: CategoryType?
    ): Flow<List<Category>> {
        return categories
            .let { flow ->
                if (type == null) return@let flow
                flow.map { cats -> cats.filter { it.type == type } }
            }
    }

    override suspend fun save(expense: EditCategoryData) {
        categories.value += Category(
            Random.nextLong(),
            expense.label,
            expense.type
        ).also {
            Logger.d("Saved category | $it")
        }
    }
}

val fakeCategories = listOf(
    Category(
        id = Random.nextLong(),
        label = "Main Job",
        type = CategoryType.INCOME
    ),
    Category(
        id = Random.nextLong(),
        label = "Other",
        type = CategoryType.INCOME
    ),
)