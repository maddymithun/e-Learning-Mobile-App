package com.example.composecleanarchitecture.base

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.composecleanarchitecture.data.network.NetworkErrorExceptions
import com.example.composecleanarchitecture.ui.common_components.ListProgressBar
import com.example.composecleanarchitecture.utils.showToast

@Composable
fun BaseComponent(
    backgroundColor: Color = MaterialTheme.colorScheme.background,
    progressBarState: State<Boolean>? = null,
    unauthorizedState: State<Boolean>? = null,
    progressBarContent: @Composable (Boolean) -> Unit,
    unAuthorizedContent: @Composable (Boolean) -> Unit,
    bodyContent: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        bodyContent()

        progressBarContent(progressBarState?.value ?: false)

        unAuthorizedContent(unauthorizedState?.value ?: false)
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