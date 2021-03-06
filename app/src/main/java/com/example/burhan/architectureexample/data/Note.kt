package com.example.burhan.architectureexample.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(val title: String, val description: String, val priority: Int, val isComplete: Boolean) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}