package com.df.android.di

import com.df.android.database.dao.GithubUserDao
import com.df.android.service.GithubService
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val service: GithubService,
    private val userDao: GithubUserDao
) : GithubRepository {

    override fun getGithubUsers() = flow {
        val result = safeCallApi {
            service.getListUsers()
        }
        emit(result)
    }


    override fun getUserDetail(userId: String) = flow {
        val result = safeCallApi {
            service.getUserDetail(userId)
        }
        emit(result)
    }

}