package com.example.burhan.architectureexample

import android.view.*
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.*


class NoteAdapter(val onCheckChanged: OnCheckChanged, val onItemClick: OnItemClick) : ListAdapter<Note, NoteAdapter.NoteHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.note_item, parent, false)
        return NoteHolder(itemView, onCheckChanged, onItemClick)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        val currentNote = getItem(position)
        holder.currentNote = currentNote
        holder.textViewTitle.text = currentNote.title
        holder.textViewDescription.text = currentNote.description
        holder.textViewPriority.text = currentNote.priority.toString()
        holder.checkboxIsComplete.isChecked = currentNote.isComplete
//        holder.checkboxIsComplete.setOnClickListener {
//            val checked = holder.checkboxIsComplete.isChecked
//            if (position != RecyclerView.NO_POSITION)
//                holder.checkBoxListener.onCheckBoxClicked(getItem(position).id!!, checked)
//        }
//        holder.itemView.setOnClickListener {
//            if (position != RecyclerView.NO_POSITION)
//                entireViewListener.onItemClick(getItem(position))
//        }
    }


    fun getNoteAt(position: Int): Note {
        return getItem(position)
    }

    inner class NoteHolder(itemView: View, private val onCheckChanged: OnCheckChanged, private val onItemClick: OnItemClick) : RecyclerView.ViewHolder(itemView), CompoundButton.OnCheckedChangeListener, View.OnClickListener {

        var currentNote: Note? = null
            set(value) {field = value}
        val textViewTitle: TextView = itemView.findViewById(R.id.text_view_title) as TextView
        val textViewDescription: TextView = itemView.findViewById(R.id.text_view_description) as TextView
        val textViewPriority: TextView = itemView.findViewById(R.id.text_view_priority) as TextView
        val checkboxIsComplete: CheckBox = itemView.findViewById(R.id.checkbox_is_complete) as CheckBox
        init {
            checkboxIsComplete.setOnCheckedChangeListener(this)
            itemView.setOnClickListener(this)
        }

        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            val checked = checkboxIsComplete.isChecked
            onCheckChanged.setIsComplete(currentNote!!.id, checked)
        }

        override fun onClick(v: View?) {
            onItemClick.onClick(currentNote!!)
        }
    }

    interface OnCheckChanged{
        fun setIsComplete(id: Int, checked: Boolean)
    }

    interface OnItemClick {
        fun onClick(note: Note)
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
