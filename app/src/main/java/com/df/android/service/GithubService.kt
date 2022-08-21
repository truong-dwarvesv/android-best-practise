package com.df.android.service

import com.df.android.data.model.GithubUserDetailDto
import com.df.android.data.model.GithubUserDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users")
    suspend fun getListUsers() : Response<List<GithubUserDto>>

    @GET("users/{userId}")
    suspend fun getUserDetail(@Path("userId") userId: String) : Response<GithubUserDetailDto>

}