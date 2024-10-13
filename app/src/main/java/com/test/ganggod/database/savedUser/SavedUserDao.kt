package com.test.ganggod.database.savedUser

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedUserDao {
    @Insert
    suspend fun insertUser(savedUser: SavedUser)

    @Query("SELECT * FROM saved_user")
    fun selectAll(): Flow<List<SavedUser>>

    @Query("DELETE FROM saved_user WHERE id = :id")
    suspend fun deleteSavedUSer(id:Long)

}