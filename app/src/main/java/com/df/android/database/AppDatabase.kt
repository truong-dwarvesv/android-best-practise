package com.df.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.df.android.data.model.GithubUserDto
import com.df.android.database.dao.GithubUserDao

@Database(entities = [GithubUserDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): GithubUserDao

    abstract fun userDetailDao(): GithubUserDao

}


