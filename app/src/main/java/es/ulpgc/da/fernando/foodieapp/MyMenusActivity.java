package es.ulpgc.da.fernando.foodieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.ulpgc.da.fernando.foodieapp.adapters.MyMenusAdapter;
import es.ulpgc.da.fernando.foodieapp.home.HomeActivity;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;

public class MyMenusActivity extends AppCompatActivity {
    //recyclerview
    private RecyclerView mRecyclerView;

    //arraylist con los datos de los menus
    private ArrayList<MenuItem> menusData;
    //interfaz del adaptador que conecta los datos con el recyclerview
    private MyMenusAdapter mAdapter;

    FloatingActionButton fabAddMenu;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menus);

        // inicializa RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);
        // configura un Layout Manager por defecto
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        // inicializa el ArrayList que contiene los datos
        menusData = new ArrayList<>();
        // inicializa el adaptador y lo asigna al RecyclerView.
        mAdapter = new MyMenusAdapter(this, menusData);
        mRecyclerView.setAdapter(mAdapter);

        // obtiene los datos
        initializeData();

        //listener del fab button add
        fabAddMenu = findViewById(R.id.fab_addMenu);
        fabAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyMenusActivity.this, CreateMenuActivity.class);
                startActivity(intent);
            }
        });

        //Funcionalidad de los botones del navBar



    }

    /**
     * inicializa los datos de los deportes desde los recursos de android
     */
    private void initializeData() {
        // obtiene los recursos del xml strings
        String[] menuList = getResources().getStringArray(R.array.menu_titles);
        int[] menuPriceList = getResources().getIntArray((R.array.menu_price));
        String[] menuStartersList = getResources().getStringArray(R.array.menu_starters);
        String[] menuBeveragesList = getResources().getStringArray(R.array.menu_beverages);
        String[] menuFirstCourseList = getResources().getStringArray(R.array.menu_firstCourse);
        String[] menuSecondCourseList = getResources().getStringArray(R.array.menu_secondCourse);
        String[] menuDessertList = getResources().getStringArray(R.array.menu_dessert);

        //TypedArray permite almacenar un array de otro recurso XML
        TypedArray menuImageResources = getResources().obtainTypedArray(R.array.menus_Images);

        // clear los datos existentes (para evitar duplique)
        menusData.clear();


        for (int i = 0; i < menuList.length; i++) {
            menusData.add(new MenuItem(menuList[i], menuImageResources.getResourceId(i, 0), menuPriceList[i], menuStartersList[i], menuBeveragesList[i], menuFirstCourseList[i], menuSecondCourseList[i], menuDessertList[i]));
        }

        //recicla TypedArray
        menuImageResources.recycle();
        // Notifica al adaptador de cambio
        mAdapter.notifyDataSetChanged();
    }

    //TODO: pendiente
    public void addMenu(View view) {
        Intent createMenuIntent = new Intent(this, CreateMenuActivity.class);
        startActivity(createMenuIntent);
    }


    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}