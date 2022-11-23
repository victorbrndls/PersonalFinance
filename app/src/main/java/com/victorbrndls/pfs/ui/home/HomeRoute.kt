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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
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
import com.victorbrndls.pfs.ui.summary.singleperiod.SinglePeriodSummaryComponent
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
    Scaffold { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            Column(
                modifier = modifier.verticalScroll(state = rememberScrollState())
            ) {
                HomeHeader(
                    onNavigateToAddExpense = onNavigateToAddExpense,
                    onNavigateToAddIncome = onNavigateToAddIncome,
                    onNavigateToListCategories = onNavigateToListCategories,
                    onNavigateToCharts = onNavigateToCharts
                )

                Spacer(modifier = Modifier.height(16.dp))

                ShortTransactionsComponent(
                    onNavigateToTransactions = onNavigateToTransactions
                )

                Spacer(modifier = Modifier.height(16.dp))

                HomeSummaryComponent()

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun HomeHeader(
    onNavigateToAddExpense: () -> Unit,
    onNavigateToAddIncome: () -> Unit,
    onNavigateToListCategories: () -> Unit,
    onNavigateToCharts: () -> Unit
) {
    Box {
        val density = LocalDensity.current
        val backgroundBottomPadding = 96.dp
        val bottomSpacer = 16.dp
        var boxHeight by remember { mutableStateOf(264.dp) }
        val contentTopPadding by remember {
            derivedStateOf { boxHeight - backgroundBottomPadding + bottomSpacer }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(remember {
                    Brush.verticalGradient(listOf(Purple40, Purple20))
                }, remember {
                    SemiOvalShape()
                })
                .padding(horizontal = 16.dp)
                .onSizeChanged {
                    boxHeight = with(density) { it.height.toDp() }
                }
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Welcome Back",
                color = White,
                fontSize = 22.sp,
            )

            Spacer(modifier = Modifier.height(16.dp))

            SinglePeriodSummaryComponent()

            Spacer(modifier = Modifier.height(backgroundBottomPadding))
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

@Composable
private fun HomeSummaryComponent() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp)
            .background(White)
            .padding(vertical = 4.dp)
    ) {
        SummaryComponent()
    }
}
