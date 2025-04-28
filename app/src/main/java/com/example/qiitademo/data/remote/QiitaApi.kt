package com.example.qiitademo.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface QiitaApi {
    @GET("items/")
    suspend fun searchItems(@Query("query") query: String): SearchItemsDto

    @GET("items/{id}")
    suspend fun getItemById(@Path("id") itemId: String): ItemDetailDto
}
