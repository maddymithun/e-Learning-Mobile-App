package com.example.composecleanarchitecture.db.room

import android.content.Context
import androidx.room.Room

class RoomHelper(private val context: Context)  {
    private val db = Room.databaseBuilder(context, RoomDB::class.java, "RAFKHATA")
                     .allowMainThreadQueries().build()
    fun getRoomDbInstance(): RoomDB {
        return db
    }
}