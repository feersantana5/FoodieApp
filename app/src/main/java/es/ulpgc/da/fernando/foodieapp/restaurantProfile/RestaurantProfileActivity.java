package es.ulpgc.da.fernando.foodieapp.restaurantProfile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.da.fernando.foodieapp.R;

public class RestaurantProfileActivity
        extends AppCompatActivity implements RestaurantProfileContract.View {

    public static String TAG = RestaurantProfileActivity.class.getSimpleName();

    private RestaurantProfileContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

        // do the setup
        RestaurantProfileScreen.configure(this);

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
    public void onDataUpdated(RestaurantProfileViewModel viewModel) {
        Log.e(TAG, "onDataUpdated()");
        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToNextScreen() {
        Intent intent = new Intent(this, RestaurantProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(RestaurantProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }
}