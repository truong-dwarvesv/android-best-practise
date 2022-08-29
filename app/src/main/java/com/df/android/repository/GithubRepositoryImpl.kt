package com.df.android.repository

import com.df.android.data.Resource
import com.df.android.data.doOnSuccess
import com.df.android.data.domain.toDomain
import com.df.android.data.entity.toEntity
import com.df.android.database.dao.GithubUserDao
import com.df.android.database.dao.GithubUserDetailDao
import com.df.android.service.GithubService
import com.df.android.service.safeCallApi
import com.df.android.utils.dispatcher.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val service: GithubService,
    private val userDao: GithubUserDao,
    private val userDetailDao: GithubUserDetailDao,
    private val dispatcher: DispatcherProvider,
) : GithubRepository {

    override fun getGithubUsers() = getRemoteGithubUsers()
        .map { resource ->
            resource.doOnSuccess { data ->
                userDao.insertAll(data.map { it.toEntity() })
            }
            val data = userDao.getAllGithubUsers().map { entity -> entity.toDomain() }
            if (data.isEmpty() && resource.isError()) {
                Resource.Error(resource.error()!!)
            } else {
                Resource.Success(data)
            }
        }.flowOn(dispatcher.io())

    private fun getRemoteGithubUsers() = flow {
        val result = safeCallApi {
            service.getListUsers()
        }
        emit(result)
    }

    override fun getUserDetail(userId: String) = getRemoteUserDetail(userId)
        .map { resource ->
            resource.doOnSuccess { user ->
                userDetailDao.insert(user.toEntity())
            }
            val user = userDetailDao.getUserDetail(userId)
            if (user == null && resource.isError()) {
                Resource.Error(resource.error()!!)
            } else {
                Resource.Success(user!!.toDomain())
            }
        }.flowOn(dispatcher.io())

    private fun getRemoteUserDetail(userId: String) = flow {
        val result = safeCallApi {
            service.getUserDetail(userId)
        }
        emit(result)
    }

}