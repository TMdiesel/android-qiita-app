package com.example.qiitademo.data.remote


import com.example.qiitademo.domain.model.ItemDetail
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

fun ItemDetailDto.toItemDetail(): ItemDetail? {
    val id = this.id
    val title = this.title
    val body = this.body
    val createdAt = this.createdAt
    val updatedAt = this.updatedAt
    val url = this.url

    return if (id != null && title != null && body != null && createdAt != null && updatedAt != null && url != null) {
        ItemDetail(
            id = id,
            title = title,
            body = body,
            createdAt = createdAt,
            updatedAt = updatedAt,
            url = url,
            userId = this.user?.id,
            userName = this.user?.name,
            likesCount = this.likesCount
        )
    } else {
        null
    }
}