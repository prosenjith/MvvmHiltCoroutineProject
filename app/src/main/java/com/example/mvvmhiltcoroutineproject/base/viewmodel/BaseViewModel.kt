package com.example.mvvmhiltcoroutineproject.base.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

open class BaseViewModel @Inject constructor() : ViewModel() {

    private val _loadingStatusChannel = Channel<Boolean>()
    val loadingStatusFlow: Flow<Boolean> = _loadingStatusChannel.receiveAsFlow()

    private val _toastMessageChannel = Channel<String>()
    val toastMessageFlow: Flow<String?> = _toastMessageChannel.receiveAsFlow()

    protected fun setLoadingStatus(shouldShow: Boolean) {
        viewModelScope.launch {
            _loadingStatusChannel.send(shouldShow)
        }
    }

    protected fun showToastMessage(message: String?) {
        if (message.isNullOrBlank()) {
            return
        }
        viewModelScope.launch {
            _toastMessageChannel.send(message)
        }
    }
}