package com.example.splash;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.splash.network.PokemonLoader;
import com.example.splash.network.models.Pokemon;
import com.example.splash.network.models.PokemonListResponse;
import com.example.splash.pokemon.PokemonAdapter;
import com.example.splash.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityAPI extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        final RecyclerView rvPokemonList = findViewById(R.id.rvPokemonList);
//LLAMADA API
        PokemonLoader loader = new PokemonLoader();

        Call<PokemonListResponse> call = loader.getPokemonList();
        call.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                List<Pokemon> pokemonList = response.body().getPokemonList();

                PokemonAdapter adapter = new PokemonAdapter(pokemonList, MainActivityAPI.this);

                rvPokemonList.setAdapter(adapter);

                rvPokemonList.setHasFixedSize(true);

                RecyclerView.LayoutManager manager = new LinearLayoutManager(MainActivityAPI.this);

                rvPokemonList.setLayoutManager(manager);


            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {
                Log.e(Constant.DEBUG_POKEMON, t.getMessage());

            }
        });

    }
}