package com.example.burhan.architectureexample;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private Boolean isComplete;

    private int priority;

    public Note(String title, String description, int priority, boolean isComplete) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.isComplete = isComplete;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public  Boolean getIsComplete(){
        return isComplete;
    }

    public int getPriority() {
        return priority;
    }
}