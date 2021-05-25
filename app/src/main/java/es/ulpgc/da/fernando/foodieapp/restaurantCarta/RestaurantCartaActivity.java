package es.ulpgc.da.fernando.foodieapp.restaurantCarta;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.home.HomeActivity;
import es.ulpgc.da.fernando.foodieapp.menuDetail.MenuDetailActivity;
import es.ulpgc.da.fernando.foodieapp.restaurantProfile.RestaurantProfileActivity;

public class RestaurantCartaActivity
        extends AppCompatActivity implements RestaurantCartaContract.View {

    public static String TAG = RestaurantCartaActivity.class.getSimpleName();

    private RestaurantCartaContract.Presenter presenter;
    private RestaurantCartaAdapter listAdapter;

    private BottomNavigationView buttonNavBar;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_carta);

        initAdapter();
        initLayout();

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

        // do the setup
        RestaurantCartaScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();
        } else {
            presenter.onRestart();
        }

        // do some work
        presenter.fetchMenuListData();
    }

    public void initAdapter() {
        //onclick
        listAdapter = new RestaurantCartaAdapter(view -> {
            //obtiene el elemento seleccionado
            MenuItem item = (MenuItem) view.getTag();
            //notifica el elemento seleccionado
            presenter.selectMenuListData(item);
        });
    }

    public void initLayout() {
        //recycler
        recyclerView = findViewById(R.id.recyclerViewMenusCarta);
        reyclerSettings();
        //navbar
        buttonNavBar = findViewById(R.id.bottomNavViewMyNav);
        navbarSettings();
    }

    public void reyclerSettings() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(listAdapter);
    }

    public void navbarSettings() {
        //buttonNavBar.setVisibility(View.GONE);
        buttonNavBar.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_menu_inicio) {
                presenter.goToHomeNav();
            }
            if (item.getItemId() == R.id.nav_menu_profile) {
                presenter.goToProfileNav();
            }
            if (item.getItemId() == R.id.nav_menu_menu) {
                //nada pq está aqui
            }
            if (item.getItemId() == R.id.nav_menu_out) {
                presenter.closeSession();
            }
            return true;
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        presenter.onResume();
    }

    //muestra la lista
    @Override
    public void displayMenuListData(final RestaurantCartaViewModel viewModel) {
        Log.e(TAG, "displayMenuListData()");
        runOnUiThread(() -> {
            // deal with the data
            //obtiene el restaurante del viewmodel
            listAdapter.setItems(viewModel.menus);  //rellena la lista con los menus
        });
    }

    @Override
    public void navigateToMenuDetail() {
        Log.e(TAG, "navigateToMenuDetail()");
        Intent intent = new Intent(this, MenuDetailActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void injectPresenter(RestaurantCartaContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void navigateToHomeNav() {
        Log.e(TAG, "navigateToHomeNav()");
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToProfileNav() {
        Log.e(TAG, "navigateToProfileNav()");
        Intent intent = new Intent(this, RestaurantProfileActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showAlertDialogNav() {
        Log.e(TAG, "showAlertDialogNav()");
        AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantCartaActivity.this);
        builder.setTitle("Cerrar Sesión");
        builder.setMessage("¿Está seguro que desea cerrar su sesión?");

        builder.setPositiveButton("Aceptar", (dialog, which) -> {
            //TODO: pendiente modificar
            navigateToHomeNav();
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> {
            //TODO: pendiente modificar
            finish();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}