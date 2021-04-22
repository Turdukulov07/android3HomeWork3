package com.example.andr3lesson3.data.remote;

import com.example.andr3lesson3.data.model.Notes;
import com.example.andr3lesson3.ui.AddNotes;
import com.example.andr3lesson3.ui.DeleteNotes;
import com.example.andr3lesson3.ui.GetNotes;
import com.example.andr3lesson3.ui.UpdateNotes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaveNotes {

        public static void getNotes(GetNotes getNotes) {
            RetrofitBuilder
                    .getInstance()
                    .getNotes()
                    .enqueue(new Callback<List<Notes>>() {
                        @Override
                        public void onResponse(Call<List<Notes>> call, Response<List<Notes>> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                getNotes.onSuccess(response.body());
                            } else {
                                getNotes.onFailure("Response is not successful");
                            }
                        }

                        @Override
                        public void onFailure(Call<List<Notes>> call, Throwable t) {
                            getNotes.onFailure(t.getLocalizedMessage());
                        }
                    });

        }

    public static void addNotes(Notes notes, AddNotes addNotes){
        RetrofitBuilder.getInstance()
                .addNotes(notes)
                .enqueue(new Callback<Notes>() {
                    @Override
                    public void onResponse(Call<Notes> call, Response<Notes> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            addNotes.onSuccess(response.body());
                        } else {
                            addNotes.onFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Notes> call, Throwable t) {
                        addNotes.onFailure(t.getLocalizedMessage());
                    }
                });
    }

    public static void deleteNotes (String id, DeleteNotes deleteNotes){
        RetrofitBuilder.getInstance()
                .deleteNotes(id)
                .enqueue(new Callback<Notes>() {
                    @Override
                    public void onResponse(Call<Notes> call, Response<Notes> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            deleteNotes.onSuccess(response.body());
                        } else {
                            deleteNotes.onFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Notes> call, Throwable t) {
                        deleteNotes.onFailure(t.getLocalizedMessage());
                    }
                });
    }

    public static void updateNotes (String id, Notes notes, UpdateNotes updateNotes){
        RetrofitBuilder.getInstance()
                .updateNotes(id, notes)
                .enqueue(new Callback<Notes>() {
                    @Override
                    public void onResponse(Call<Notes> call, Response<Notes> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            updateNotes.onSuccess(response.body());
                        } else {
                            updateNotes.onFailure(response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<Notes> call, Throwable t) {
                        updateNotes.onFailure(t.getLocalizedMessage());
                    }
                });
    }

}