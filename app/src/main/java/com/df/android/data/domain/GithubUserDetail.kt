package com.df.android.data.domain

import com.df.android.data.entity.GithubUserDetailEntity
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


fun GithubUserDetailEntity.toDomain(): GithubUserDetail {
    return GithubUserDetail(
        id = this.id,
        login = this.login,
        nodeId = this.nodeId,
        avatarUrl = this.avatarUrl,
        gravatarId = this.gravatarId,
        url = this.url,
        htmlUrl = this.htmlUrl,
        followersUrl = this.followersUrl,
        followingUrl = this.followingUrl,
        gistsUrl = this.gistsUrl,
        starredUrl = this.starredUrl,
        subscriptionsUrl = this.subscriptionsUrl,
        organizationsUrl = this.organizationsUrl,
        reposUrl = this.reposUrl,
        eventsUrl = this.eventsUrl,
        receivedEventsUrl = this.receivedEventsUrl,
        type = this.type,
        siteAdmin = this.siteAdmin,
        name = this.name,
        company = this.company,
        blog = this.blog,
        location = this.location,
        email = this.email,
        hireable = this.hireable,
        bio = this.bio,
        twitterUsername = this.twitterUsername,
        publicRepos = this.publicRepos,
        publicGists = this.publicGists,
        followers = this.followers,
        following = this.following,
        createdAt = this.createdAt,
        updatedAt = this.updatedAt
    )
}