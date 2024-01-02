package com.example.mvvmhiltcoroutineproject.di.module

import com.example.mvvmhiltcoroutineproject.data.repository.FakeStoreRepository
import com.example.mvvmhiltcoroutineproject.data.repository.FakeStoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFakeRepository(repository: FakeStoreRepositoryImpl): FakeStoreRepository
}