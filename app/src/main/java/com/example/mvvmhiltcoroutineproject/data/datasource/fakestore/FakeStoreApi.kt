package com.example.mvvmhiltcoroutineproject.data.datasource.fakestore

import com.example.mvvmhiltcoroutineproject.data.network.onResponse
import com.example.mvvmrxjavaproject.data.model.Product
import javax.inject.Inject

class FakeStoreApi @Inject constructor(private val service: FakeStoreService) {

    suspend fun fetchProducts(): List<Product> {
        return service.fetchProducts()
            .onResponse()
    }
}
