package es.ulpgc.da.fernando.foodieapp.restaurantCarta;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;

public class RestaurantCartaAdapter extends RecyclerView.Adapter<RestaurantCartaAdapter.ViewHolder> {

    private List<MenuItem> itemList; //lista de items
    private final View.OnClickListener clickListener;//Listener

    public RestaurantCartaAdapter(View.OnClickListener listener) {
        itemList = new ArrayList(); //lista de items
        clickListener = listener;//Listener
    }

    public void addItem(MenuItem item) {
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<MenuItem> items) {
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    //modifica los items para mostrarlos
    public void setItems(List<MenuItem> items) {
        itemList = items;
        notifyDataSetChanged();
    }

    //ontiene el numero de items
    @Override
    public int getItemCount() {
        return itemList.size();
    }


    //relaciona el adapter con el layout
    //crea la celda
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurantmenulist_item, parent, false);
        return new ViewHolder(view);
    }

    //actualiza el contenido del ViewHolder con el item dado en la posicion indicada,edita sus params tb para ser usados con el RecyclerView.
    //rellena la celda
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setTag(itemList.get(position)); //añade etiqueta con su posicion
        holder.menuTitle.setText(itemList.get(position).name);//añade texto
        //añade intents
        holder.menuImage.setTag(itemList.get(position));
        holder.menuImage.setOnClickListener(clickListener);
        holder.menuEdit.setTag(itemList.get(position));
        holder.menuEdit.setOnClickListener(clickListener);
        holder.menuDelete.setTag(itemList.get(position));
        holder.menuDelete.setOnClickListener(clickListener);
        //añade imagen
        loadImageFromURL(holder.menuImage, itemList.get(position).image);
    }

    //describe la vista de los items en el RecyclerView y su posicion (para cada celda en memoria)
    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView menuTitle;
        final ImageView menuImage, menuEdit, menuDelete;

        ViewHolder(View view) {
            super(view);
            //imagen
            menuImage = view.findViewById(R.id.menuImage);
            //intents
            menuEdit = view.findViewById(R.id.menuEdit);
            menuDelete = view.findViewById(R.id.menuDelete);
            //texto
            menuTitle = view.findViewById(R.id.menuTitle);
        }
    }

    //metodo para cargar la imagen desde url usando Glide
    private void loadImageFromURL(ImageView imageView, String imageUrl) {
        RequestManager reqManager = Glide.with(imageView.getContext());
        RequestBuilder reqBuilder = reqManager.load(imageUrl);
        RequestOptions reqOptions = new RequestOptions();
        reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        reqBuilder.apply(reqOptions);
        reqBuilder.into(imageView);
    }
}
