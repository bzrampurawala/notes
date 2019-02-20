package com.example.burhan.architectureexample.di

import com.example.burhan.architectureexample.view.NoteViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule{

    @Singleton
    @Provides
    fun providesNoteViewModel(): NoteViewModel = NoteViewModel()
}