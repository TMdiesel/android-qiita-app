package com.example.qiitademo.domain.repository

import com.example.qiitademo.data.remote.ItemDetailDto
import com.example.qiitademo.data.remote.SearchItemsDto

interface ItemRepository {

    suspend fun searchItems(query: String): SearchItemsDto

    suspend fun getItemById(itemId: String): ItemDetailDto
}