package com.victorbrndls.pfs.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.designsystem.modifier.calculateItemWidth
import com.victorbrndls.pfs.ui.designsystem.theme.White

@Composable
internal fun HomeQuickMenu(
    onNavigateToAddExpense: () -> Unit,
    onNavigateToAddIncome: () -> Unit,
    onNavigateToListCategories: () -> Unit,
    onNavigateToCharts: () -> Unit,
    modifier: Modifier = Modifier
) {
    var buttonWidth by remember { mutableStateOf(0.dp) }

    FlowRow(
        mainAxisAlignment = FlowMainAxisAlignment.SpaceAround,
        modifier = modifier
            .fillMaxWidth()
            .calculateItemWidth(
                itemsPerRow = 3,
                onItemWidthCalculated = { buttonWidth = it })
    ) {
        VerticalButton(
            text = stringResource(id = R.string.title_add_expense),
            iconId = R.drawable.ic_baseline_add_24_black,
            onClick = onNavigateToAddExpense,
            modifier = Modifier.width(buttonWidth)
        )

        VerticalButton(
            text = stringResource(id = R.string.title_add_income),
            iconId = R.drawable.ic_baseline_remove_24_black,
            onClick = onNavigateToAddIncome,
            modifier = Modifier.width(buttonWidth)
        )

        VerticalButton(
            text = stringResource(id = R.string.title_list_categories),
            iconId = R.drawable.ic_baseline_bookmarks_24_black,
            onClick = onNavigateToListCategories,
            modifier = Modifier.width(buttonWidth)
        )

        VerticalButton(
            text = stringResource(id = R.string.title_charts),
            iconId = R.drawable.ic_baseline_bar_chart_24_black,
            onClick = onNavigateToCharts,
            modifier = Modifier.width(buttonWidth)
        )

        VerticalButton(
            text = stringResource(id = R.string.title_budget),
            iconId = R.drawable.ic_baseline_data_thresholding_24_black,
            onClick = {},
            modifier = Modifier.width(buttonWidth)
        )

        Box(modifier = Modifier.width(buttonWidth))
    }
}

@Composable
private fun VerticalButton(
    text: String,
    @DrawableRes iconId: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(4.dp),
        modifier = modifier
            .aspectRatio(1f)
            .padding(4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = iconId),
                tint = White,
                contentDescription = text,
                modifier = Modifier.fillMaxSize(0.6f)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text)
        }
    }
}