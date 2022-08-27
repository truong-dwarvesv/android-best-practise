package com.df.android.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.df.android.data.dto.model.GithubUserDto
import java.io.Serializable

@Entity(tableName = "githubUser")
data class GithubUserEntity(
    @PrimaryKey
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
    val siteAdmin: Boolean = false
) : Serializable

fun GithubUserDto.toEntity(): GithubUserEntity{
    return GithubUserEntity(
        id = this.id,
        login  = this.login,
        nodeId  = this.nodeId,
        avatarUrl  = this.avatarUrl,
        gravatarId  = this.gravatarId,
        url  = this.url,
        htmlUrl  = this.htmlUrl,
        followersUrl  = this.followersUrl,
        followingUrl  = this.followingUrl,
        gistsUrl  = this.gistsUrl,
        starredUrl  = this.starredUrl,
        subscriptionsUrl  = this.subscriptionsUrl,
        organizationsUrl  = this.organizationsUrl,
        reposUrl  = this.reposUrl,
        eventsUrl  = this.eventsUrl,
        receivedEventsUrl  = this.receivedEventsUrl,
        type  = this.type,
        siteAdmin  = this.siteAdmin
    )
}