package com.example.burhan.architectureexample.db


import androidx.room.*
import com.example.burhan.architectureexample.MainApplication
import com.example.burhan.architectureexample.data.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
