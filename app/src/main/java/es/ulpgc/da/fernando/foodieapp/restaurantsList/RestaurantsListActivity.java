package es.ulpgc.da.fernando.foodieapp.restaurantsList;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.app.NavBar;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.home.HomeActivity;
import es.ulpgc.da.fernando.foodieapp.myMenus.MyMenusListAdapter;
import es.ulpgc.da.fernando.foodieapp.restaurantCarta.RestaurantCartaActivity;
import es.ulpgc.da.fernando.foodieapp.restaurantProfile.RestaurantProfileActivity;

public class RestaurantsListActivity
        extends NavBar implements RestaurantsListContract.View {

    public static String TAG = RestaurantsListActivity.class.getSimpleName();

    private RestaurantsListContract.Presenter presenter;
    private RestaurantsListAdapter listAdapter;

    private RecyclerView recyclerView;
    private BottomNavigationView buttonNavBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurantslist_activity);

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

        initAdapter();
        initLayout();

        // do the setup
        RestaurantsListScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();
        } else {
            presenter.onRestart();
        }

        // do some work
        presenter.fetchRestaurantsListData();
    }

    public void initAdapter() {
        //onclick
        listAdapter = new RestaurantsListAdapter(view -> {
            RestaurantItem item = (RestaurantItem) view.getTag();
            Log.d(TAG, "onClick: " + item);
            if (view.getId() == R.id.restaurantLogoImageCardView) {
                //listener de la lista cuando se pulsa obtiene el tag y lo pasa
                presenter.selectRestaurantListData(item);
            }
            if (view.getId() == R.id.restaurantWebpageCardView) {
                Intent locationIntent = new Intent(Intent.ACTION_VIEW);
                locationIntent.setData(Uri.parse(item.webpage));
                startActivity(locationIntent);
            }
            if (view.getId() == R.id.restaurantLocationCardView) {
                Intent webpageIntent = new Intent(Intent.ACTION_VIEW);
                webpageIntent.setData(Uri.parse(item.location));
                startActivity(webpageIntent);
            }
        });
    }

    public void initLayout() {
        //recyclerview
        recyclerView = findViewById(R.id.recyclerViewRestaurants);
        reyclerSettings();

        //navbar
        buttonNavBar = findViewById(R.id.bottomNavViewMyNav);
        navbarSettings();
    }

    public void reyclerSettings() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //relacion de recycler con adapter
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
                presenter.goToMyMenusListNav();
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
    public void displayRestaurantsListData(final RestaurantsListViewModel viewModel) {
        Log.e(TAG, "displayRestaurantsListData()");
        runOnUiThread(() -> {
            // deal with the data
            //muesta la info
            listAdapter.setItems(viewModel.restaurants);
        });
    }

    @Override
    public void navigateToRestaurantCarta() {
        Log.e(TAG, "navigateToRestaurantCarta()");
        Intent intent = new Intent(this, RestaurantCartaActivity.class);
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
    public void injectPresenter(RestaurantsListContract.Presenter presenter) {
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
    public void navigateToMenuNav() {
        Log.e(TAG, "navigateToMenuNav()");
        Intent intent = new Intent(this, RestaurantCartaActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showAlertDialogNav() {
        Log.e(TAG, "showAlertDialogNav()");
        AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantsListActivity.this);
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