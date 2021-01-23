package com.example.bookku.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bookku.R;
import com.example.bookku.adapter.rvAdapter;
import com.example.bookku.api.ApiClient;
import com.example.bookku.api.ApiInterface;
import com.example.bookku.model.Result;
import com.example.bookku.model.Value;
import com.example.bookku.roomdb.AppDatabase;
import com.example.bookku.roomdb.Book;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksActivity extends AppCompatActivity {

    private AppDatabase database;
    private List<Result> results = new ArrayList<>();
    private rvAdapter adapter;
    private RecyclerView rv;
    ProgressBar progress;
    private Toolbar appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

//        getSupportActionBar().setTitle("Data");
//        appbar = findViewById(R.id.appbar);
//        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rv = findViewById(R.id.rv);
        progress = findViewById(R.id.pb);

        adapter = new rvAdapter(this,results);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2,RecyclerView.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        lihat();
        room();
    }



    void lihat(){
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<Value> call = apiInterface.lihat();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String val = response.body().getValue();
                progress.setVisibility(View.GONE);

                if (val.equals("1")){
                    results = response.body().getResult();
                    adapter = new rvAdapter(BooksActivity.this,results);
                    adapter.notifyDataSetChanged();
                    rv.setAdapter(adapter);
                    rv.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(BooksActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();

            }
        });
    }

    void room(){
        database = Room.databaseBuilder(
                getApplicationContext(),
                AppDatabase.class,
                "dbBook")
                .build();
    }

    @Override
    public boolean onSupportNavigateUp() {

        onBackPressed();
        return true;
    }
}