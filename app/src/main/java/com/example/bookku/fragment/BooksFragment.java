package com.example.bookku.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bookku.activity.Agama;
import com.example.bookku.activity.Bahasa;
import com.example.bookku.activity.BooksActivity;
import com.example.bookku.R;
import com.example.bookku.activity.FilsafatdanPsikologi;
import com.example.bookku.activity.LiteraturdanSastra;
import com.example.bookku.activity.SainsdanMatematika;
import com.example.bookku.activity.SejarahdanGeografi;
import com.example.bookku.activity.SenidanRekreasi;
import com.example.bookku.activity.Sosial;
import com.example.bookku.activity.Teknologi;

//<!--    Semua-->
//<!--    Filsafat dan Psikologi-->
//<!--    Agama-->
//<!--    Sosial-->
//<!--    Bahasa-->
//<!--    Sains dan Matematika-->
//<!--    Teknologi-->
//<!--    Seni dan Rekreasi-->
//<!--    Literatur dan Sastra-->
//<!--    Sejarah dan Geografi-->
public class BooksFragment extends Fragment {

    CardView kategori_semua, filsafatdanpsikologi,agama,sosial,bahasa,sainsdanmatematika,teknologi,senidanrekreasi,literaturdansastra,sejarahdangeografi;
    View v;
    ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        v = view;
        kategori_semua = v.findViewById(R.id.kategori_semua);
        kategori_semua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), BooksActivity.class));

            }
        });

        filsafatdanpsikologi = v.findViewById(R.id.filsafatdanpsikologi);
        filsafatdanpsikologi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), FilsafatdanPsikologi.class));

            }
        });

        agama = v.findViewById(R.id.agama);
        agama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Agama.class));

            }
        });
        sosial = v.findViewById(R.id.sosial);
        sosial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Sosial.class));

            }
        });
        bahasa = v.findViewById(R.id.bahasa);
        bahasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Bahasa.class));

            }
        });
        sainsdanmatematika = v.findViewById(R.id.sainsdanteknologi);
        sainsdanmatematika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SainsdanMatematika.class));

            }
        });
        teknologi = v.findViewById(R.id.teknologi);
        teknologi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), Teknologi.class));

            }
        });
        senidanrekreasi = v.findViewById(R.id.senidanrekreasi);
        senidanrekreasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SenidanRekreasi.class));

            }
        });
        literaturdansastra = v.findViewById(R.id.literaturdansastra);
        literaturdansastra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), LiteraturdanSastra.class));

            }
        });
        sejarahdangeografi = v.findViewById(R.id.sejarahdangeografi);
        sejarahdangeografi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SejarahdanGeografi.class));

            }
        });
//        menumpuk fragment
//        FragmentManager fm = getFragmentManager();
//        FragmentTransaction ft = fm.beginTransaction();
//        AllBooksFragment llf = new AllBooksFragment();
//        ft.replace(R.id.frame_fragment, llf);
//        ft.commit();

    }


}