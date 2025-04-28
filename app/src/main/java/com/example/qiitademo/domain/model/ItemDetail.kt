package com.example.qiitademo.domain.model

data class ItemDetail(
    val id: String,
    val title: String,
    val body: String,
    val createdAt: String,
    val updatedAt: String,
    val url: String,
    val userId: String?,
    val userName: String?,
    val likesCount: Int?
)
