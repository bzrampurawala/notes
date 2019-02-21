package com.example.burhan.architectureexample.di

import android.app.Application
import com.example.burhan.architectureexample.db.NoteDao
import com.example.burhan.architectureexample.db.NoteDatabase
import com.example.burhan.architectureexample.repository.NoteRepository
import com.example.burhan.architectureexample.view.mainactivity.NoteViewModel
import com.example.burhan.architectureexample.view.mainactivity.di.MainActivityComponent
import com.example.burhan.architectureexample.view.mainactivity.di.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            DbModule::class
        ]
)
interface ApplicationComponent {

    fun inject(noteRepository: NoteRepository)
    fun plus(mainActivityModule: MainActivityModule): MainActivityComponent

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}