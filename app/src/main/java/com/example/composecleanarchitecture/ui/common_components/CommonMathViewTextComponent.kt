package com.example.composecleanarchitecture.ui.common_components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.viewinterop.AndroidView
import com.example.composecleanarchitecture.ui.theme.brandShade4TextColor
import com.judemanutd.katexview.KatexView


@Composable
fun CommonMathViewTextComponent(
    title: String,
    textColor: Color = brandShade4TextColor,

    ) {
    val mathSequence = convertSentencesToLatexFormat(input = title)
    AndroidView(
        factory = { context ->
            KatexView(context, attrs = null, defStyleAttr = 0).apply {
                setText("$mathSequence")
                setTextColor(textColor.toArgb())
            }
        },
        update = { view ->
            view.setText("$mathSequence")
            view.setTextColor(textColor.toArgb())
        }
    )
}

fun convertSentencesToLatexFormat(input: String): String {
    val sentences = input.split(" ").toMutableList()
    val latexPattern = Regex("""\\[^ ]+""")

    for (i in sentences.indices) {
        if (sentences[i].matches(latexPattern)) {
            sentences[i] = "$$${sentences[i]}$$"
        }
    }

    return sentences.joinToString(" ")
}