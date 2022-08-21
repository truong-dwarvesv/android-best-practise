package com.df.android.di

import com.df.android.data.NetworkResult
import com.df.android.data.model.GithubUserDetailDto
import com.df.android.data.model.GithubUserDto
import kotlinx.coroutines.flow.Flow

interface GithubRepository {

    fun getGithubUsers(): Flow<NetworkResult<List<GithubUserDto>>>

    fun getUserDetail(userId: String): Flow<NetworkResult<GithubUserDetailDto>>

}