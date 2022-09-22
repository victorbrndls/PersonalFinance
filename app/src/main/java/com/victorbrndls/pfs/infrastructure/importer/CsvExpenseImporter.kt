package com.victorbrndls.pfs.infrastructure.importer

import android.app.Application
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.core.category.dto.EditCategoryData
import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.core.category.usecase.GetCategoriesUseCase
import com.victorbrndls.pfs.core.category.usecase.SaveCategoryUseCase
import com.victorbrndls.pfs.core.expense.dto.EditExpenseData
import com.victorbrndls.pfs.core.expense.usecase.SaveExpenseUseCase
import com.victorbrndls.pfs.infrastructure.logger.Logger
import com.victorbrndls.pfs.infrastructure.money.MoneyTranslator
import java.util.*
import javax.inject.Inject

class CsvExpenseImporter @Inject constructor(
    private val context: Application,
    private val saveCategoryUseCase: SaveCategoryUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val saveExpenseUseCase: SaveExpenseUseCase,
    private val moneyTranslator: MoneyTranslator,
) {

    private val abbreviatedMonths = context.resources.getStringArray(R.array.abbreviated_months)

    suspend fun import() {
        val resource = context.resources.openRawResource(R.raw.expenses)
        val lines = resource.reader().readLines()

        val expenses = lines.drop(1) // drop header
            .map { it.split(",") }
        Logger.d("Read ${expenses.size} possible expenses")

        val categories = expenses.mapNotNull { parseCategory(it) }.toSet()
        val existingCategories = getCategoriesUseCase.getAll(
            type = CategoryType.EXPENSE
        ).map { it.label }.toSet()

        val newCategories = categories - existingCategories
        newCategories.forEach { createCategory(it) }
        Logger.d("Read ${categories.size} categories, creating ${newCategories.size} new ones")

        val updatedCategories = getCategoriesUseCase.getAll(type = CategoryType.EXPENSE)
        expenses.forEach { createExpense(it, updatedCategories) }
        Logger.d("Saved ${expenses.size} expenses")
    }

    private suspend fun createCategory(label: String) {
        saveCategoryUseCase.save(
            EditCategoryData(
                id = null,
                label = label,
                type = CategoryType.EXPENSE
            )
        )
    }

    private fun parseCategory(parts: List<String>): String? {
        return parts[2].takeIf { it.isNotBlank() }
    }

    private suspend fun createExpense(parts: List<String>, categories: List<Category>) {
        runCatching {
            val date = parseDate(parts[0]) ?: return
            val description = parts[1].takeIf { it.isNotBlank() } ?: return
            val amount = moneyTranslator.parse(parts[3].removePrefix("$")) ?: return

            saveExpenseUseCase.save(
                EditExpenseData(
                    null,
                    description,
                    categories.random(),
                    date,
                    amount
                )
            )
        }
    }

    private fun parseDate(abbreviatedMonth: String): Date? {
        val month = abbreviatedMonths.indexOf(abbreviatedMonth)

        return Calendar.getInstance().apply {
            set(Calendar.DATE, (1..28).random())
            set(Calendar.MONTH, month)
        }.time
    }

}