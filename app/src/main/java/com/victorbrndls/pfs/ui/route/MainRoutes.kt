package com.victorbrndls.pfs.ui.route

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.victorbrndls.pfs.ui.category.edit.EditCategoryRoute
import com.victorbrndls.pfs.ui.category.list.ListCategoriesRoute
import com.victorbrndls.pfs.ui.chart.ChartsRoute
import com.victorbrndls.pfs.ui.expense.edit.EditExpenseRoute
import com.victorbrndls.pfs.ui.home.HomeRoute
import com.victorbrndls.pfs.ui.income.edit.EditIncomeRoute

@Composable
fun MainRoutes() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.HOME) {
        composable(Routes.HOME) { HomeRoute(navController) }

        composable(Routes.EDIT_EXPENSE) { EditExpenseRoute(navController) }

        composable(Routes.EDIT_INCOME) { EditIncomeRoute(navController) }

        composable(Routes.LIST_CATEGORIES) { ListCategoriesRoute(navController) }
        dialog(Routes.EDIT_CATEGORY) { EditCategoryRoute(navController) }

        composable(Routes.CHARTS) { ChartsRoute() }
    }
}