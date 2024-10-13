package com.test.ganggod.database.savedUser

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SavedUser::class], version = 1, exportSchema = false)
abstract class SavedUserDatabase:RoomDatabase() {
    abstract fun chatHistoryDao(): SavedUserDao

    companion object{
        @Volatile
        private var INSTANCE: SavedUserDatabase? = null
        fun getDatabase(context: Context): SavedUserDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SavedUserDatabase::class.java,
                    "Game"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

}