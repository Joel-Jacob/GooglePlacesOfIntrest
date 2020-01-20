package com.example.googleplacesofintrest.network;

import com.example.googleplacesofintrest.model.PlacesPojo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface PlacesInterface {
    //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=AIzaSyBrjkGUsBnkwvpKmjYD2xI1kTYq3mqkkRA
    @GET("/maps/api/place/nearbysearch/json")
    Observable<PlacesPojo> getPlaces(
            @Query("location") String location,
            @Query("radius") String radius,
            @Query("type") String type,
            @Query("key") String key
    );
}
