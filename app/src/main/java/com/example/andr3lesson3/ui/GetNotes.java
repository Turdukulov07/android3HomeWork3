package com.example.andr3lesson3.ui;

import com.example.andr3lesson3.data.model.Notes;

import java.util.List;

public interface GetNotes {
    void onSuccess(List<Notes> posts);
    void onFailure(String error);
}
