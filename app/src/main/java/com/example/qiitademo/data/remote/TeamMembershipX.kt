package com.example.qiitademo.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TeamMembershipX(
    val name: String?
)