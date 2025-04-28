package com.example.qiitademo.di

import com.example.qiitademo.common.Constants.BASE_URL
import com.example.qiitademo.data.remote.QiitaApi
import com.example.qiitademo.data.repository.ItemRepositoryImpl
import com.example.qiitademo.domain.repository.ItemRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQiitaApi(): QiitaApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(QiitaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideItemRepository(api: QiitaApi): ItemRepository {
        return ItemRepositoryImpl(api)
    }
}
