package com.example.qiitademo.data.remote

import com.example.qiitademo.domain.model.Item



typealias SearchItemsDto = List<SearchItemsDtoItem>

fun SearchItemsDto.toItems(): List<Item> {
    return this.mapNotNull { dtoItem ->
        // 必須項目が揃ってない場合はnullを返してリストから除外する
        val id = dtoItem.id
        val title = dtoItem.title
        val createdAt = dtoItem.createdAt
        val updatedAt = dtoItem.updatedAt

        if (id != null && title != null && createdAt != null && updatedAt != null) {
            Item(
                id = id,
                title = title,
                createdAt = createdAt,
                updatedAt = updatedAt,
                userId = dtoItem.user?.id,    // user自体がnullならuserIdもnull
                userName = dtoItem.user?.name,
                likesCount = dtoItem.likesCount
            )
        } else {
            null // 必須フィールドが欠けているので除外
        }
    }
}