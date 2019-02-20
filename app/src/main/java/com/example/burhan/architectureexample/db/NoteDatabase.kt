package com.example.burhan.architectureexample.db


import androidx.room.*
import com.example.burhan.architectureexample.MainApplication
import com.example.burhan.architectureexample.data.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    init {
        MainApplication.applicationComponent.inject(this)
    }
    abstract fun noteDao(): NoteDao

//    companion object {
//
//        private var instance: NoteDatabase? = null
//
//        @Synchronized
//        fun getInstance(context: Context): NoteDatabase {
//            if (instance == null) instance = Room.databaseBuilder(context.applicationContext,
//                    NoteDatabase::class.java, "note_database")
//                    .fallbackToDestructiveMigration()
//                    .build()
//            return instance as NoteDatabase
//        }
//    }
}
