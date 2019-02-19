package com.example.burhan.architectureexample

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import androidx.recyclerview.widget.*

class MainActivity : AppCompatActivity(), NoteAdapter.OnCheckChanged, NoteAdapter.OnItemClick{

    private lateinit var noteViewModel: NoteViewModel
    companion object {
        const val ADD_NOTE_REQUEST = 1
        const val EDIT_NOTE_REQUEST = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addNoteButton.setOnClickListener{
                val intent = Intent(this@MainActivity, AddEditNote::class.java)
                startActivityForResult(intent, ADD_NOTE_REQUEST)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        val adapter = NoteAdapter(this,this)
        recyclerView.adapter = adapter

        noteViewModel = NoteViewModel(application)
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.allNotes.observe(this, Observer<List<Note>>{ notes ->adapter.submitList(notes) })
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                noteViewModel.delete(adapter.getNoteAt(viewHolder.adapterPosition))
                Toast.makeText(this@MainActivity, "Note deleted", Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(recyclerView)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            val title = data!!.getStringExtra(AddEditNote.EXTRA_TITLE)
            val description = data.getStringExtra(AddEditNote.EXTRA_DESCRIPTION)
            val priority = data.getIntExtra(AddEditNote.EXTRA_PRIORITY, 1)

            val note = Note(title, description, priority, false)
            noteViewModel.insert(note)

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) {
            val id = data!!.getIntExtra(AddEditNote.EXTRA_ID, -1)

            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show()
                return
            }

            val title = data.getStringExtra(AddEditNote.EXTRA_TITLE)
            val description = data.getStringExtra(AddEditNote.EXTRA_DESCRIPTION)
            val isComplete = data.getBooleanExtra(AddEditNote.IS_COMPLETE, false)
            val priority = data.getIntExtra(AddEditNote.EXTRA_PRIORITY, 1)

            val note = Note(title, description, priority, isComplete)
            note.id = id
            noteViewModel.update(note)

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.delete_all_notes -> {
                noteViewModel.deleteAllNotes()
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onClick(note: Note) {
        val intent = Intent(this@MainActivity, AddEditNote::class.java)
        intent.putExtra(AddEditNote.EXTRA_ID, note.id)
        intent.putExtra(AddEditNote.EXTRA_TITLE, note.title)
        intent.putExtra(AddEditNote.EXTRA_DESCRIPTION, note.description)
        intent.putExtra(AddEditNote.EXTRA_PRIORITY, note.priority)
        intent.putExtra(AddEditNote.IS_COMPLETE, note.isComplete)
        startActivityForResult(intent, EDIT_NOTE_REQUEST)
    }

    override fun setIsComplete(id: Int, checked: Boolean) {
        noteViewModel.setIsComplete(id, checked)
    }
}
