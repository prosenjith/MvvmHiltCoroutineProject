package com.example.mvvmhiltcoroutineproject.ui.main

import androidx.lifecycle.viewModelScope
import com.example.mvvmhiltcoroutineproject.base.viewmodel.BaseViewModel
import com.example.mvvmhiltcoroutineproject.data.repository.FakeStoreRepository
import com.example.mvvmrxjavaproject.data.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
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
                .flowOn(Dispatchers.IO)
                .catch {
                    _toastMessage.value = it.localizedMessage
                }.collect {
                    _products.value = it
                }
        }
    }
}