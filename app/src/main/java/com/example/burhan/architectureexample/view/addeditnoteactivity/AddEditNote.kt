package com.example.burhan.architectureexample.view.addeditnoteactivity

import android.content.Intent
import android.os.Bundle
import android.widget.*

import kotlinx.android.synthetic.main.activity_add_note.*

import androidx.appcompat.app.AppCompatActivity
import com.example.burhan.architectureexample.R

class AddEditNote : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "com.codinginflow.architectureexample.EXTRA_ID"
        const val EXTRA_TITLE = "com.codinginflow.architectureexample.EXTRA_TITLE"
        const val EXTRA_DESCRIPTION = "com.codinginflow.architectureexample.EXTRA_DESCRIPTION"
        const val EXTRA_PRIORITY = "com.codinginflow.architectureexample.EXTRA_PRIORITY"
        const val IS_COMPLETE = "com.codinginflow.architectureexample.IS_COMPLETE"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        numberPickerPriority.minValue = 1
        numberPickerPriority.maxValue = 10

        val intent = intent
        if (intent.hasExtra(EXTRA_ID)) {
            title = "Edit Note"
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE))
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION))
            numberPickerPriority.value = intent.getIntExtra(EXTRA_PRIORITY, 1)
        }
        else title = "Add Note"

        buttonSaveNote.setOnClickListener{
                val title = editTextTitle.text.toString()
                val description = editTextDescription.text.toString()
                val priority = numberPickerPriority.value

                if (title.trim().isEmpty() || description.trim().isEmpty())
                    Toast.makeText(this@AddEditNote, "Please insert a title and description", Toast.LENGTH_SHORT).show()
                else{
                    val data = Intent()
                    data.putExtra(EXTRA_TITLE, title)
                    data.putExtra(EXTRA_DESCRIPTION, description)
                    data.putExtra(EXTRA_PRIORITY, priority)

                    val id = getIntent().getIntExtra(EXTRA_ID, -1)
                    val isComplete = getIntent().getBooleanExtra(IS_COMPLETE, false)
                    if (id != -1) {
                        data.putExtra(EXTRA_ID, id)
                        data.putExtra(IS_COMPLETE, isComplete)
                    }

                    setResult(RESULT_OK, data)
                    finish()
                }

        }
    }

}