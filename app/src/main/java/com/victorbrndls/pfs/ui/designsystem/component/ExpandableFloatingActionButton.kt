package com.victorbrndls.pfs.ui.designsystem.component

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableFloatingActionButton(
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = FloatingActionButtonDefaults.shape,
    containerColor: Color = FloatingActionButtonDefaults.containerColor,
    contentColor: Color = contentColorFor(containerColor),
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    fabContent: @Composable FabController.() -> Unit,
    content: @Composable FabController.() -> Unit,
) {
    var isOpen by remember { mutableStateOf(false) }
    val controller = remember {
        object : FabController {
            override fun close() = Unit.also { isOpen = false }
        }
    }

    Column(
        horizontalAlignment = Alignment.End
    ) {
        if (isOpen) {
            controller.content()
            Spacer(modifier = Modifier.height(16.dp))
        }

        FloatingActionButton(
            onClick = { isOpen = !isOpen },
            modifier = modifier,
            interactionSource = interactionSource,
            shape = shape,
            containerColor = containerColor,
            contentColor = contentColor,
            elevation = elevation,
        ) {
            controller.fabContent()
        }
    }
}

interface FabController {
    fun close()
}