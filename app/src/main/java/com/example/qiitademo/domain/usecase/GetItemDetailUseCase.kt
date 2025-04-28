package com.example.qiitademo.domain.usecase

import com.example.qiitademo.common.NetworkResponse
import com.example.qiitademo.data.remote.toItemDetail
import com.example.qiitademo.domain.model.ItemDetail
import com.example.qiitademo.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetItemDetailUseCase @Inject constructor(
    private val repository: ItemRepository,
) {
    operator fun invoke(photoId: String): Flow<NetworkResponse<ItemDetail>> = flow {
        try {
            emit(NetworkResponse.Loading<ItemDetail>())
            val itemDetail = repository.getItemById(photoId).toItemDetail()
            if (itemDetail != null) {
                emit(NetworkResponse.Success(itemDetail))
            } else {
                emit(NetworkResponse.Failure<ItemDetail>("ItemDetail cannot be got"))
            }
        } catch (e: Exception) {
            emit(NetworkResponse.Failure<ItemDetail>(e.message.toString()))
        }
    }
}