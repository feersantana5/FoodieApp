package es.ulpgc.da.fernando.foodieapp.models;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.RestaurantCartaActivity;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private ArrayList<Restaurant> restaurantsData;
    private Context mContext;


    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurantsData) {
        this.restaurantsData = restaurantsData;
        this.mContext = context;
    }

    @Override
    public RestaurantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.restaurantslist_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RestaurantAdapter.ViewHolder holder, int position) {
        // Get current restaurant
        Restaurant currentRestaurant = restaurantsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentRestaurant);
    }

    @Override
    public int getItemCount() {
        return restaurantsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        final TextView mTitleText;
        final ImageView mLocation;
        final ImageView mWebpage;
        final ImageView mRestaurantLogoImage;

        ViewHolder(View itemView) {
            super(itemView);

            //Initialize views
            mTitleText = itemView.findViewById(R.id.restaurantTitle);
            mLocation = itemView.findViewById(R.id.restaurantLocation);
            mWebpage = itemView.findViewById(R.id.restaurantWebpage);
            mRestaurantLogoImage = itemView.findViewById(R.id.restaurantLogoImage);

            // Set OnClickListener to entire view
            itemView.setOnClickListener(this);
        }

        void bindTo(Restaurant currentRestaurant) {
            // Populate the textviews with data.
            mTitleText.setText(currentRestaurant.getTitle());
            //TODO: hay que pasar indo de las uris de los botones, realmente imagenes?

            // Load images into ImageView using Glide
            Glide.with(mContext).load(currentRestaurant.getImageResource()).into(mRestaurantLogoImage);
        }

        @Override
        public void onClick(View view) {
            Restaurant currentRestaurant = restaurantsData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, RestaurantCartaActivity.class);
            //TODO: pendiente de que pasar exactamente
            detailIntent.putExtra("title", currentRestaurant.getTitle());
            detailIntent.putExtra("image_resource", currentRestaurant.getImageResource());

            mContext.startActivity(detailIntent);
        }
    }
}
