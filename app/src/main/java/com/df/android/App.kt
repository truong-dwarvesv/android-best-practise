package com.df.android

import android.app.Application
import androidx.room.Room
import com.df.android.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object {
        private lateinit var instance: App
        fun shared(): App {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }


    val appDatabase: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "local-database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}