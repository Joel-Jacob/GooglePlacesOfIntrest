package com.example.googleplacesofintrest.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.googleplacesofintrest.R;
import com.example.googleplacesofintrest.model.Result;
import com.example.googleplacesofintrest.util.Util;

import java.util.List;

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlacesViewHolder>{

    private List<Result> places;
    private PlacesDelegate placesDelegate;
    private Context context;

    public PlacesAdapter(List<Result> places, PlacesDelegate placesDelegate) {
        this.places = places;
        this.placesDelegate = placesDelegate;
    }

    public interface PlacesDelegate{
        void getDirections(Result place);
    }

    @NonNull
    @Override
    public PlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.places_item_layout, parent, false);

        return new PlacesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlacesViewHolder holder, final int position) {
        //https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&
        // photoreference=CnRtAAAATLZNl354RwP_9UKbQ_5Psy40texXePv4oAlgP4qNEkdIrkyse7rPXYGd9D_Uj1rVsQdWT4oRz4QrYAJNpFX7rzqqMlZw2h2E2y5IKMUZ7ouD_SlcHxYq1yL4KbKUv3qtWgTK0A6QbGh87GB3sscrHRIQiG2RrmU_jF4tENr9wGS_YxoUSSDrYjWmrNfeEHSGSc3FyhNLlBU&
        // key=YOUR_API_KEY
        String imageUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference="+
                places.get(position).getPhotos().get(0).getPhotoReference() +"&key="+
                Util.API_KEY;
        //Log.d("Tag_x","url:" +imageUrl);
        holder.nameTv.setText(places.get(position).getName());
        holder.priceTv.setText("price: "+places.get(position).getPriceLevel());
        holder.ratingTv.setText("rating: "+places.get(position).getRating());

        Glide.with(context)
                .load(imageUrl)
                .into(holder.photoImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("TAG_X", movieResults.get(position).getTitle());
                placesDelegate.getDirections(places.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return places.size();
    }

    class PlacesViewHolder extends RecyclerView.ViewHolder{

        ImageView photoImage;
        TextView nameTv;
        TextView ratingTv;
        TextView priceTv;

        public PlacesViewHolder(@NonNull View itemView) {
            super(itemView);

            photoImage = itemView.findViewById(R.id.place_photo);
            nameTv = itemView.findViewById(R.id.place_name);
            ratingTv = itemView.findViewById(R.id.place_rating);
            priceTv = itemView.findViewById(R.id.place_price);
        }
    }
}
