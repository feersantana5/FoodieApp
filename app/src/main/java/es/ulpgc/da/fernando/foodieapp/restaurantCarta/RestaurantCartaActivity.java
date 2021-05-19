package es.ulpgc.da.fernando.foodieapp.restaurantCarta;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.menuDetail.MenuDetailActivity;

public class RestaurantCartaActivity
        extends AppCompatActivity implements RestaurantCartaContract.View {

    public static String TAG = RestaurantCartaActivity.class.getSimpleName();

    private RestaurantCartaContract.Presenter presenter;
    private RestaurantCartaAdapter listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_carta);

        listAdapter = new RestaurantCartaAdapter(new View.OnClickListener() {
            //onclick
            @Override
            public void onClick(View view) {
                //obtiene el elemento seleccionado
                MenuItem item = (MenuItem) view.getTag();
                //notifica el elemento seleccionado
                presenter.selectMenuListData(item);
            }
        });

        //relaciona el RecyclerView con el list adapter
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMenusCarta);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(listAdapter);
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

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        presenter.onResume();
    }

    //muestra la lista
    @Override
    public void displayMenuListData(final RestaurantCartaViewModel viewModel) {
        Log.e(TAG, "displayProductListData()");

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // deal with the data
                RestaurantItem restaurant = viewModel.restaurant; //obtiene el restaurante del viewmodel
                listAdapter.setItems(viewModel.menus);  //rellena la lista con los menus
            }
        });
    }


    //intent siguiente activity
    @Override
    public void navigateToMenuDetail() {
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
}