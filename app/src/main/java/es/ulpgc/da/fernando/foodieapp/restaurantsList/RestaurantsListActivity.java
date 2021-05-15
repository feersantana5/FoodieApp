package es.ulpgc.da.fernando.foodieapp.restaurantsList;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import es.ulpgc.da.fernando.foodieapp.R;
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
        RecyclerView recyclerView = findViewById(R.id.category_list);
        //relacion de recycler con adapter
        recyclerView.setAdapter(listAdapter);


        // do the setup
        RestaurantsListScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();

        } else {
            presenter.onRestart();
        }
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

    @Override
    public void onDataUpdated(RestaurantsListViewModel viewModel) {
        Log.e(TAG, "onDataUpdated()");
        // deal with the data
        ((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, RestaurantsListActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(RestaurantsListContract.Presenter presenter) {
        this.presenter = presenter;
    }
}