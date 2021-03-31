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

import es.ulpgc.da.fernando.foodieapp.MenuDetailActivity;
import es.ulpgc.da.fernando.foodieapp.R;


public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private ArrayList<Menu> menusData;
    private Context mContext;


    public MenuAdapter(Context context, ArrayList<Menu> menusData) {
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
        Menu currentMenu = menusData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentMenu);
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

        void bindTo(Menu currentMenu) {
            // Populate the textviews with data.
            mTitleText.setText(currentMenu.getName());
            // Load images into ImageView using Glide
            Glide.with(mContext).load(currentMenu.getImageResource()).into(mMenuImage);
        }

        @Override
        public void onClick(View view) {
            Menu currentMenu = menusData.get(getAdapterPosition());
            Intent detailIntent = new Intent(mContext, MenuDetailActivity.class);
            detailIntent.putExtra("title", currentMenu.getName());
            //TODO: pendiente de que pasar exactamente platos, bla bla bla....
            detailIntent.putExtra("image_resource", currentMenu.getImageResource());
            mContext.startActivity(detailIntent);
        }
    }
}
