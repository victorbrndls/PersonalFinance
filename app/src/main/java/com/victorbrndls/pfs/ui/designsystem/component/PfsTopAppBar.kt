package com.victorbrndls.pfs.ui.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun PfsTopAppBar(
    @StringRes titleRes: Int,
    onNavigationClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SmallTopAppBar(
        title = {
            Text(text = stringResource(id = titleRes))
        },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "", /* todo */
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        modifier = modifier
    )
}