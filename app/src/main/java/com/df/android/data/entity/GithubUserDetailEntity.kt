package com.df.android.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.df.android.data.dto.model.GithubUserDetailDto

@Entity(tableName = "githubUserDetail")
data class GithubUserDetailEntity(
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
    val siteAdmin: Boolean = false,
    val name: String = "",
    val company: String = "",
    val blog: String = "",
    val location: String = "",
    val email: String = "",
    val hireable: String = "",
    val bio: String = "",
    val twitterUsername: String = "",
    val publicRepos: Int = 0,
    val publicGists: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    val createdAt: String = "",
    val updatedAt: String = ""
)

fun GithubUserDetailDto.toEntity(): GithubUserDetailEntity {
    return GithubUserDetailEntity(
        id = this.id ?: -1,
        login = this.login.orEmpty(),
        nodeId = this.nodeId.orEmpty(),
        avatarUrl = this.avatarUrl.orEmpty(),
        gravatarId = this.gravatarId.orEmpty(),
        url = this.url.orEmpty(),
        htmlUrl = this.htmlUrl.orEmpty(),
        followersUrl = this.followersUrl.orEmpty(),
        followingUrl = this.followingUrl.orEmpty(),
        gistsUrl = this.gistsUrl.orEmpty(),
        starredUrl = this.starredUrl.orEmpty(),
        subscriptionsUrl = this.subscriptionsUrl.orEmpty(),
        organizationsUrl = this.organizationsUrl.orEmpty(),
        reposUrl = this.reposUrl.orEmpty(),
        eventsUrl = this.eventsUrl.orEmpty(),
        receivedEventsUrl = this.receivedEventsUrl.orEmpty(),
        type = this.type.orEmpty(),
        siteAdmin = this.siteAdmin ?: false,
        name = this.name.orEmpty(),
        company = this.company.orEmpty(),
        blog = this.blog.orEmpty(),
        location = this.location.orEmpty(),
        email = this.email.orEmpty(),
        hireable = this.hireable.orEmpty(),
        bio = this.bio.orEmpty(),
        twitterUsername = this.twitterUsername.orEmpty(),
        publicRepos = this.publicRepos ?: 0,
        publicGists = this.publicGists ?: 0,
        followers = this.followers ?: 0,
        following = this.following ?: 0,
        createdAt = this.createdAt.orEmpty(),
        updatedAt = this.updatedAt.orEmpty()
    )
}