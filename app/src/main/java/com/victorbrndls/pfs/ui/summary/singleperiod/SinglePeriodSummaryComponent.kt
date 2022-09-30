package com.victorbrndls.pfs.ui.summary.singleperiod

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorbrndls.pfs.R
import com.victorbrndls.pfs.ui.designsystem.theme.Green40
import com.victorbrndls.pfs.ui.designsystem.theme.Red40
import com.victorbrndls.pfs.ui.designsystem.theme.White

@Composable
fun SinglePeriodSummaryComponent(
    modifier: Modifier = Modifier,
    viewModel: SinglePeriodSummaryViewModel = hiltViewModel()
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        SummaryEntry(
            label = stringResource(id = R.string.label_income),
            text = viewModel.item?.income ?: "",
            color = Green40
        )

        Spacer(modifier = Modifier.width(12.dp))

        SummaryEntry(
            label = stringResource(id = R.string.label_expenses),
            text = viewModel.item?.expenses ?: "",
            color = Red40
        )
    }
}

@Composable
private fun RowScope.SummaryEntry(
    label: String,
    text: String,
    color: Color
) {
    Column(
        modifier = Modifier.Companion
            .weight(1f)
            .shadow(4.dp, RoundedCornerShape(12.dp))
            .background(White, RoundedCornerShape(12.dp))
            .padding(8.dp)
    ) {
        Text(
            text = label,
            modifier = Modifier.alpha(0.6f)
        )
        Text(
            text = text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
    }
}