package com.example.mvvmhiltcoroutineproject.data.session

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreference @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Session {
}