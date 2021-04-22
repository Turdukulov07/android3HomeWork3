package com.example.andr3lesson3.ui;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andr3lesson3.R;
import com.example.andr3lesson3.data.model.Notes;
import com.example.andr3lesson3.data.remote.SaveNotes;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {

    private NotesAdapter adapter;
    private RecyclerView recyclerView;
    private List<Notes> listFilm = new ArrayList<>();
    private NavController navController;
    private Button btnNewNotes;

    public NotesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NotesAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_start, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        loadingNotes();
        setClickBtn();
        setClick();
    }

    private void setClick() {
        adapter.setClick(new OnItemClickListener() {
            @Override
            public void onClick(int position, Notes notes) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("post", notes);
                navController = Navigation.findNavController(getActivity(),
                        R.id.nav_host_fragment);
                navController.navigate(R.id.secondFragment, bundle);
            }

            @Override
            public void longClick(int position, Notes notes) {
                Log.d("B", "longClick: ");
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
                alert.setTitle("Удалить?");
                alert.setMessage("Удаление");
                alert.setNegativeButton("отменить?", null);
                alert.setPositiveButton("удалить", (dialog, which) -> {
                    SaveNotes.deleteNotes(notes.getId(), new DeleteNotes() {
                                @Override
                                public void onSuccess(Notes notes) {
                                    Notes notesNew =  adapter.getNotes(position);
                                    adapter.deleteNotes(notesNew);
                                }

                                @Override
                                public void onFailure(String error) {

                                }
                            });

                });
                alert.create().show();
            }
        });
    }


    private void setClickBtn() {
        btnNewNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewFragment();
            }
        });
    }

    private void openNewFragment() {
        navController = Navigation.findNavController(getActivity(),
                R.id.nav_host_fragment);
        navController.navigate(R.id.secondFragment);
    }

    private void loadingNotes() {
        SaveNotes.getNotes(new GetNotes() {
            @Override
            public void onSuccess(List<Notes> notes) {
                adapter.addList(notes);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(String error) {
            }
        });
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.rvStartFragment);
        btnNewNotes = view.findViewById(R.id.btnNewNotes);
    }
}