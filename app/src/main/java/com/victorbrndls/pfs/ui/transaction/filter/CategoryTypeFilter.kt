package com.victorbrndls.pfs.ui.transaction.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.victorbrndls.pfs.core.category.entity.CategoryType
import com.victorbrndls.pfs.ui.designsystem.theme.Black10
import com.victorbrndls.pfs.ui.designsystem.theme.Transparent
import com.victorbrndls.pfs.ui.ktx.stringRes

@Composable
fun CategoryTypeFilter(
    selected: CategoryType?,
    onSelected: (CategoryType) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            CategoryType.values().forEach { type ->
                Text(
                    text = stringResource(id = type.stringRes),
                    modifier = Modifier
                        .background(if (selected == type) Black10 else Transparent)
                        .clickable { onSelected(type) }
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                )
            }
        }
    }
}
