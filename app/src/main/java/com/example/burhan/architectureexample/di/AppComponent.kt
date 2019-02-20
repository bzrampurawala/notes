package com.example.burhan.architectureexample.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.burhan.architectureexample.db.NoteDao
import com.example.burhan.architectureexample.db.NoteDatabase
import com.example.burhan.architectureexample.repository.NoteRepository
import com.example.burhan.architectureexample.view.NoteViewModel
import com.example.burhan.architectureexample.view.mainactivity.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            RepositoryModule::class,
            DbModule::class,
            ViewModelModule::class
        ]
)
interface AppComponent {

    fun getNoteRepository(): NoteRepository

    fun getNoteDao(): NoteDao

    fun getNoteDatabase(): NoteDatabase

    fun getNoteViewModel(): NoteViewModel

    fun inject(mainActivity: MainActivity)
    fun inject(noteViewModel: NoteViewModel)
    fun inject(noteRepository: NoteRepository)
    fun inject(noteDao: NoteDao)
    fun inject(noteDatabase: NoteDatabase)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}