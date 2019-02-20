package com.example.burhan.architectureexample.repository

import android.os.AsyncTask

import androidx.lifecycle.LiveData
import com.example.burhan.architectureexample.data.Note
import com.example.burhan.architectureexample.db.NoteDao
import com.example.burhan.architectureexample.view.mainactivity.MainActivity
import javax.inject.Inject

class NoteRepository{

    @Inject
    lateinit var noteDao: NoteDao

    val allNotes: LiveData<List<Note>>

    init {
        MainActivity.applicationComponent.inject(this)
        allNotes = noteDao.allNotes
    }
    fun insert(note: Note) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note) {
        UpdateNoteAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note) {
        DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun setIsComplete(id: Int, checked: Boolean) {
        var value = 0
        if (checked) value = 1
        SetIsCompleteAsyncTask(noteDao).execute(id, value)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTask(noteDao).execute()
    }

    class InsertNoteAsyncTask constructor(private val noteDao: NoteDao) : AsyncTask<Note, Void, Unit>() {


        override fun doInBackground(vararg notes: Note) {
            noteDao.insert(notes[0])
        }
    }

    class UpdateNoteAsyncTask constructor(private val noteDao: NoteDao) : AsyncTask<Note, Void, Unit>() {

        override fun doInBackground(vararg notes: Note){
            noteDao.update(notes[0])
        }
    }

    class DeleteNoteAsyncTask constructor(private val noteDao: NoteDao) : AsyncTask<Note, Void, Unit>() {

        override fun doInBackground(vararg notes: Note){
            noteDao.delete(notes[0])
        }
    }

    class DeleteAllNotesAsyncTask constructor(private val noteDao: NoteDao) : AsyncTask<Void, Void, Unit>() {

        override fun doInBackground(vararg voids: Void){
            noteDao.deleteAllNotes()
        }
    }

    class SetIsCompleteAsyncTask constructor(private val noteDao: NoteDao) : AsyncTask<Int, Void, Unit>() {

        override fun doInBackground(vararg ints: Int?){
            if (ints[1] == 0)
                noteDao.setIsComplete(ints[0]!!, false)
            else
                noteDao.setIsComplete(ints[0]!!, true)
        }


    }
}