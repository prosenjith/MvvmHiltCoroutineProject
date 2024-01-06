package com.example.mvvmhiltcoroutineproject.data.datasource.fakestore

import com.example.mvvmrxjavaproject.data.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface FakeStoreService {

    @GET("products")
    suspend fun fetchProducts(): Response<List<Product>>
}