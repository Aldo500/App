package com.example.splash.network;

import com.example.splash.network.models.PokemonListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonAPI {
    @GET("pokemon")
    Call<PokemonListResponse> getPokemonList();
}
