package com.example.bookku.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.example.bookku.R;
import com.example.bookku.activity.ViewBookActivity;
import com.example.bookku.roomdb.AppDatabase;
import com.example.bookku.roomdb.Book;

import java.util.ArrayList;

public class simpanAdapter extends RecyclerView.Adapter<simpanAdapter.ViewHolder>{
    private ArrayList<Book> daftarBook;
    private AppDatabase appDatabase;
    private Context context;

    public simpanAdapter(ArrayList<Book> daftarBook, AppDatabase appDatabase, Context context) {
        this.daftarBook = daftarBook;
        this.appDatabase = appDatabase;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.list_itemsimpan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        String img = daftarBook.get(position).getImg_cover();
        final String Judul = daftarBook.get(position).getJudul();
        String Deksripsi = daftarBook.get(position).getDeskripsi();
        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                // set title dialog
                alertDialogBuilder.setTitle("Hapus dari baca nanti ?");

                // set pesan dari dialog
                alertDialogBuilder
                        .setMessage(Judul)
                        .setCancelable(false)
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onDeleteData(position);
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

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, ViewBookActivity.class).putExtra("book", daftarBook.get(position).getFile()));

            }
        });

        holder.tv_judul.setText(Judul);
        holder.tv_deskripsi.setText(Deksripsi);
        Glide.with(context)
                .load(img)
                .into(holder.img_cover);
    }

    private void onDeleteData(int position){
        appDatabase.bookDAO().deleteBook(daftarBook.get(position));
        daftarBook.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, daftarBook.size());
    }
    @Override
    public int getItemCount() {
        return daftarBook.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img_cover;
        ImageView hapus;
        TextView tv_judul, tv_deskripsi, tv_penulis;
        CardView cv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            tv_judul = itemView.findViewById(R.id.tv_judul);
            img_cover = itemView.findViewById(R.id.img_cover);
            tv_deskripsi = itemView.findViewById(R.id.tv_deskripsi);
            hapus = itemView.findViewById(R.id.hapus);
            cv = itemView.findViewById(R.id.cv);

        }
    }



}
