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
        onNavigateToEditExpenses = { navController.navigate(Routes.EDIT_EXPENSES) },
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun HomeScreen(
    onNavigateToEditExpenses: () -> Unit,
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
                Button(onClick = onNavigateToEditExpenses) {
                    Text(text = "Edit Expenses")
                }
            }
        }
    }
}