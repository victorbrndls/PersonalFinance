package com.victorbrndls.pfs.core.category.dto

import com.victorbrndls.pfs.core.category.entity.CategoryType

data class EditCategoryData(
    val id: Long?,
    val label: String,
    val type: CategoryType
)