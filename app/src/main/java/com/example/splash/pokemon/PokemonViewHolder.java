package com.example.splash.pokemon;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.splash.R;

public class PokemonViewHolder extends RecyclerView.ViewHolder {
    TextView tvPokemonName;
    LinearLayout llPokemonContainer;

    public PokemonViewHolder(@NonNull @androidx.annotation.NonNull View v) {
        super(v);

        tvPokemonName = v.findViewById(R.id.tvPokemonName);
        llPokemonContainer = v.findViewById(R.id.llPokemonContainer);
    }
}
