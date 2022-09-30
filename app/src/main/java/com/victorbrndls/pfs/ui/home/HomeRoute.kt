package com.victorbrndls.pfs.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.designsystem.shape.SemiOvalShape
import com.victorbrndls.pfs.ui.designsystem.theme.Purple20
import com.victorbrndls.pfs.ui.designsystem.theme.Purple40
import com.victorbrndls.pfs.ui.designsystem.theme.White
import com.victorbrndls.pfs.ui.route.Routes
import com.victorbrndls.pfs.ui.summary.SummaryComponent
import com.victorbrndls.pfs.ui.transaction.list.smaller.ListShortTransactionComponent

@Composable
fun HomeRoute(
    navController: NavController,
) {
    HomeScreen(
        onNavigateToListCategories = { navController.navigate(Routes.LIST_CATEGORIES) },
        onNavigateToAddExpense = { navController.navigate(Routes.EDIT_EXPENSE) },
        onNavigateToAddIncome = { navController.navigate(Routes.EDIT_INCOME) },
        onNavigateToCharts = { navController.navigate(Routes.CHARTS) },
        onNavigateToTransactions = { navController.navigate(Routes.LIST_TRANSACTIONS) },
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun HomeScreen(
    onNavigateToListCategories: () -> Unit,
    onNavigateToAddExpense: () -> Unit,
    onNavigateToAddIncome: () -> Unit,
    onNavigateToCharts: () -> Unit,
    onNavigateToTransactions: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold() { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            Column(
                modifier = modifier.verticalScroll(state = rememberScrollState())
            ) {
                Box {
                    val boxHeight by remember { mutableStateOf(120.dp) }
                    val contentTopPadding by remember { derivedStateOf { boxHeight * 0.50f } }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(boxHeight)
                            .background(remember {
                                Brush.verticalGradient(listOf(Purple40, Purple20))
                            }, remember {
                                SemiOvalShape()
                            })
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Welcome Back",
                            color = White,
                            fontSize = 22.sp,
                        )
                    }

                    Box(
                        modifier = Modifier
                            .padding(top = contentTopPadding)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .shadow(4.dp, RoundedCornerShape(12.dp))
                            .background(White, RoundedCornerShape(12.dp))
                    ) {
                        HomeQuickMenu(
                            onNavigateToAddExpense = onNavigateToAddExpense,
                            onNavigateToAddIncome = onNavigateToAddIncome,
                            onNavigateToListCategories = onNavigateToListCategories,
                            onNavigateToCharts = onNavigateToCharts,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                ShortTransactionsComponent(onNavigateToTransactions)

                Spacer(modifier = Modifier.height(16.dp))

                SummaryComponent()

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
private fun ShortTransactionsComponent(onNavigateToTransactions: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .background(White, RoundedCornerShape(12.dp))
    ) {
        Text(
            text = stringResource(id = R.string.title_list_transaction),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 4.dp)
        )
        ListShortTransactionComponent()
        Button(onClick = onNavigateToTransactions) {
            Text(text = stringResource(id = R.string.transaction_see_more))
        }
    }
}