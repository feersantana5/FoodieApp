package es.ulpgc.da.fernando.foodieapp.restaurantsList;

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
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public class RestaurantsListAdapter
        extends RecyclerView.Adapter<RestaurantsListAdapter.ViewHolder> {

    private List<RestaurantItem> itemList; //lista de categorias
    private final View.OnClickListener clickListener; //listener onclick

    public RestaurantsListAdapter(View.OnClickListener listener) {
        itemList = new ArrayList();
        clickListener = listener;
    }

    public void addItem(RestaurantItem item) {
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<RestaurantItem> items) {
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    //modifica items de la lista para presentarla
    public void setItems(List<RestaurantItem> items) {
        itemList = items;
        //notifica que se ha cambiado los datos
        notifyDataSetChanged();
    }

    //numero de items
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    //relaciona el layout del adapter
    //crea la celda
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurantslist_item, parent, false);
        return new ViewHolder(view);
    }

    //actualiza el contenido del ViewHolder con el item dado en la posicion indicada,edita sus params tb para ser usados con el RecyclerView.
    //rellena la celda
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setTag(itemList.get(position)); //añade etiqueta con su posicion
        holder.itemView.setOnClickListener(clickListener);//añade listener
        //TODO:
        holder.cardViewTitle.setText(itemList.get(position).title);//añade texto
        //añade intents
        holder.cardViewLocation.setOnClickListener(clickListener);
        holder.cardViewWebpage.setOnClickListener(clickListener);
        //añade imagen
        loadImageFromURL(holder.cardViewLogo, itemList.get(position).logo);
    }

    //describe la vista de los items en el RecyclerView y su posicion (para cada celda en memoria)
    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView cardViewTitle;
        final ImageView cardViewLocation, cardViewWebpage, cardViewLogo;

        ViewHolder(View view) {
            super(view);
            //TODO:
            //texto
            cardViewTitle = view.findViewById(R.id.restaurantTitleCardView);
            //intents
            cardViewLocation = view.findViewById(R.id.restaurantLocationCardView);
            cardViewWebpage = view.findViewById(R.id.restaurantWebpageCardView);
            //imagen
            cardViewLogo = view.findViewById(R.id.restaurantLogoImageCardView);
        }
    }

    private void loadImageFromURL(ImageView imageView, String imageUrl) { //metodo para añadir la imagen usando Glide
        RequestManager reqManager = Glide.with(imageView.getContext());
        RequestBuilder reqBuilder = reqManager.load(imageUrl);
        RequestOptions reqOptions = new RequestOptions();
        reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        reqBuilder.apply(reqOptions);
        reqBuilder.into(imageView);
    }

}
