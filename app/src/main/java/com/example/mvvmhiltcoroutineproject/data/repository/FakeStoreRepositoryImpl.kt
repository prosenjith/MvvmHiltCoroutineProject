package com.example.mvvmhiltcoroutineproject.data.repository

import android.content.Context
import com.example.mvvmhiltcoroutineproject.data.datasource.fakestore.FakeStoreApi
import com.example.mvvmhiltcoroutineproject.data.network.onException
import com.example.mvvmrxjavaproject.data.model.Product
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ViewModelScoped
class FakeStoreRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val api: FakeStoreApi
) : FakeStoreRepository {
    override suspend fun fetchProducts(): Flow<List<Product>> {
        return flow {
            emit(api.fetchProducts())
        }.onException(context)
    }
}