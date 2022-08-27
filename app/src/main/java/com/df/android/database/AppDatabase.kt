package com.df.android.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.df.android.data.entity.GithubUserDetailEntity
import com.df.android.data.entity.GithubUserEntity
import com.df.android.database.dao.GithubUserDao
import com.df.android.database.dao.GithubUserDetailDao

@Database(entities = [GithubUserEntity::class, GithubUserDetailEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): GithubUserDao

    abstract fun userDetailDao(): GithubUserDetailDao

}


