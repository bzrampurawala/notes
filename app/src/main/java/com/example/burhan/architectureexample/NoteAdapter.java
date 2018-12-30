package com.example.burhan.architectureexample;

import android.view.*;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.*;


public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteHolder> {
    private OnItemClickListener entireViewListener;
    private OnCheckBoxClickListener checkBoxListener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(Note oldItem, Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Note oldItem, Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority() &&
                    oldItem.getIsComplete() == newItem.getIsComplete();
        }
    };
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item, parent, false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));
        holder.checkboxIsComplete.setChecked(currentNote.getIsComplete());
    }



    public Note getNoteAt(int position) {
        return getItem(position);
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
                        checkBoxListener.onCheckBoxClicked(getItem(position).getId(),val);
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (entireViewListener != null && position != RecyclerView.NO_POSITION) {
                        entireViewListener.onItemClick(getItem(position));
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
