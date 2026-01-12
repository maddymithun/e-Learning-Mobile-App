package com.example.composecleanarchitecture.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composecleanarchitecture.data.network.AppNetworkState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel : ViewModel() {
    private val _showProgressBar = MutableStateFlow(false)
    val showProgressBar = _showProgressBar.asStateFlow()

    private val _unauthorized = MutableStateFlow(false)
    val unauthorized = _unauthorized.asStateFlow()

    private val _uiState: MutableStateFlow<UIState<Any>?> = MutableStateFlow(null)
    val uiState: StateFlow<UIState<Any>?> = _uiState

    protected fun <T : Any> generateUiState(stateId: Int, state: AppNetworkState<T>) {
        when (state) {
            is AppNetworkState.Loading -> {
                _uiState.value = UIState.Loading(stateId = stateId)

                _showProgressBar.value = true

                _unauthorized.value = false
            }

            is AppNetworkState.Data -> {
                _uiState.value = UIState.DataLoaded(stateId = stateId, data = state.data)

                _showProgressBar.value = false

                _unauthorized.value = false
            }

            is AppNetworkState.Error -> {
                _uiState.value =
                    UIState.Error(
                        stateId = stateId,
                        message = state.exception.errorMessage ?: "",
                        unAuthorized = state.unauthorized
                    )

                _showProgressBar.value = false

                _unauthorized.value = state.unauthorized
            }
        }
    }

    protected fun <T : Any> generateUiStateLocal(stateId: Int, state: T) {
        _uiState.value = UIState.Loading(stateId = stateId)

        _showProgressBar.value = true

        _unauthorized.value = false

        _uiState.value = UIState.DataLoaded(stateId = stateId, data = state)

        _showProgressBar.value = false

        _unauthorized.value = false

    }

    fun setLoadingState(stateId: Int) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading(stateId = stateId)
            _showProgressBar.value = true
            _unauthorized.value = false
        }
    }

    fun checkLoading(stateId: Int) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading(stateId = stateId)

            _showProgressBar.value = true
            _unauthorized.value = false
            delay(10000)
            _showProgressBar.value = false
        }
    }
}

sealed class UIState<out T> {
    data class DataLoaded<out T>(val stateId: Int, val data: T) : UIState<T>()

    data class Loading(val stateId: Int) : UIState<Nothing>()

    data class Error(val stateId: Int, var message: String, var unAuthorized: Boolean = false) :
        UIState<Nothing>()

}