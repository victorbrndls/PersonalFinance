package com.victorbrndls.pfs.ui.designsystem.modifier

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import kotlin.math.floor

internal fun Modifier.calculateItemWidth(
    itemsPerRow: Int,
    onItemWidthCalculated: (Dp) -> Unit
): Modifier = composed {
    val localDensity = LocalDensity.current

    onSizeChanged {
        with(localDensity) {
            val itemWidth = floor((it.width / itemsPerRow.toFloat()))
            onItemWidthCalculated(itemWidth.toDp())
        }
    }
}