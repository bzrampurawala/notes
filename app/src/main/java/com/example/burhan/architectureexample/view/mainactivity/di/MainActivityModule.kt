package com.example.burhan.architectureexample.view.mainactivity.di

import com.example.burhan.architectureexample.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @MainActivityScope
    @Provides
    fun providesNoteRepository(): NoteRepository = NoteRepository()
}