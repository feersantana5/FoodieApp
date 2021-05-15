package es.ulpgc.da.fernando.foodieapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

    private ArrayList<RestaurantItem> restaurantsData;
    private Context mContext;


    public RestaurantAdapter(Context context, ArrayList<RestaurantItem> restaurantsData) {
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
        RestaurantItem currentRestaurantItem = restaurantsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentRestaurantItem);
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

            // Set OnClickListener al image
            mRestaurantLogoImage.setOnClickListener(this);
            //TODO: onclick a btns
            mLocation.setOnClickListener(this); // a internet
            mWebpage.setOnClickListener(this); // a internet
        }

        void bindTo(RestaurantItem currentRestaurantItem) {
            // Populate the textviews with data.
            mTitleText.setText(currentRestaurantItem.getTitle());
            //TODO: hay que pasar indo de las uris de los botones, realmente imagenes?

            // Load images into ImageView using Glide
            Glide.with(mContext).load(currentRestaurantItem.getImageResource()).into(mRestaurantLogoImage);
        }

        @Override
        public void onClick(View view) {
            RestaurantItem currentRestaurantItem = restaurantsData.get(getAdapterPosition());
            if (view.getId() == R.id.restaurantLogoImage) {
                Intent detailIntent = new Intent(mContext, RestaurantCartaActivity.class);
                //TODO: pendiente de que pasar exactamente
                detailIntent.putExtra("title", currentRestaurantItem.getTitle());
                detailIntent.putExtra("image_resource", currentRestaurantItem.getImageResource());
                mContext.startActivity(detailIntent);
            }
            if (view.getId() == R.id.restaurantWebpage) {
                String url = "https://www.allenderestauracion.com";
                Intent locationIntent = new Intent(Intent.ACTION_VIEW);
                locationIntent.setData(Uri.parse(url));
                mContext.startActivity(locationIntent);
            }
            if (view.getId() == R.id.restaurantLocation) {
                Intent webpageIntent = new Intent(Intent.ACTION_VIEW);
                webpageIntent.setData(Uri.parse("https://goo.gl/maps/PwmAy5tok6GUUP6bA"));
                mContext.startActivity(webpageIntent);
            }
        }
    }
}
