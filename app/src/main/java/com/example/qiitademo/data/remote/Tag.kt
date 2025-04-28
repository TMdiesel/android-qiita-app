package com.example.qiitademo.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tag(
    val name: String?,
    val versions: List<String?>?
)