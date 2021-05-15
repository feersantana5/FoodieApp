package es.ulpgc.da.fernando.foodieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

import java.util.ArrayList;

import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.adapters.RestaurantAdapter;

public class RestaurantsListActivity extends AppCompatActivity {

    //recyclerview
    private RecyclerView mRecyclerView;

    //arraylist con los datos de los restaurantes
    private ArrayList<RestaurantItem> restaurantsData;
    //interfaz del adaptador que conecta los datos con el recyclerview
    private RestaurantAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurantslist_activity);

        // inicializa RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);
        // configura un Layout Manager por defecto
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // inicializa el ArrayList que contiene los datos
        restaurantsData = new ArrayList<>();
        // inicializa el adaptador y lo asigna al RecyclerView.
        mAdapter = new RestaurantAdapter(this, restaurantsData);
        mRecyclerView.setAdapter(mAdapter);

        // obtiene los datos
        initializeData();
    }

    /**
     * inicializa los datos de los menus desde los recursos de android
     */
    private void initializeData() {
        // obtiene los recursos del xml strings (titulos / informaciones)
        String[] restaurantList = getResources().getStringArray(R.array.restaurants_titles);
        String[] restaurantLocationList = getResources().getStringArray(R.array.restaurants_locations);
        String[] restaurantWebpageList = getResources().getStringArray(R.array.restaurants_webpages);

        //TypedArray permite almacenar un array de otro recurso XML
        TypedArray restaurantsImageResources = getResources().obtainTypedArray(R.array.restaurants_logoImages);

        // clear los datos existentes (para evitar duplique)
        restaurantsData.clear();

        // Crea el ArrayList de los objetos menus con los titulos e informacion sobre cada restaurante
        for (int i = 0; i < restaurantList.length; i++) {
            restaurantsData.add(new RestaurantItem(restaurantList[i], restaurantLocationList[i], restaurantWebpageList[i], restaurantsImageResources.getResourceId(i, 0)));
        }
        //recicla TypedArray
        restaurantsImageResources.recycle();
        // Notifica al adaptador de cambio
        mAdapter.notifyDataSetChanged();
    }

}