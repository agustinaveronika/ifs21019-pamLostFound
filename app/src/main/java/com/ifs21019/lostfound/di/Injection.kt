package com.ifs21019.lostfound.di

import android.content.Context
import com.ifs21019.lostfound.data.pref.UserPreference
import com.ifs21019.lostfound.data.pref.dataStore
import com.ifs21019.lostfound.data.remote.retrofit.ApiConfig
import com.ifs21019.lostfound.data.remote.retrofit.IApiService
import com.ifs21019.lostfound.data.repository.AuthRepository
import com.ifs21019.lostfound.data.repository.ObjectRepository
import com.ifs21019.lostfound.data.repository.UserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideAuthRepository(context: Context): AuthRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService: IApiService = ApiConfig.getApiService(user.token)
        return AuthRepository.getInstance(pref, apiService)
    }
    fun provideUserRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService: IApiService = ApiConfig.getApiService(user.token)
        return UserRepository.getInstance(apiService)
    }
    fun provideObjectRepository(context: Context): ObjectRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking { pref.getSession().first() }
        val apiService: IApiService = ApiConfig.getApiService(user.token)
        return ObjectRepository.getInstance(apiService)
    }
}