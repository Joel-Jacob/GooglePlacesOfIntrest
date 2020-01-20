package com.example.googleplacesofintrest.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.googleplacesofintrest.R;
import com.example.googleplacesofintrest.adapter.PlacesAdapter;
import com.example.googleplacesofintrest.model.Result;

import java.util.ArrayList;
import java.util.List;

public class PlacesFragment extends Fragment implements PlacesAdapter.PlacesDelegate {
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);

        //Log.d("TAG_X", "HERE");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view);

        Bundle bundle = getArguments();
        int num = bundle.getInt("numList");
        List<Result> places = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Result tempResult = bundle.getParcelable("places_" + i);

            places.add(tempResult);

            //Log.d("TAG_X", "photo: " + places.get(i).getPhotos().get(0).getPhotoReference());
        }

        setRV(places);
        //Log.d("TAG_X", "numList: " + num);
    }

    private void setRV (List<Result> placeResults){
        //Log.d("TAG_X", movieResults.get(0).getTitle());
        PlacesAdapter adapter = new PlacesAdapter(placeResults, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL, false));
    }

    @Override
    public void getDirections(Result place) {
        Log.d("TAG_X", place.getName());

        // Creates an Intent that will load a map of San Francisco
        //google.navigation:q=latitude,longitude
        Uri gmmIntentUri = Uri.parse("google.navigation:q="+place.getGeometry().getLocation().getLat()+","+place.getGeometry().getLocation().getLng());
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }
}
