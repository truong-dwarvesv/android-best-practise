package com.df.android.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.df.android.data.entity.GithubUserEntity

@Dao
interface GithubUserDao {

    @Query("SELECT * FROM githubUser")
    suspend fun getAllGithubUsers(): List<GithubUserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<GithubUserEntity>): List<Long>

    @Query("DELETE FROM githubUser")
    fun deleteAll()

}


