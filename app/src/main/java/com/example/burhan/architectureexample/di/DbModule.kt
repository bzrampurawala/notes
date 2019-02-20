package com.example.burhan.architectureexample.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.burhan.architectureexample.db.NoteDao
import com.example.burhan.architectureexample.db.NoteDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule{

    @Singleton
    @Provides
    fun provideNoteDataBase(application: Application): NoteDatabase = Room.databaseBuilder(application,
            NoteDatabase::class.java, "note_database")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideNotesDao(db: NoteDatabase): NoteDao = db.noteDao()
}