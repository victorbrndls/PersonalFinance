package com.victorbrndls.pfs.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.victorbrndls.pfs.ui.route.Routes

@Composable
fun HomeRoute(
    navController: NavController,
) {
    HomeScreen(
        onNavigateToListCategories = { navController.navigate(Routes.LIST_CATEGORIES) },
        onNavigateToEditExpense = { navController.navigate(Routes.EDIT_EXPENSE) },
        onNavigateToEditIncome = { navController.navigate(Routes.EDIT_INCOME) },
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun HomeScreen(
    onNavigateToListCategories: () -> Unit,
    onNavigateToEditExpense: () -> Unit,
    onNavigateToEditIncome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = onNavigateToListCategories) {
                    Text(text = "List Categories")
                }
                Button(onClick = onNavigateToEditExpense) {
                    Text(text = "Edit Expense")
                }
                Button(onClick = onNavigateToEditIncome) {
                    Text(text = "Edit Income")
                }
            }
        }
    }
}