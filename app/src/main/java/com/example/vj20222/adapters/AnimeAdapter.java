package com.example.vj20222.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vj20222.FormAnimeActivity;
import com.example.vj20222.R;
import com.example.vj20222.entities.Anime;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimeAdapter extends RecyclerView.Adapter {

    List<Anime> data;

    public AnimeAdapter(List<Anime> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_anime, parent, false);
        return new AnimeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Anime anime = data.get(position);

        ImageView ivAnimePoster = holder.itemView.findViewById(R.id.ivAnimePoster);
        TextView tvAnimeName = holder.itemView.findViewById(R.id.tvAnimeName);
        TextView tvAnimeDescription = holder.itemView.findViewById(R.id.tvAnimeDescription);

        // Picaso
        Picasso.get().load(anime.posterURL).into(ivAnimePoster);
        tvAnimeName.setText(anime.name);
        tvAnimeDescription.setText(anime.description);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), FormAnimeActivity.class);

                intent.putExtra("ANIME_DATA", new Gson().toJson(anime));
//                intent.putExtra("ANIME_DATA_ID", anime.id);
//                intent.putExtra("ANIME_DATA_NAME", anime.name);
//                intent.putExtra("ANIME_DATA_DESC", anime.description);
//                intent.putExtra("ANIME_DATA_POSTER", anime.posterURL);

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class AnimeViewHolder extends RecyclerView.ViewHolder {

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
