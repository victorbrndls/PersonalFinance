package com.victorbrndls.pfs.ui.designsystem.shape

import androidx.compose.foundation.shape.GenericShape
import androidx.compose.ui.graphics.Shape

class SemiOvalShape : Shape by GenericShape(builder = { size, _ ->
    lineTo(size.width, 0f)
    relativeLineTo(0f, size.height * 0.8f)
    cubicTo(
        x1 = size.width * .7f,
        y1 = size.height,
        x2 = size.width * .3f,
        y2 = size.height,
        x3 = 0f,
        y3 = size.height * 0.8f
    )
})