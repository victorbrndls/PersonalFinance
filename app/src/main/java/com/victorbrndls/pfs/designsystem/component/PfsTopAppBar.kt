package com.victorbrndls.pfs.designsystem.component

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PfsTopAppBar(
    @StringRes titleRes: Int,
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit = {},
) {
    SmallTopAppBar(
        title = {
            Text(text = stringResource(id = titleRes))
        },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
fun PfsTopAppBarPreview() {
    PfsTopAppBar(
        titleRes = android.R.string.unknownName
    )
}