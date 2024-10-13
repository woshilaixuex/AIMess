package com.test.ganggod.database.savedUser

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_user")
data class SavedUser(
    @PrimaryKey
    val id:Long,
    val userName:String,
    val password:String,
    val email:String
)
