package com.example.burhan.architectureexample.di


import com.example.burhan.architectureexample.repository.NoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
@Module
class RepositoryModule{

    @Singleton
    @Provides
    fun providesNoteRepository(): NoteRepository = NoteRepository()
}