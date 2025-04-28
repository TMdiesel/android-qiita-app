package com.example.qiitademo.domain.model

data class Item(
    val id: String,
    val title: String,
    val createdAt: String,
    val updatedAt: String,
    val userId: String?,
    val userName: String?,
    val likesCount: Int?
)
