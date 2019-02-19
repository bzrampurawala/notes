package com.example.burhan.architectureexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository = NoteRepository(application)
    val allNotes: LiveData<List<Note>>

    init {
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