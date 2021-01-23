package com.example.bookku.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookku.R;
import com.example.bookku.adapter.simpanAdapter;
import com.example.bookku.roomdb.AppDatabase;
import com.example.bookku.roomdb.Book;

import java.util.ArrayList;
import java.util.Arrays;

public class BacaNantiFragment extends Fragment {
    private RecyclerView rv;
    private simpanAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AppDatabase database;
    private ArrayList<Book> daftarbook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_baca_nanti, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View v = view;

        rv = v.findViewById(R.id.dataItem);

        daftarbook = new ArrayList<>();

        database = Room.databaseBuilder(v.getContext()
                .getApplicationContext(),
                AppDatabase.class,
                "dbBook")
                .allowMainThreadQueries()
                .build();

        daftarbook.addAll(Arrays.asList(database.bookDAO().readDataBook()));
        rv.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(v.getContext());
        rv.setLayoutManager(layoutManager);

        adapter = new simpanAdapter(daftarbook, database, v.getContext());
        rv.setAdapter(adapter);
    }
}