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


public class MyMenusAdapter extends RecyclerView.Adapter<MyMenusAdapter.ViewHolder> {
    private ArrayList<Menu> menusData;
    private Context mContext;


    public MyMenusAdapter(Context context, ArrayList<Menu> menusData) {
        this.menusData = menusData;
        this.mContext = context;
    }

    @Override
    public MyMenusAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyMenusAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.restaurant_my_menulist_item, parent, false));
    }

    @Override
    public void onBindViewHolder(MyMenusAdapter.ViewHolder holder, int position) {
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
            //TODO: pendiente de pasar nombre del restaurante

            detailIntent.putExtra("title", currentMenu.getName());
            detailIntent.putExtra("image_resource", currentMenu.getImageResource());
            detailIntent.putExtra("price", currentMenu.getPrice());
            detailIntent.putExtra("starter", currentMenu.getStarter());
            detailIntent.putExtra("beverage", currentMenu.getBeverage());
            detailIntent.putExtra("firstCourse", currentMenu.getFirstCourse());
            detailIntent.putExtra("secondCourse", currentMenu.getSecondCourse());
            detailIntent.putExtra("dessert", currentMenu.getDessert());

            mContext.startActivity(detailIntent);
        }
    }
}
