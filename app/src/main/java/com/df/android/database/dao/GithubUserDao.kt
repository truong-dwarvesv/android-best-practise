package com.df.android.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.df.android.data.model.GithubUserDto
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubUserDao {

    @Query("SELECT * FROM githubUser")
    fun getAllGithubUsers(): Flow<List<GithubUserDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<GithubUserDto>): Flow<List<Long>>

    @Query("DELETE FROM githubUser")
    fun deleteAll()

}


