package com.example.qiitademo.data.remote


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemDetailDto(
    val body: String?,
    val coediting: Boolean?,
    @Json(name = "comments_count")
    val commentsCount: Int?,
    @Json(name = "created_at")
    val createdAt: String?,
    val group: GroupX?,
    val id: String?,
    @Json(name = "likes_count")
    val likesCount: Int?,
    @Json(name = "organization_url_name")
    val organizationUrlName: String?,
    @Json(name = "page_views_count")
    val pageViewsCount: Int?,
    val `private`: Boolean?,
    @Json(name = "reactions_count")
    val reactionsCount: Int?,
    @Json(name = "rendered_body")
    val renderedBody: String?,
    val slide: Boolean?,
    @Json(name = "stocks_count")
    val stocksCount: Int?,
    val tags: List<TagX>?,
    @Json(name = "team_membership")
    val teamMembership: TeamMembershipX?,
    val title: String?,
    @Json(name = "updated_at")
    val updatedAt: String?,
    val url: String?,
    val user: UserX?
)