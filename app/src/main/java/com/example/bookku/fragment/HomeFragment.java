package com.example.bookku.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bookku.R;
import com.example.bookku.activity.BooksActivity;
import com.example.bookku.activity.SearchActivity;
import com.example.bookku.adapter.rvHomeAdapter;
import com.example.bookku.api.ApiClient;
import com.example.bookku.api.ApiInterface;
import com.example.bookku.model.Result;
import com.example.bookku.model.Value;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    TextView lihat_semua;
    RecyclerView rv_home;
    private List<Result> results = new ArrayList<>();
    rvHomeAdapter adapter;
    View v;
    private LinearLayout search;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view;
        lihat_semua = v.findViewById(R.id.tv_lihat_semua);
        lihat_semua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), BooksActivity.class));
            }
        });
        search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SearchActivity.class));

            }
        });

        ImageSlider imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("http://192.168.43.223/ads/img/img01.jpg", ""));
        slideModels.add(new SlideModel("http://192.168.43.223/ads/img/img02.jpg", ""));
        slideModels.add(new SlideModel("http://192.168.43.223/ads/img/img03.JPG", ""));
        imageSlider.setImageList(slideModels, true);

        rv_home = view.findViewById(R.id.rv_home);

        adapter = new rvHomeAdapter(view.getContext(), results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), RecyclerView.HORIZONTAL, false);
        rv_home.setLayoutManager(layoutManager);
        rv_home.setAdapter(adapter);
        lihat();
    }

    void lihat() {
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<Value> call = apiInterface.lihat();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String val = response.body().getValue();


                if (val.equals("1")) {
                    results = response.body().getResult();
                    adapter = new rvHomeAdapter(v.getContext(), results);
                    adapter.notifyDataSetChanged();
                    rv_home.setAdapter(adapter);
                    rv_home.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {

                Toast.makeText(v.getContext(), "Gagal menghubungi server", Toast.LENGTH_SHORT).show();

            }
        });
    }


}