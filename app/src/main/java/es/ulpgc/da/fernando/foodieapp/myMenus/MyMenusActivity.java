package es.ulpgc.da.fernando.foodieapp.myMenus;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.createMenu.CreateMenuActivity;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.editMenu.EditMenuActivity;
import es.ulpgc.da.fernando.foodieapp.home.HomeActivity;
import es.ulpgc.da.fernando.foodieapp.restaurantCarta.RestaurantCartaActivity;
import es.ulpgc.da.fernando.foodieapp.restaurantProfile.RestaurantProfileActivity;
import es.ulpgc.da.fernando.foodieapp.restaurantsList.RestaurantsListActivity;

public class MyMenusActivity
        extends AppCompatActivity implements MyMenusContract.View {

    public static String TAG = MyMenusActivity.class.getSimpleName();

    private MyMenusContract.Presenter presenter;
    private MyMenusListAdapter listAdapter;
    private FloatingActionButton fabAddMenu;
    private RecyclerView recyclerView;
    private BottomNavigationView buttonNavBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menus);

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

        initAdapter();
        initLayout();
        enableLayoutButtons();

        // do the setup
        MyMenusScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();
        } else {
            presenter.onRestart();
        }

        // do some work
        presenter.fetchMyMenusListData();
    }

    public void initAdapter() {
        //onclick
        listAdapter = new MyMenusListAdapter(view -> {
            MenuItem item = (MenuItem) view.getTag();
            Log.d(TAG, "listAdapterOnClick: " + item);

            if (view.getId() == R.id.menuEdit) {
                //listener de la lista cuando se pulsa obtiene el tag y lo pasa
                presenter.goToEditMenu(item);
            }
            if (view.getId() == R.id.menuDelete) {
                presenter.deleteMenu(item);
            }
        });
    }

    public void initLayout() {
        fabAddMenu = findViewById(R.id.fab_addMenu);

        //recyclerView
        recyclerView = findViewById(R.id.myMenusRecyclerView);
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
                presenter.goToMyMenusListNav();
            }
            if (item.getItemId() == R.id.nav_menu_out) {
                presenter.closeSession();
            }
            return true;
        });
    }

    public void enableLayoutButtons() {
        fabAddMenu.setOnClickListener(view -> presenter.goToCreateMenu());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        presenter.onResume();
    }

    @Override
    public void displayMyMenusListData(final MyMenusViewModel viewModel) {
        Log.e(TAG, "displayMyMenusListData()");
        runOnUiThread(() -> {
            // deal with the data
            //obtiene el restaurante del viewmodel
            listAdapter.setItems(viewModel.menus);  //rellena la lista con los menus
        });
    }

    @Override
    public void showToastThread(MyMenusViewModel viewModel) {
        Log.e(TAG, "showToastThread()");
        runOnUiThread(() -> {
            //Do something on UiThread
            Toast.makeText(getApplicationContext(), viewModel.toast, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void navigateToEditMenu() {
        Intent intent = new Intent(this, EditMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToCreateMenu() {
        Intent intent = new Intent(this, CreateMenuActivity.class);
        startActivity(intent);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(MyMenusActivity.this);
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
    public void injectPresenter(MyMenusContract.Presenter presenter) {
        this.presenter = presenter;
    }
}