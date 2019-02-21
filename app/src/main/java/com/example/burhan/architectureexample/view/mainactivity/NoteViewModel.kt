package com.example.burhan.architectureexample.view.mainactivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.burhan.architectureexample.MainApplication
import com.example.burhan.architectureexample.data.Note
import com.example.burhan.architectureexample.di.ApplicationComponent
import com.example.burhan.architectureexample.repository.NoteRepository
import com.example.burhan.architectureexample.view.mainactivity.di.MainActivityModule
import javax.inject.Inject

class NoteViewModel: ViewModel() {

    @Inject
    lateinit var repository: NoteRepository

    val allNotes: LiveData<List<Note>>

    init {
        MainApplication.applicationComponent.plus(MainActivityModule()).inject(this)
        allNotes = repository.allNotes
    }

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun update(note: Note) {
        repository.update(note)
    }

    fun delete(note: Note) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    fun setIsComplete(id: Int, checked: Boolean) {
        repository.setIsComplete(id, checked)
    }
}