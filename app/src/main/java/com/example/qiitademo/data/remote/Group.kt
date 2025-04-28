package com.example.qiitademo.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Group(
    @Json(name = "created_at")
    val createdAt: String?,
    val description: String?,
    val name: String?,
    val `private`: Boolean?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    @Json(name = "url_name")
    val urlName: String?
)