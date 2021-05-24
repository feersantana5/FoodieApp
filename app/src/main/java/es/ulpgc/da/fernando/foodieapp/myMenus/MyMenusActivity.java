package es.ulpgc.da.fernando.foodieapp.myMenus;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ulpgc.da.fernando.foodieapp.EditMenuActivity;
import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.register.RegisterViewModel;
import es.ulpgc.da.fernando.foodieapp.restaurantCarta.RestaurantCartaAdapter;
import es.ulpgc.da.fernando.foodieapp.restaurantCarta.RestaurantCartaViewModel;
import es.ulpgc.da.fernando.foodieapp.restaurantsList.RestaurantsListAdapter;

public class MyMenusActivity
        extends AppCompatActivity implements MyMenusContract.View {

    public static String TAG = MyMenusActivity.class.getSimpleName();

    private MyMenusContract.Presenter presenter;
    private MyMenusListAdapter listAdapter;


    FloatingActionButton fabAddMenu;
    RecyclerView recyclerView;


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
        listAdapter = new MyMenusListAdapter(new View.OnClickListener() {
            //onclick
            @Override
            public void onClick(View view) {
                MenuItem item = (MenuItem) view.getTag();
                Log.d(TAG, "listAdapterOnClick: " + item);

                if (view.getId() == R.id.menuEdit) {
                    //listener de la lista cuando se pulsa obtiene el tag y lo pasa
                    presenter.goToEditMenu();
                }
                if (view.getId() == R.id.menuDelete) {
                    presenter.deleteMenu(item);
                }
                //TODO: detalle en menus?
            }
        });
    }

    public void initLayout() {
        fabAddMenu = findViewById(R.id.fab_addMenu);

        recyclerView = findViewById(R.id.myMenusRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(listAdapter);
    }

    public void enableLayoutButtons() {
        fabAddMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // deal with the data
                RestaurantItem restaurant = viewModel.restaurant; //obtiene el restaurante del viewmodel
                listAdapter.setItems(viewModel.menus);  //rellena la lista con los menus
            }
        });
    }

    @Override
    public void showToastThread(MyMenusViewModel viewModel) {
        Log.e(TAG, "showToastThread()");
        runOnUiThread(new Runnable() {
            public void run() {
                //Do something on UiThread
                Toast.makeText(getApplicationContext(), viewModel.toast, Toast.LENGTH_SHORT).show();
            }
        });
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
    public void onDataUpdated(MyMenusViewModel viewModel) {
        Log.e(TAG, "onDataUpdated()");
        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToEditMenu() {
        Intent intent = new Intent(this, EditMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(MyMenusContract.Presenter presenter) {
        this.presenter = presenter;
    }
}