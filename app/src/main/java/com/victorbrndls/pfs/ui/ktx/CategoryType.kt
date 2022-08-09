package com.victorbrndls.pfs.ui.ktx

import androidx.compose.ui.graphics.Color
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.ui.designsystem.theme.Green40
import com.victorbrndls.pfs.ui.designsystem.theme.Red40

val CategoryType.stringRes: Int
    get() = when (this) {
        CategoryType.INCOME -> R.string.label_income
        CategoryType.EXPENSE -> R.string.label_expense
    }

val CategoryType.backgroundColor: Color
    get() = when (this) {
        CategoryType.INCOME -> Green40
        CategoryType.EXPENSE -> Red40
    }

val CategoryType.textOnBackgroundColor: Color
    get() = when (this) {
        CategoryType.INCOME -> Color.White
        CategoryType.EXPENSE -> Color.White
    }