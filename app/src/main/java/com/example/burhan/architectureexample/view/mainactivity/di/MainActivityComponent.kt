package com.example.burhan.architectureexample.view.mainactivity.di

import com.example.burhan.architectureexample.repository.NoteRepository
import com.example.burhan.architectureexample.view.mainactivity.NoteViewModel
import dagger.Subcomponent

@MainActivityScope
@Subcomponent(
        modules = [MainActivityModule::class]
)
interface MainActivityComponent {
    fun inject(noteViewModel: NoteViewModel)
}