package com.example.burhan.architectureexample

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @get:Query("SELECT * FROM note_table ORDER BY priority DESC")
    val allNotes: LiveData<List<Note>>

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query(" UPDATE note_table SET isComplete = :checked WHERE id=:noteID;")
    fun setIsComplete(noteID: Int, checked: Boolean)
}