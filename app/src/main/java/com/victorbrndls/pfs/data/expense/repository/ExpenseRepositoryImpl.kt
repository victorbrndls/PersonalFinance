package com.victorbrndls.pfs.data.expense.repository

import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.core.expense.dto.EditExpenseData
import com.victorbrndls.pfs.core.expense.entity.Expense
import com.victorbrndls.pfs.core.expense.repository.ExpenseRepository
import com.victorbrndls.pfs.data.category.repository.fakeCategories
import com.victorbrndls.pfs.infrastructure.logger.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.math.BigDecimal
import java.util.*
import javax.inject.Inject
import kotlin.random.Random

class ExpenseRepositoryImpl @Inject constructor() : ExpenseRepository {

    private val expenses = MutableStateFlow(fakeExpenses)

    override suspend fun getAll(): List<Expense> {
        return expenses.value
    }

    override suspend fun observe(): Flow<List<Expense>> {
        return expenses
    }

    override suspend fun save(expense: EditExpenseData) {
        expenses.value += Expense(
            Random.nextLong(),
            expense.description,
            expense.category,
            expense.date,
            expense.amount
        ).also {
            Logger.d("Saved expense | $it")
        }
    }
}

private val fakeExpenses = listOf(
    Expense(
        id = Random.nextLong(),
        description = "4g chip",
        category = fakeCategories.filter { it.type == CategoryType.EXPENSE }.random(),
        date = Date(Date().time + Random.nextLong(-864000000, 864000000)),
        amount = BigDecimal.valueOf(212)
    ),
    Expense(
        id = Random.nextLong(),
        description = "Amsterdam Hotel",
        category = fakeCategories.filter { it.type == CategoryType.EXPENSE }.random(),
        date = Date(Date().time + Random.nextLong(-864000000, 864000000)),
        amount = BigDecimal.valueOf(2599)
    ),
    Expense(
        id = Random.nextLong(),
        description = "Bear Soaq",
        category = fakeCategories.filter { it.type == CategoryType.EXPENSE }.random(),
        date = Date(Date().time + Random.nextLong(-864000000, 864000000)),
        amount = BigDecimal.valueOf(30)
    ),
    Expense(
        id = Random.nextLong(),
        description = "Supermarket",
        category = fakeCategories.filter { it.type == CategoryType.EXPENSE }.random(),
        date = Date(Date().time + Random.nextLong(-864000000, 864000000)),
        amount = BigDecimal.valueOf(52)
    ),
    Expense(
        id = Random.nextLong(),
        description = "Personal Trainer",
        category = fakeCategories.filter { it.type == CategoryType.EXPENSE }.random(),
        date = Date(Date().time + Random.nextLong(-864000000, 864000000)),
        amount = BigDecimal.valueOf(20)
    ),
    Expense(
        id = Random.nextLong(),
        description = "Family dinner",
        category = fakeCategories.filter { it.type == CategoryType.EXPENSE }.random(),
        date = Date(Date().time + Random.nextLong(-864000000, 864000000)),
        amount = BigDecimal.valueOf(149)
    ),
    Expense(
        id = Random.nextLong(),
        description = "Car Fuel",
        category = fakeCategories.filter { it.type == CategoryType.EXPENSE }.random(),
        date = Date(Date().time + Random.nextLong(-864000000, 864000000)),
        amount = BigDecimal.valueOf(206)
    ),
    Expense(
        id = Random.nextLong(),
        description = "StatusInvent",
        category = fakeCategories.filter { it.type == CategoryType.EXPENSE }.random(),
        date = Date(Date().time + Random.nextLong(-864000000, 864000000)),
        amount = BigDecimal.valueOf(59)
    ),
)