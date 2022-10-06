package com.victorbrndls.pfs.ui.transaction.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.victorbrndls.pfs.ui.designsystem.theme.Purple20
import com.victorbrndls.pfs.ui.designsystem.theme.Purple40

data class BarPeriodFilterEntry(
    val id: String,
    val label: String,
    val value: Float,
    val data: Any? = null,
    val isSelected: Boolean = false
)

@Stable
class BarPeriodFilterState(
    items: List<BarPeriodFilterEntry>,
    onEntryClicked: (BarPeriodFilterEntry) -> Unit
) {

    var items = mutableStateOf(items)
        private set

    var onEntryClicked: (BarPeriodFilterEntry) -> Unit = {}
        private set(value) {
            field = { clicked ->
                items.value = items.value.map { item ->
                    if (item != clicked || item.value <= 0f) return@map item
                    clicked.copy(isSelected = !clicked.isSelected)
                }
                value(clicked)
            }
        }

    init {
        // this causes the set callback to be triggered
        this.onEntryClicked = onEntryClicked
    }
}

@Composable
fun rememberBarPeriodFilterState(
    items: List<BarPeriodFilterEntry> = emptyList(),
    onEntryClicked: (BarPeriodFilterEntry) -> Unit = {}
): BarPeriodFilterState {
    return remember(items) {
        BarPeriodFilterState(items, onEntryClicked)
    }
}

@Composable
fun BarPeriodFilter(
    modifier: Modifier = Modifier,
    state: BarPeriodFilterState = rememberBarPeriodFilterState(),
) {
    val labelHeight = 32.dp
    val listState = rememberLazyListState()

    LaunchedEffect(state) {
        val lastIndex = state.items.value.lastIndex
        if (lastIndex >= 0) listState.scrollToItem(lastIndex)
    }

    LazyRow(
        state = listState,
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        val items = state.items.value
        val biggestValue by derivedStateOf { items.maxByOrNull { it.value }?.value }

        items(
            items = items,
            key = { it.id }
        ) { item ->
            BoxWithConstraints {
                biggestValue?.let { value ->
                    val proportion = item.value / value

                    BarPeriodItem(
                        entry = item,
                        onClicked = { state.onEntryClicked(item) },
                        boxModifier = Modifier.height((maxHeight - labelHeight) * proportion),
                        labelModifier = Modifier.heightIn(max = labelHeight)
                    )
                }
            }

        }
    }
}

@Composable
fun BarPeriodItem(
    entry: BarPeriodFilterEntry,
    onClicked: () -> Unit,
    modifier: Modifier = Modifier,
    boxModifier: Modifier = Modifier,
    labelModifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = boxModifier
                .width(36.dp)
                .background(entry.boxColor())
                .clickable(onClick = onClicked)
        )
        Text(
            text = entry.label,
            modifier = labelModifier
        )
    }

}

private fun BarPeriodFilterEntry.boxColor() = when (this.isSelected) {
    true -> Purple40
    false -> Purple20
}