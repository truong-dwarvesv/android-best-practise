package com.df.android.repository

import com.df.android.data.Resource
import com.df.android.data.domain.GithubUser
import com.df.android.data.domain.GithubUserDetail
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    fun getGithubUsers(): Flow<Resource<List<GithubUser>>>

    fun getUserDetail(userId: String): Flow<Resource<GithubUserDetail>>

}