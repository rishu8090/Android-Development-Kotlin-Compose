package com.example.week7.day1_pagination

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchRepositoryResponse(
    @Json(name = "total_count")
    val totalCount: Int,
    val items: List<Repository>,
)

@JsonClass(generateAdapter = true)
data class Repository(
    val id: Long,
    val name: String,
    val owner: RepositoryOwner,
    @Json(name = "html_url")
    val url: String,
    val description: String?,
    @Json(name = "stargazers_count")
    val stars: Int,
)

@JsonClass(generateAdapter = true)
data class RepositoryOwner(
    val id: Long,
    @Json(name = "login")
    val login: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
)

