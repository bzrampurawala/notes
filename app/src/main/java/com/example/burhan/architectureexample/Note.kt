package com.example.burhan.architectureexample

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
class Note(val title: String, val description: String, val priority: Int, val isComplete: Boolean) {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
        set(value){
            field = value
        }
        get() = field
}