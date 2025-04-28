package com.example.qiitademo.data.repository

import com.example.qiitademo.data.remote.ItemDetailDto
import com.example.qiitademo.data.remote.QiitaApi
import com.example.qiitademo.data.remote.SearchItemsDto
import com.example.qiitademo.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(private val api: QiitaApi) : ItemRepository {
    override suspend fun searchItems(query: String): SearchItemsDto {
        return api.searchItems(query)
    }

    override suspend fun getItemById(itemId: String): ItemDetailDto {
        return api.getItemById(itemId)
    }
}