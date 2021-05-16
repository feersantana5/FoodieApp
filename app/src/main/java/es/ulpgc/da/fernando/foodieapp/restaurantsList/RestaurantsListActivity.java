package es.ulpgc.da.fernando.foodieapp.restaurantsList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.RestaurantCartaActivity;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public class RestaurantsListActivity
        extends AppCompatActivity implements RestaurantsListContract.View {

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

        //adapter y onclick
        listAdapter = new RestaurantsListAdapter(new View.OnClickListener() {

            //onClick
            @Override
            public void onClick(View view) {
                //listener de la lista cuando se pulsa obtiene el tag y lo pasa
                RestaurantItem item = (RestaurantItem) view.getTag();
                presenter.selectRestaurantListData(item);
            }
        });

        //recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerViewRestaurants);
        //relacion de recycler con adapter
        recyclerView.setAdapter(listAdapter);


        // do the setup
        RestaurantsListScreen.configure(this);

        // do some work
        presenter.fetchCategoryListData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        presenter.onResume();
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

    //muestra la lista
    @Override
    public void displayCategoryListData(final CategoryListViewModel viewModel) {
        Log.e(TAG, "displayCategoryListData()");

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                // deal with the data
                //muesta la info
                listAdapter.setItems(viewModel.categories);
            }
        });
    }

    //intent
    @Override
    public void navigateToRestaurantCarta() {
        Intent intent = new Intent(this, RestaurantCartaActivity.class);
        startActivity(intent);
    }

    //intent
    @Override
    public void navigateToInternet() {
        Intent webpageIntent = new Intent(Intent.ACTION_VIEW);
        webpageIntent.setData(Uri.parse("https://goo.gl/maps/PwmAy5tok6GUUP6bA"));
        mContext.startActivity(webpageIntent);
    }

    //intent
    @Override
    public void navigateToMaps() {
        String url = "https://www.allenderestauracion.com";
        Intent locationIntent = new Intent(Intent.ACTION_VIEW);
        locationIntent.setData(Uri.parse(url));
        mContext.startActivity(locationIntent);
    }

    @Override
    public void injectPresenter(RestaurantsListContract.Presenter presenter) {
        this.presenter = presenter;
    }

}