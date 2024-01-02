package com.example.mvvmhiltcoroutineproject.data.repository

import com.example.mvvmrxjavaproject.data.model.Product
import kotlinx.coroutines.flow.Flow

interface FakeStoreRepository {
    suspend fun fetchProducts(): Flow<List<Product>>
}