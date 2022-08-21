package com.df.android.di

import android.content.Context
import androidx.room.Room
import com.df.android.database.AppDatabase
import com.df.android.database.dao.GithubUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "local-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideGithubUserDao(database: AppDatabase): GithubUserDao {
        return database.userDao()
    }
}