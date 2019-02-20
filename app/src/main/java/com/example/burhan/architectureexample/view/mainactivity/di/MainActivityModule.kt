package com.example.burhan.architectureexample.view.mainactivity.di

import com.example.burhan.architectureexample.view.mainactivity.MainActivity
import com.example.burhan.architectureexample.view.mainactivity.NoteViewModel
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule(private val mainActivity: MainActivity){

    @MainActivityScope
    @Provides
    fun providesNoteViewModel(): NoteViewModel = NoteViewModel()

    @MainActivityScope
    @Provides
    fun providesMainActivity(): MainActivity = mainActivity
}