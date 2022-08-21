package com.df.android.domain

import java.io.Serializable

data class GithubUserDetail(
    val id: Int = 0,
    val login: String = "",
    val nodeId: String = "",
    val avatarUrl: String = "",
    val gravatarId: String = "",
    val url: String = "",
    val htmlUrl: String = "",
    val followersUrl: String = "",
    val followingUrl: String = "",
    val gistsUrl: String = "",
    val starredUrl: String = "",
    val subscriptionsUrl: String = "",
    val organizationsUrl: String = "",
    val reposUrl: String = "",
    val eventsUrl: String = "",
    val receivedEventsUrl: String = "",
    val type: String = "",
    val siteAdmin: Boolean = false,
    val name: String?,
    val company: String = "",
    val blog: String = "",
    val location: String = "",
    val email: String = "",
    val hireable: String = "",
    val bio: String?,
    val twitterUsername: String = "",
    val publicRepos: Int = 0,
    val publicGists: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    val createdAt: String = "",
    val updatedAt: String = ""
): Serializable