package com.example.bookku.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bookku.R;
import com.example.bookku.adapter.rvAdapter;
import com.example.bookku.api.ApiClient;
import com.example.bookku.api.ApiInterface;
import com.example.bookku.model.Result;
import com.example.bookku.model.Value;
import com.example.bookku.roomdb.AppDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    SearchView search;
    private AppDatabase database;
    private List<Result> results = new ArrayList<>();
    private rvAdapter adapter;
    private RecyclerView rv;
    ProgressBar progress;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getSupportActionBar().hide();

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        search = findViewById(R.id.searchView);
        search.setIconified(false);
        search.setOnQueryTextListener(this);

        rv = findViewById(R.id.rv_search);
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
                    adapter = new rvAdapter(SearchActivity.this,results);
                    adapter.notifyDataSetChanged();
                    rv.setAdapter(adapter);
                    rv.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(SearchActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();

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
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        rv.setVisibility(View.GONE);


        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<Value> call = apiInterface.search(newText);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String val = response.body().getValue();

                rv.setVisibility(View.VISIBLE);

                if (val.equals("1")){
                    results = response.body().getResult();
                    adapter = new rvAdapter(SearchActivity.this,results);
                    adapter.notifyDataSetChanged();
                    rv.setAdapter(adapter);
                    rv.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

//                Toast.makeText(SearchActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();

            }
        });
        return true;
    }
}