package com.example.bookku.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookku.R;
import com.example.bookku.model.Result;

import java.util.List;

public class rvHomeAdapter extends RecyclerView.Adapter<rvHomeAdapter.ViewHolder> {

    private Context context;
    private List<Result> results;

    public rvHomeAdapter(Context context, List<Result> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_home, parent, false);
        rvHomeAdapter.ViewHolder holder = new rvHomeAdapter.ViewHolder(v);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Result result = results.get(position);
        Glide.with(context)
                .load(result.getImg_cover())
                .into(holder.img_cover);
        holder.tv_judul.setText(result.getJudul());
        holder.penulis.setText(result.getPenulis());
    }

    @Override
    public int getItemCount() {
        return results == null ? 0 : results.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_cover;
        ImageView simpan;
        TextView tv_judul, tv_deskripsi, penulis;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_judul = itemView.findViewById(R.id.judul_home);
            img_cover = itemView.findViewById(R.id.img_cover_home);
            penulis = itemView.findViewById(R.id.penulis_home);
//            tv_penulis = itemView.findViewById(R.id.tv_penulis);
            cv = itemView.findViewById(R.id.cv_home);
        }
    }
}
