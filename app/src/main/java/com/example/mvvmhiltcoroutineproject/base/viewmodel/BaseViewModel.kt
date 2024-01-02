package com.example.mvvmhiltcoroutineproject.base.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

open class BaseViewModel @Inject constructor(): ViewModel() {

    protected val _shouldShowLoader = SingleLiveEvent<Boolean>()
    val shouldShowLoader: LiveData<Boolean> = _shouldShowLoader

    protected val _toastMessage = SingleLiveEvent<String>()
    val toastMessage: LiveData<String> = _toastMessage
}