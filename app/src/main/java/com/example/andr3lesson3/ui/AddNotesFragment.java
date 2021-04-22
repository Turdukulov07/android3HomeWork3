package com.example.andr3lesson3.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.andr3lesson3.R;
import com.example.andr3lesson3.data.model.Notes;
import com.example.andr3lesson3.data.remote.SaveNotes;

public class AddNotesFragment extends Fragment {

    private EditText editTitle;
    private EditText editContent;
    private EditText editUser;
    private EditText editGroup;
    private Button btnSave;
    private Notes notes;
    private NavController navController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            notes = (Notes) getArguments().getSerializable("post");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        setClick();
    }

    private void init(View view) {
        editTitle = view.findViewById(R.id.editTitle);
        editContent = view.findViewById(R.id.editContent);
        editUser = view.findViewById(R.id.editUser);
        editGroup = view.findViewById(R.id.editGroup);
        btnSave = view.findViewById(R.id.btnSave);

        if (notes != null) {
            editTitle.setText(notes.getTitle());
            editContent.setText(notes.getContent());
            editUser.setInputType(notes.getUser());
            editGroup.setInputType(notes.getGroup());
        }
    }

    private void setClick() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (notes != null) {
                    addNotes();
                    SaveNotes.updateNotes(notes.getId(), notes, new UpdateNotes() {
                        @Override
                        public void onSuccess(Notes notes) {
                            Toast.makeText(getContext(), "Update", Toast.LENGTH_SHORT).show();
                            close();
                        }

                        @Override
                        public void onFailure(String error) {
                        }
                    });
                } else {
                    notes = new Notes();
                    addNotes();
                    SaveNotes.addNotes(notes, new AddNotes() {
                        @Override
                        public void onSuccess(Notes notes) {
                            Toast.makeText(getContext(), "New notes", Toast.LENGTH_SHORT).show();
                            close();
                        }

                        @Override
                        public void onFailure(String error) {
                        }
                    });

                }
            }
        });
    }

    private void close() {
        navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment);
        navController.navigateUp();
    }

    private void addNotes() {
        notes.setTitle(editTitle.getText().toString());
        notes.setContent(editContent.getText().toString());
        int user = Integer.parseInt(editUser.getText().toString());
        int group = Integer.parseInt(editGroup.getText().toString());
        notes.setUser(user);
        notes.setGroup(group);

    }
}

