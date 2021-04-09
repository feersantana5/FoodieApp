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

import es.ulpgc.da.fernando.foodieapp.EditMenuActivity;
import es.ulpgc.da.fernando.foodieapp.MenuDetailActivity;
import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.models.Menu;


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
        final ImageView mMenuEdit;
        final ImageView mMenuDelete;


        ViewHolder(View itemView) {
            super(itemView);

            //Initialize views
            mTitleText = itemView.findViewById(R.id.menuTitle);
            mMenuImage = itemView.findViewById(R.id.menuImage);

            mMenuEdit = itemView.findViewById(R.id.menuEdit);
            mMenuDelete = itemView.findViewById(R.id.menuDelete);

            // Set OnClickListener to the imagen and the buttons
            mMenuImage.setOnClickListener(this); // a MenuDetailActivity
            //TODO: onclick a btns
            mMenuEdit.setOnClickListener(this); // a editar
            mMenuDelete.setOnClickListener(this); // a borrar
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
            if (view.getId() == R.id.menuImage) {
                Intent menuDetailIntent = new Intent(mContext, MenuDetailActivity.class);
                //TODO: pendiente de pasar nombre del restaurante
                menuDetailIntent.putExtra("title", currentMenu.getName());
                menuDetailIntent.putExtra("image_resource", currentMenu.getImageResource());
                menuDetailIntent.putExtra("price", currentMenu.getPrice());
                menuDetailIntent.putExtra("starter", currentMenu.getStarter());
                menuDetailIntent.putExtra("beverage", currentMenu.getBeverage());
                menuDetailIntent.putExtra("firstCourse", currentMenu.getFirstCourse());
                menuDetailIntent.putExtra("secondCourse", currentMenu.getSecondCourse());
                menuDetailIntent.putExtra("dessert", currentMenu.getDessert());

                mContext.startActivity(menuDetailIntent);
            }
            if (view.getId() == R.id.menuEdit) {
                Intent editMenuIntent = new Intent(mContext, EditMenuActivity.class);
                mContext.startActivity(editMenuIntent);
            }

/*            if (view.getId() == R.id.menuDelete) {
                Intent deleteIntent = new Intent(mContext, .class);
                mContext.startActivity(deleteIntent);
            }*/

        }
    }
}

