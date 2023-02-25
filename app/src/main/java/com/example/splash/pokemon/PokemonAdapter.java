package com.example.splash.pokemon;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splash.R;
import com.example.splash.network.models.Pokemon;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {
    List<Pokemon> pokemonList;
    Context ctx;

    public PokemonAdapter(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public PokemonAdapter(List<Pokemon> pokemonList, Context ctx) {
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull @androidx.annotation.NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pokemon, parent, false);

        return new PokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, final int i) {
        holder.tvPokemonName.setText(pokemonList.get(i).getName());

        holder.llPokemonContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url =pokemonList.get(i).getUrl();
            }
        });
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }
}
