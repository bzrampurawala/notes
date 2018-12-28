package com.example.burhan.architectureexample;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.*;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY priority DESC")
    LiveData<List<Note>> getAllNotes();

    @Query(" UPDATE note_table SET isComplete = :val WHERE id=:noteID;")
    void setIsComplete(int noteID, boolean val);
}