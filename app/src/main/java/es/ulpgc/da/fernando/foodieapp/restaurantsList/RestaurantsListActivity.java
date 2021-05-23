package es.ulpgc.da.fernando.foodieapp.restaurantsList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.app.NavBar;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.home.HomeActivity;
import es.ulpgc.da.fernando.foodieapp.restaurantCarta.RestaurantCartaActivity;
import es.ulpgc.da.fernando.foodieapp.restaurantProfile.RestaurantProfileActivity;

public class RestaurantsListActivity
        extends NavBar implements RestaurantsListContract.View {

    public static String TAG = RestaurantsListActivity.class.getSimpleName();

    private RestaurantsListContract.Presenter presenter;
    private RestaurantsListAdapter listAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurantslist_activity);

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */


        BottomNavigationView buttonNavBar;
        buttonNavBar = findViewById(R.id.bottomNavViewMyNav);
        //buttonNavBar.setVisibility(View.GONE);
        //buttonNavBar.onNavigationItemSelected(buttonNavBar.getMenu().getItem());
//        buttonNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(android.view.MenuItem item) {
//                if (item.getItemId() == R.id.nav_menu_inicio) {
//                    goToHome();
//                }
//                if (item.getItemId() == R.id.nav_menu_profile) {
//                    goProfile();
//                }
//                if (item.getItemId() == R.id.nav_menu_menu) {
//                    goMenu();
//                }
//                if (item.getItemId() == R.id.nav_menu_out) {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(RestaurantsListActivity.this);
//                    builder.setTitle("Cerrar Sesión");
//                    builder.setMessage("¿Está seguro que desea cerrar su sesión?");
//
//                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //TODO: pendiente modificar
//                            goToHome();
//                        }
//                    });
//                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //TODO: pendiente modificar
//                            finish();
//                        }
//                    });
//
//                    AlertDialog dialog = builder.create();
//                    dialog.show();
//
//                }
//                return true;
//            }
//        });

        //adapter y onclick
        listAdapter = new RestaurantsListAdapter(new View.OnClickListener() {
            //onClick
            @Override
            public void onClick(View view) {
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
            }
        });

        //recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerViewRestaurants);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //relacion de recycler con adapter
        recyclerView.setAdapter(listAdapter);

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

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // deal with the data
                //muesta la info
                listAdapter.setItems(viewModel.restaurants);
            }
        });
    }

    //intent
    @Override
    public void navigateToRestaurantCarta() {
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


    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void goProfile() {
        Intent intent = new Intent(this, RestaurantProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void goMenu() {
        Intent intent = new Intent(this, RestaurantCartaActivity.class);
        startActivity(intent);
        finish();
    }
}