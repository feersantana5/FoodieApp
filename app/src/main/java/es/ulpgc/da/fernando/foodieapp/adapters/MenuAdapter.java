package es.ulpgc.da.fernando.foodieapp.adapters;

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

import es.ulpgc.da.fernando.foodieapp.MenuDetailActivity;
import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private ArrayList<MenuItem> menusData;
    private Context mContext;


    public MenuAdapter(Context context, ArrayList<MenuItem> menusData) {
        this.menusData = menusData;
        this.mContext = context;
    }

    @Override
    public MenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MenuAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.restaurantmenulist_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MenuAdapter.ViewHolder holder, int position) {
        // Get current menu
        MenuItem currentMenuItem = menusData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentMenuItem);
    }

    @Override
    public int getItemCount() {
        return menusData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        final ImageView mMenuImage;
        final TextView mTitleText;

        ViewHolder(View itemView) {
            super(itemView);

            //Initialize views
            mTitleText = itemView.findViewById(R.id.menuTitle);
            mMenuImage = itemView.findViewById(R.id.menuImage);

            // Set OnClickListener to entire view
            itemView.setOnClickListener(this);
        }

        void bindTo(MenuItem currentMenuItem) {
            // Populate the textviews with data.
            mTitleText.setText(currentMenuItem.getName());
            // Load images into ImageView using Glide
            Glide.with(mContext).load(currentMenuItem.getImageResource()).into(mMenuImage);
        }

        @Override
        public void onClick(View view) {
            MenuItem currentMenuItem = menusData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, MenuDetailActivity.class);
            //TODO: pendiente de pasar nombre del restaurante

            detailIntent.putExtra("title", currentMenuItem.getName());
            detailIntent.putExtra("image_resource", currentMenuItem.getImageResource());
            detailIntent.putExtra("price", currentMenuItem.getPrice());
            detailIntent.putExtra("starter", currentMenuItem.getStarter());
            detailIntent.putExtra("beverage", currentMenuItem.getBeverage());
            detailIntent.putExtra("firstCourse", currentMenuItem.getFirstCourse());
            detailIntent.putExtra("secondCourse", currentMenuItem.getSecondCourse());
            detailIntent.putExtra("dessert", currentMenuItem.getDessert());


            mContext.startActivity(detailIntent);
        }
    }
}