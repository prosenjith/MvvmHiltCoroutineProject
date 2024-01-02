package com.example.mvvmhiltcoroutineproject.di.module

import com.example.mvvmhiltcoroutineproject.data.session.AppPreference
import com.example.mvvmhiltcoroutineproject.data.session.Session
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SessionModule {

    @Binds
    abstract fun bindSession(preference: AppPreference): Session
}