package com.example.burhan.architectureexample.view.mainactivity.di

import com.example.burhan.architectureexample.view.mainactivity.NoteViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule{

    @MainActivityScope
    @Provides
    fun providesNoteViewModel(): NoteViewModel = NoteViewModel()
}