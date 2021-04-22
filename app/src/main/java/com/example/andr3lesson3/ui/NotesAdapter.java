package com.example.andr3lesson3.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andr3lesson3.R;
import com.example.andr3lesson3.data.model.Notes;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Notes> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<Notes> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setClick(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void deleteNotes(Notes notes) {
        list.remove(notes);
        notifyDataSetChanged();
    }

    public Notes getNotes(int position) {
        return list.get(position);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textTitle;
        private TextView textContent;
        private TextView textUser;
        private TextView textGroup;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.txtViewTitle);
            textContent = itemView.findViewById(R.id.txtViewContent);
            textUser = itemView.findViewById(R.id.txtViewUser);
            textGroup = itemView.findViewById(R.id.txtViewGroup);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getAdapterPosition(),
                            list.get(getAdapterPosition()));
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.longClick(getAdapterPosition(),
                            list.get(getAdapterPosition()));
                    return true;
                }
            });
        }

        public void bind(Notes notes) {
            textTitle.setText(notes.getTitle());
            textContent.setText(notes.getContent());
            textUser.setInputType(notes.getUser());
            textGroup.setInputType(notes.getGroup());
        }
    }
}
