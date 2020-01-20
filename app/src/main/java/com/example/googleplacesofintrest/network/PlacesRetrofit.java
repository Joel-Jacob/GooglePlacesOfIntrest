package com.example.googleplacesofintrest.network;


import com.example.googleplacesofintrest.model.PlacesPojo;
import com.example.googleplacesofintrest.util.Util;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlacesRetrofit {
    private PlacesInterface placesInterface;


    public PlacesRetrofit() {
        placesInterface = create(getInstance());
    }

    private Retrofit getInstance(){
        return  new Retrofit.Builder()
                .baseUrl(Util.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private PlacesInterface create(Retrofit retrofit){
        return retrofit.create(PlacesInterface.class);
    }

    public Observable<PlacesPojo> getNearbyPlaces(
            String location,
            String radius,
            String type
    ){
        return placesInterface.getPlaces(location, radius, type, Util.API_KEY);
    }
}
