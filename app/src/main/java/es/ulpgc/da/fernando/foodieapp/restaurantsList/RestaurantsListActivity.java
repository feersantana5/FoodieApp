package es.ulpgc.da.fernando.foodieapp.restaurantsList;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
                RestaurantItem item = (RestaurantItem) view.getTag();
                Log.d(TAG, "onClick: "+item.logo);
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

}