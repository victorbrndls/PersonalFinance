package com.victorbrndls.pfs.ui.category.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.core.category.entity.Category
import com.victorbrndls.pfs.ui.designsystem.component.PfsHorizontalProgressBar
import com.victorbrndls.pfs.ui.designsystem.component.PfsTopAppBar
import com.victorbrndls.pfs.ui.ktx.backgroundColor
import com.victorbrndls.pfs.ui.ktx.stringRes
import com.victorbrndls.pfs.ui.ktx.textOnBackgroundColor
import com.victorbrndls.pfs.ui.route.Routes

@Composable
fun ListCategoriesRoute(
    navController: NavController,
    viewModel: ListCategoriesViewModel = hiltViewModel()
) {
    if (viewModel.closeScreen) LaunchedEffect(Unit) {
        navController.popBackStack()
    }

    LaunchedEffect(Unit) {
        viewModel.isNavigateToAddCategory.collect { navigate ->
            if (navigate) navController.navigate(Routes.EDIT_CATEGORY)
        }
    }

    ListCategoriesScreen(
        categories = viewModel.categories,
        isLoading = viewModel.isLoading,
        onAddClicked = { viewModel.addNewCategory() },
        onNavigateUp = { navController.popBackStack() }
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun ListCategoriesScreen(
    categories: List<Category>,
    isLoading: Boolean,
    onAddClicked: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            PfsTopAppBar(
                titleRes = R.string.title_list_categories,
                onNavigationClick = { onNavigateUp() })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClicked) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(id = R.string.content_description_add),
                    tint = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    ) { innerPadding ->
        BoxWithConstraints(
            modifier = modifier
                .padding(innerPadding)
                .consumedWindowInsets(innerPadding)
        ) {
            if (isLoading) {
                PfsHorizontalProgressBar()
            }
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    items = categories,
                    key = { it.id },
                ) { category ->
                    CategoryItem(category)
                }
            }
        }
    }
}

@Composable
private fun CategoryItem(
    category: Category,
    modifier: Modifier = Modifier
) {
    val type = category.type

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 12.dp)
    ) {
        Text(
            text = category.label,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.width(16.dp))

        Surface(
            color = type.backgroundColor,
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = stringResource(id = type.stringRes),
                fontSize = 11.sp,
                color = type.textOnBackgroundColor,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }

}
