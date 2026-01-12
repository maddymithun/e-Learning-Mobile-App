package com.example.composecleanarchitecture.ui.common_components

import android.content.Context
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.composecleanarchitecture.data.network.NetworkErrorExceptions
import com.example.composecleanarchitecture.ui.theme.SlateGrey
import com.example.composecleanarchitecture.utils.showToast


@Composable
fun DottedRectangle(
    radius: Float = 10f,
    dotWidth: Float = 2f,
    bgColor: Color = MaterialTheme.colorScheme.background,
    dotColor: Color = SlateGrey
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = bgColor,
                shape = RoundedCornerShape(radius.dp)
            )
    ) {
        drawRoundRect(
            color = dotColor,
            size = size,
            style = Stroke(
                width = dotWidth,
                pathEffect = PathEffect.dashPathEffect(
                    floatArrayOf(
                        10f,
                        10f
                    ), 0f
                )
            ),
            cornerRadius = CornerRadius(radius.dp.toPx()),
        )
    }
}

fun <T : Any> LazyPagingItems<T>.paginationListHandler(context: Context, listScope: LazyListScope) {
    when {
        loadState.refresh is LoadState.Loading -> {
            listScope.item {
                ListProgressBar()
            }
        }
        loadState.append is LoadState.Loading -> {
            listScope.item {
                ListProgressBar()
            }
        }
        loadState.refresh is LoadState.Error -> {
            context.showToast(
                ((loadState.append as LoadState.Error).error as NetworkErrorExceptions).message
                    ?: "Something went wrong!"
            )
        }
        loadState.append is LoadState.Error -> {
            context.showToast(
                ((loadState.append as LoadState.Error).error as NetworkErrorExceptions).message
                    ?: "Something went wrong!"
            )
        }
    }
}

fun <T : Any> LazyPagingItems<T>.paginationListHandlerForGrid(context: Context, listScope: LazyGridScope) {
    when {
        loadState.refresh is LoadState.Loading -> {
            listScope.item {
                ListProgressBar()
            }
        }
        loadState.append is LoadState.Loading -> {
            listScope.item {
                ListProgressBar()
            }
        }
        loadState.refresh is LoadState.Error -> {
            context.showToast(
                 "Something went wrong!"
            )
        }
        loadState.append is LoadState.Error -> {
            context.showToast(
                 "Something went wrong!"
            )
        }
    }
}