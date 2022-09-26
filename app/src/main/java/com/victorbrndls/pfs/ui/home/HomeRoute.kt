package com.victorbrndls.pfs.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.designsystem.component.ExpandableFloatingActionButton
import com.victorbrndls.pfs.ui.designsystem.component.IconTextButton
import com.victorbrndls.pfs.ui.designsystem.shape.SemiOvalShape
import com.victorbrndls.pfs.ui.designsystem.theme.Purple20
import com.victorbrndls.pfs.ui.designsystem.theme.Purple40
import com.victorbrndls.pfs.ui.designsystem.theme.White
import com.victorbrndls.pfs.ui.route.Routes
import com.victorbrndls.pfs.ui.summary.SummaryComponent

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
    Scaffold(
        floatingActionButton = {
            HomeFab(
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
            Column {
                Box {
                    val boxHeight by remember { mutableStateOf(120.dp) }
                    val contentTopPadding by derivedStateOf { boxHeight * 0.50f }

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
                            onNavigateToCharts = onNavigateToCharts,
                            onNavigateToTransactions = onNavigateToTransactions,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                SummaryComponent()

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}