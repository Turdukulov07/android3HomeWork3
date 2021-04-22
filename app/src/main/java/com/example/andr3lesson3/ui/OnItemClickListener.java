package com.example.andr3lesson3.ui;
import com.example.andr3lesson3.data.model.Notes;

public interface OnItemClickListener {

    void onClick(int position, Notes notes);
    void longClick(int position, Notes notes);
}
