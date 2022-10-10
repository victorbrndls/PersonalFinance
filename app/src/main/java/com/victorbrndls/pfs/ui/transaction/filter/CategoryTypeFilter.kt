package com.victorbrndls.pfs.ui.transaction.filter

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.ui.designsystem.theme.Purple20
import com.victorbrndls.pfs.ui.ktx.stringRes

@Composable
fun CategoryTypeFilter(
    selected: CategoryType?,
    onSelected: (CategoryType) -> Unit,
    modifier: Modifier = Modifier
) {
    val boxWidth = 185.dp // TODO how to calculate this?
    val halfBoxWidth = boxWidth / 2
    val buttonHeight = 36.dp

    val selectionOffsetX by animateDpAsState(
        targetValue = if (selected == CategoryType.EXPENSE) halfBoxWidth else 0.dp,
    )
    val selectionWidth by animateDpAsState(
        targetValue = if (selected == null) boxWidth else halfBoxWidth,
    )
    val leftCornerRadius by animateDpAsState(
        targetValue = when (selected) {
            CategoryType.EXPENSE -> 0.dp
            else -> 4.dp
        }
    )
    val rightCornerRadius by animateDpAsState(
        targetValue = when (selected) {
            CategoryType.INCOME -> 0.dp
            else -> 4.dp
        }
    )
    val selectionShape by remember {
        derivedStateOf {
            RoundedCornerShape(
                topStart = leftCornerRadius,
                bottomStart = leftCornerRadius,
                topEnd = rightCornerRadius,
                bottomEnd = rightCornerRadius,
            )
        }
    }

    Box(modifier = modifier.width(boxWidth)) {
        Box(
            modifier = Modifier
                .offset(x = selectionOffsetX)
                .width(selectionWidth)
                .height(buttonHeight)
                .background(Purple20, selectionShape)
        )

        Row {
            CategoryTypeButton(
                type = CategoryType.INCOME,
                modifier = Modifier
                    .height(buttonHeight)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { onSelected(CategoryType.INCOME) }
                    )
            )
            CategoryTypeButton(
                type = CategoryType.EXPENSE,
                modifier = Modifier
                    .height(buttonHeight)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = { onSelected(CategoryType.EXPENSE) }
                    )
            )
        }
    }
}

@Composable
private fun CategoryTypeButton(
    type: CategoryType,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = type.stringRes).uppercase(),
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
    )
}
