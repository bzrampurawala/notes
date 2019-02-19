package com.example.burhan.architectureexample


import androidx.room.*
import android.content.Context

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        private var instance: NoteDatabase? = null

        @Synchronized
        fun getInstance(context: Context): NoteDatabase {
            if (instance == null) instance = Room.databaseBuilder(context.applicationContext,
                    NoteDatabase::class.java, "note_database")
                    .fallbackToDestructiveMigration()
                    .build()
            return instance as NoteDatabase
        }
    }
}
