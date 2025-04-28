package com.example.qiitademo.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserX(
    val description: String?,
    @Json(name = "facebook_id")
    val facebookId: String?,
    @Json(name = "followees_count")
    val followeesCount: Int?,
    @Json(name = "followers_count")
    val followersCount: Int?,
    @Json(name = "github_login_name")
    val githubLoginName: String?,
    val id: String?,
    @Json(name = "items_count")
    val itemsCount: Int?,
    @Json(name = "linkedin_id")
    val linkedinId: String?,
    val location: String?,
    val name: String?,
    val organization: String?,
    @Json(name = "permanent_id")
    val permanentId: Int?,
    @Json(name = "profile_image_url")
    val profileImageUrl: String?,
    @Json(name = "team_only")
    val teamOnly: Boolean?,
    @Json(name = "twitter_screen_name")
    val twitterScreenName: String?,
    @Json(name = "website_url")
    val websiteUrl: String?
)