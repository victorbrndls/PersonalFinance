package com.victorbrndls.pfs.ui.transaction.list

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.designsystem.component.PfsTopAppBar
import com.victorbrndls.pfs.ui.route.Routes
import com.victorbrndls.pfs.ui.transaction.component.AddTransactionsFab

@Composable
fun ListTransactionsRoute(
    navController: NavController,
) {
    ListTransactionsScreen(
        onNavigateToAddExpense = { navController.navigate(Routes.EDIT_EXPENSE) },
        onNavigateToAddIncome = { navController.navigate(Routes.EDIT_INCOME) },
        onNavigateUp = { navController.navigateUp() },
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun ListTransactionsScreen(
    onNavigateToAddExpense: () -> Unit,
    onNavigateToAddIncome: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            PfsTopAppBar(
                titleRes = R.string.title_list_transaction,
                onNavigationClick = { onNavigateUp() })
        },
        floatingActionButton = {
            AddTransactionsFab(
                onNavigateToAddExpense = onNavigateToAddExpense,
                onNavigateToAddIncome = onNavigateToAddIncome,
            )
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            ListTransactionComponent(modifier = Modifier.fillMaxSize())
        }
    }
}