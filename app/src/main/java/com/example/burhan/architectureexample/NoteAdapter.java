package com.example.burhan.architectureexample;

import android.view.*;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes = new ArrayList<>();
    private OnItemClickListener entireViewListener;
    private OnCheckBoxClickListener checkBoxListener;
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = notes.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.checkboxIsComplete.setChecked(currentNote.getIsComplete());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position) {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private TextView textViewPriority;
        private CheckBox checkboxIsComplete;

        public NoteHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            checkboxIsComplete = itemView.findViewById((R.id.checkbox_is_complete));

            checkboxIsComplete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean val = checkboxIsComplete.isChecked();
                    int position = getAdapterPosition();
                    if(checkBoxListener !=null && position != RecyclerView.NO_POSITION){
                        checkBoxListener.onCheckBoxClicked(notes.get(position).getId(),val);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (entireViewListener != null && position != RecyclerView.NO_POSITION) {
                        entireViewListener.onItemClick(notes.get(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public interface OnCheckBoxClickListener{
        void onCheckBoxClicked(int id,boolean val);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.entireViewListener = listener;
    }

    public void setOnCheckBoxClickListener(OnCheckBoxClickListener listener){
        this.checkBoxListener = listener;
    }
}
