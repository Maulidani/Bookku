package com.example.bookku.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.bookku.R;
import com.example.bookku.activity.BooksActivity;
import com.example.bookku.activity.SearchActivity;
import com.example.bookku.adapter.rvAdapter;
import com.example.bookku.adapter.rvHomeAdapter;
import com.example.bookku.api.ApiClient;
import com.example.bookku.api.ApiInterface;
import com.example.bookku.model.Result;
import com.example.bookku.model.Value;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

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
        search = view.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SearchActivity.class));

            }
        });

        ImageSlider imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel("http://192.168.43.223/ads/img/img1.jpg", "Judul 1"));
        slideModels.add(new SlideModel("http://192.168.43.223/ads/img/img2.jpg", "Judul 2"));
        slideModels.add(new SlideModel("http://192.168.43.223/ads/img/img3.jpg", "Judul 3"));
        slideModels.add(new SlideModel("http://192.168.43.223/ads/img/img4.jpg", "Judul 4"));
        slideModels.add(new SlideModel("http://192.168.43.223/ads/img/img5.jpg", "Judul 5"));
        imageSlider.setImageList(slideModels, true);

        rv_home = view.findViewById(R.id.rv_home);

        adapter = new rvHomeAdapter(view.getContext(),results);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(),RecyclerView.HORIZONTAL,false);
        rv_home.setLayoutManager(layoutManager);
        rv_home.setAdapter(adapter);
        lihat();
    }

    void lihat(){
        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<Value> call = apiInterface.lihat();
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String val = response.body().getValue();


                if (val.equals("1")){
                    results = response.body().getResult();
                    adapter = new rvHomeAdapter(v.getContext(),results);
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