package com.df.android.utils

import android.content.Context
import android.net.ConnectivityManager
import com.df.android.App
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


object Utils {
    //Reformat
    fun reformatDate(timeStamp: String): String {
        return try {
            val date1 = timeStamp2Date(timeStamp)
            val df2: DateFormat = SimpleDateFormat("hh:mm aa MMMM dd, yyyy")
            df2.format(date1)
        } catch (e: Exception) {
            e.printStackTrace()
            timeStamp
        }
    }
    //String to date
    fun timeStamp2Date(timeStamp: String): Date? {
        return try {
            val df1: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSX")
            df1.parse(timeStamp)
        } catch (e: Exception) {
            null
        }
    }

    fun isToday(date: Date): Boolean {
        val today = Date()
        val fmt = SimpleDateFormat("yyyyMMdd")
        return fmt.format(date) == fmt.format(today)
    }

    fun isNetworkConnected(): Boolean {
        (App.shared().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            return@isNetworkConnected getNetworkCapabilities(activeNetwork) != null
        }
    }

}