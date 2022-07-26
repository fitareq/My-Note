package com.example.mynote.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynote.dao.NotesDao

abstract class NotesDatabase : RoomDatabase(){

    abstract fun myNotesDao():NotesDao

    companion object{
        @Volatile
        var INSTANCE: NotesDatabase?=null

        fun getDatabaseInstance():NotesDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this)
            {

            }
        }
    }
}