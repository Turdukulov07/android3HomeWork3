package com.example.andr3lesson3.ui;

import com.example.andr3lesson3.data.model.Notes;

public interface AddNotes {

    void onSuccess(Notes notes);
    void onFailure(String error);
}
