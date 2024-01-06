package com.example.mvvmhiltcoroutineproject.ui.main

import androidx.lifecycle.viewModelScope
import com.example.mvvmhiltcoroutineproject.base.viewmodel.BaseViewModel
import com.example.mvvmhiltcoroutineproject.data.repository.FakeStoreRepository
import com.example.mvvmrxjavaproject.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FakeStoreRepository
) : BaseViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            repository.fetchProducts()
                .onStart { setLoadingStatus(true) }
                .catch { showToastMessage(it.localizedMessage) }
                .onCompletion { setLoadingStatus(false) }
                .collect { _products.value = it }
        }
    }
}