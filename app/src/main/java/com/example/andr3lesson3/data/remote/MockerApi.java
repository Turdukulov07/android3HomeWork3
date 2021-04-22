package com.example.andr3lesson3.data.remote;

import com.example.andr3lesson3.data.model.Notes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MockerApi {

    @GET(NotesKeys.KEY_NOTES)
    Call<List<Notes>> getNotes();

    @POST(NotesKeys.KEY_NOTES)
    Call<Notes> addNotes(@Body Notes notes);

    @DELETE(NotesKeys.KEY_NOTES_ID)
    Call<Notes> deleteNotes(@Path("id") String id);

    @PUT(NotesKeys.KEY_NOTES_ID)
    Call<Notes> updateNotes(@Path("id") String id, @Body Notes notes);

}
