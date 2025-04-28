package com.example.qiitademo.domain.usecase

import android.util.Log
import com.example.qiitademo.common.NetworkResponse
import com.example.qiitademo.data.remote.toItems
import com.example.qiitademo.domain.model.Item
import com.example.qiitademo.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchItemsUseCase @Inject constructor(
    private val repository: ItemRepository
) {
    operator fun invoke(query: String): Flow<NetworkResponse<List<Item>>> = flow {
        try {
            emit(NetworkResponse.Loading<List<Item>>())
            Log.d("Loading", "loadingoading")
            val items = repository.searchItems(query).toItems()
            Log.d("Success", "kd")
            emit(NetworkResponse.Success<List<Item>>(items))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(NetworkResponse.Failure<List<Item>>(e.message.toString()))
        }
    }
}