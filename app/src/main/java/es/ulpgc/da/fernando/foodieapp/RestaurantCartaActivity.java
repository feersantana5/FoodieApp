package es.ulpgc.da.fernando.foodieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

import java.util.ArrayList;

import es.ulpgc.da.fernando.foodieapp.models.Menu;
import es.ulpgc.da.fernando.foodieapp.models.MenuAdapter;


public class RestaurantCartaActivity extends AppCompatActivity {
    //recyclerview
    private RecyclerView mRecyclerView;

    //arraylist con los datos de los menus
    private ArrayList<Menu> menusData;
    //interfaz del adaptador que conecta los datos con el recyclerview
    private MenuAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_carta);

        // inicializa RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);
        // configura un Layout Manager por defecto
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        // inicializa el ArrayList que contiene los datos
        menusData = new ArrayList<>();
        // inicializa el adaptador y lo asigna al RecyclerView.
        mAdapter = new MenuAdapter(this, menusData);
        mRecyclerView.setAdapter(mAdapter);

        // obtiene los datos
        initializeData();
    }

    /**
     * inicializa los datos de los deportes desde los recursos de android
     */
    private void initializeData() {
        // obtiene los recursos del xml strings
        String[] menuList = getResources().getStringArray(R.array.menu_titles);

        //TypedArray permite almacenar un array de otro recurso XML
        TypedArray menuImageResources = getResources().obtainTypedArray(R.array.menus_Images);

        // clear los datos existentes (para evitar duplique)
        menusData.clear();

        //TODO: colocar esto bien
        // Crea el ArrayList de los objetos menus con los titulos e informacion sobre cada menu
        for (int i = 0; i < menuList.length; i++) {
            menusData.add(new Menu(menuList[i], menuImageResources.getResourceId(i, 0),15, "nachos", "agua", "sopa", "carne", "flan"));
        }

        //recicla TypedArray
        menuImageResources.recycle();
        // Notifica al adaptador de cambio
        mAdapter.notifyDataSetChanged();
    }
}