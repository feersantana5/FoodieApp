package es.ulpgc.da.fernando.foodieapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import es.ulpgc.da.fernando.foodieapp.models.Menu;
import es.ulpgc.da.fernando.foodieapp.adapters.MenuAdapter;


public class RestaurantCartaActivity extends AppCompatActivity {
    //recyclerview
    private RecyclerView mRecyclerView;

    //arraylist con los datos de los menus
    private ArrayList<Menu> menusData;
    //interfaz del adaptador que conecta los datos con el recyclerview
    private MenuAdapter mAdapter;

    BottomNavigationView buttonNavBar;

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

        //Funcionalidad de los botones del navBar

        buttonNavBar =  findViewById(R.id.bottomNavViewMyNav);
        buttonNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.nav_menu_inicio){
                    goToHome();
                }
                if(item.getItemId() == R.id.nav_menu_profile){
                    goProfile();
                }
                if(item.getItemId() == R.id.nav_menu_menu){
                    goMenu();
                }
                if(item.getItemId() == R.id.nav_menu_out){
                    Toast toast3 = new Toast(getApplicationContext());

                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.lytLayout));

                    TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
                    txtMsg.setText("¡Se ha cerrado la Sesión!");

                    toast3.setDuration(Toast.LENGTH_SHORT);
                    toast3.setView(layout);
                    toast3.show();
                    goOut();
                }
                return true;

            }
        });
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
            menusData.add(new Menu(menuList[i], menuImageResources.getResourceId(i, 0), menuPriceList[i], menuStartersList[i], menuBeveragesList[i], menuFirstCourseList[i], menuSecondCourseList[i], menuDessertList[i]));
        }

        //recicla TypedArray
        menuImageResources.recycle();
        // Notifica al adaptador de cambio
        mAdapter.notifyDataSetChanged();
    }


    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
    private void goProfile() {
        Intent intent = new Intent(this, RestaurantProfileActivity.class);
        startActivity(intent);
    }
    private void goMenu() {
        Intent intent = new Intent(this, RestaurantCartaActivity.class);
        startActivity(intent);
    }
    private void goOut() {

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}