package com.df.android.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.df.android.data.model.GithubUserDetailDto
import kotlinx.coroutines.flow.Flow

@Dao
interface GithubUserDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: GithubUserDetailDto): Flow<Long>

    @Query("DELETE FROM githubUserDetail")
    fun deleteAll()

}


