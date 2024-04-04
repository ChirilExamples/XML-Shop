package com.example.afinal.di

import android.content.Context
import com.example.afinal.data.ShoppingRepository
import com.example.afinal.data.local.AppDatabase
import com.example.afinal.data.local.ShoppingDAO
import com.example.afinal.data.remote.ShoppingRemoteDatasource
import com.example.afinal.data.remote.ShoppingService
import com.example.afinal.domain.Repository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Injection {

    @Provides
    fun provideGSON(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideAPIService(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideShoppingsService(retrofit: Retrofit): ShoppingService =
        retrofit.create(ShoppingService::class.java)

    @Singleton
    @Provides
    fun provideRocketRemoteDatasource(shoppingService: ShoppingService) =
        ShoppingRemoteDatasource(shoppingService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext applicationContext: Context) =
        AppDatabase.getDatabase(applicationContext)

    @Singleton
    @Provides
    fun provideRocketDAO(appDatabase: AppDatabase) = appDatabase.shoppingDAO()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDatasource: ShoppingRemoteDatasource,
        localDataSource: ShoppingDAO
    ): Repository = ShoppingRepository(remoteDatasource, localDataSource)
}
