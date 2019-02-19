package com.example.burhan.architectureexample

import android.view.*
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.*


class NoteAdapter : ListAdapter<Note, NoteAdapter.NoteHolder>(DIFF_CALLBACK) {
    lateinit var entireViewListener: OnItemClickListener
    lateinit var checkBoxListener: OnCheckBoxClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = getItem(position)
        holder.textViewTitle.text = currentNote.title
        holder.textViewDescription.text = currentNote.description
        holder.textViewPriority.setText(currentNote.priority)
        holder.checkboxIsComplete.isChecked = currentNote.isComplete
    }


    fun getNoteAt(position: Int): Note {
        return getItem(position)
    }

    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.text_view_title) as TextView
        val textViewDescription: TextView = itemView.findViewById(R.id.text_view_description) as TextView
        val textViewPriority: TextView = itemView.findViewById(R.id.text_view_priority) as TextView
        val checkboxIsComplete: CheckBox = itemView.findViewById(R.id.checkbox_is_complete) as CheckBox

        init {

            checkboxIsComplete.setOnClickListener{
                val checked = checkboxIsComplete.isChecked
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                   checkBoxListener.onCheckBoxClicked(, checked)
            }

            itemView.setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION)
                    entireViewListener.onItemClick()
            }

        }
    }

    interface OnItemClickListener {
        fun onItemClick(note: Note)
    }

    interface OnCheckBoxClickListener {
        fun onCheckBoxClicked(id: Int, checked: Boolean)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.entireViewListener = listener
    }

    fun setOnCheckBoxClickListener(listener: OnCheckBoxClickListener) {
        this.checkBoxListener = listener
    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.title == newItem.title &&
                        oldItem.description == newItem.description &&
                        oldItem.priority == newItem.priority &&
                        oldItem.isComplete == newItem.isComplete
            }
        }
    }
}
