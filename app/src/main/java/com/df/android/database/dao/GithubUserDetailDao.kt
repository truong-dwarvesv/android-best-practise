package com.df.android.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.df.android.data.entity.GithubUserDetailEntity

@Dao
interface GithubUserDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: GithubUserDetailEntity): Long

    @Query("SELECT * FROM githubUserDetail WHERE login =:userId")
    suspend fun getUserDetail(userId: String): GithubUserDetailEntity?

    @Query("DELETE FROM githubUserDetail")
    fun deleteAll()

}


