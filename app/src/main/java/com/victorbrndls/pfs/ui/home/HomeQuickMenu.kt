package com.victorbrndls.pfs.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.designsystem.theme.White

@Composable
internal fun HomeQuickMenu(
    onNavigateToCharts: () -> Unit,
    onNavigateToTransactions: () -> Unit,
    modifier: Modifier = Modifier
) {
    // TODO remove fling
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier,
    ) {
        item {
            VerticalButton(
                text = stringResource(id = R.string.title_charts),
                iconId = R.drawable.ic_baseline_bar_chart_24_black,
                onClick = onNavigateToCharts,
            )
        }
        item {
            VerticalButton(
                text = stringResource(id = R.string.title_list_transaction),
                iconId = R.drawable.ic_baseline_compare_arrows_24_black,
                onClick = onNavigateToTransactions,
            )
        }
        item {
            VerticalButton(
                text = stringResource(id = R.string.title_budget),
                iconId = R.drawable.ic_baseline_data_thresholding_24_black,
                onClick = {},
            )
        }
    }
}

@Composable
private fun VerticalButton(
    text: String,
    @DrawableRes iconId: Int,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick() },
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier
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