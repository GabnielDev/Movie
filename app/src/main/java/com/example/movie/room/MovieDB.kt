package com.example.movie.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movie.remote.response.ResponsePeople

//@Database(
//    entities = [ResponsePeople::class],
//    version = 1
//)

abstract class MovieDB : RoomDatabase() {

//    companion object {
//        @Volatile
//        private var INSTANCE: MovieDB? = null
//
//        fun getInstance(context: Context): MovieDB {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(MovieDB::class) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    MovieDB::class.java,
//                    "movie.db"
//                ).build()
//
//                INSTANCE = instance
//                return instance
//
//            }
//        }
//
//    }

}