package com.example.bookku.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.bumptech.glide.Glide;
import com.example.bookku.activity.BooksActivity;
import com.example.bookku.R;
import com.example.bookku.activity.ViewBookActivity;
import com.example.bookku.model.Result;
import com.example.bookku.roomdb.AppDatabase;
import com.example.bookku.roomdb.Book;

import java.util.ArrayList;
import java.util.List;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.ViewHolder>{

    private AppDatabase database;
    private Context context;
    private List<Result> results;
    private ArrayList<Book> daftarBook;


    public rvAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final Result result = results.get(position);
        Glide.with(context)
                .load(result.getImg_cover())
                .into(holder.img_cover);
        holder.tv_judul.setText(result.getJudul());
        holder.tv_deskripsi.setText(result.getDeskripsi());
//        holder.tv_penulis.setText(result.getPenulis());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ViewBookActivity.class).putExtra("book", results.get(position).getFile()));

            }
        });

        database = Room.databaseBuilder(
                context.getApplicationContext(),
                AppDatabase.class,
                "dbBook")
                .build();

        holder.simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();
            }
            private void alert(){
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title dialog
                alertDialogBuilder.setTitle("Simpan ke baca nanti ?");

                // set pesan dari dialog
                alertDialogBuilder
                        .setMessage((result.getJudul()))
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Book data = new Book();

                                String file = result.getFile();
                                String img = result.getImg_cover();
                                data.setImg_cover(img);
                                data.setFile(file);
                                data.setJudul(holder.tv_judul.getText().toString());
                                data.setDeskripsi(holder.tv_deskripsi.getText().toString());

                                insert(data);
                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                // membuat alert dialog dari builder
                AlertDialog alertDialog = alertDialogBuilder.create();

                // menampilkan alert dialog
                alertDialog.show();


            }
        });




    }

    @SuppressLint("StaticFieldLeak")
    public void insert(final Book book){
        new AsyncTask<Void, Void, Long>(){
            @Override
            protected Long doInBackground(Void... voids) {
                return database.bookDAO().insertBook(book);
            }

            @Override
            protected void onPostExecute(Long aLong) {
                Toast.makeText(context, "Disimpan", Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_cover;
        ImageView simpan;
        TextView tv_judul, tv_deskripsi, tv_penulis;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_judul = itemView.findViewById(R.id.tv_judul);
            img_cover = itemView.findViewById(R.id.img_cover);
            tv_deskripsi = itemView.findViewById(R.id.tv_deskripsi);
            simpan = itemView.findViewById(R.id.simpan);
//            tv_penulis = itemView.findViewById(R.id.tv_penulis);
            cv = itemView.findViewById(R.id.cv);
        }
    }
}
