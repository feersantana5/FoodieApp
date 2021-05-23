package es.ulpgc.da.fernando.foodieapp.restaurantProfile;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;

public class RestaurantProfilePresenter implements RestaurantProfileContract.Presenter {

    public static String TAG = RestaurantProfilePresenter.class.getSimpleName();

    private WeakReference<RestaurantProfileContract.View> view;
    private RestaurantProfileState state;
    private RestaurantProfileContract.Model model;
    private FoodieMediator mediator;

    public RestaurantProfilePresenter(FoodieMediator mediator) {
        this.mediator = mediator;
        state = mediator.getRestaurantProfileState();
    }

    @Override
    public void onStart() {
         Log.e(TAG, "onStart()");
        // initialize the state if is necessary
        if (state == null) {
            state = new RestaurantProfileState();
        }
    }

    @Override
    public void onRestart() {
         Log.e(TAG, "onRestart()");
        // update the model if is necessary
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");
        // use passed state if is necessary
    }

    @Override
    public void onBackPressed() {
         Log.e(TAG, "onBackPressed()");
    }

    @Override
    public void onPause() {
         Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
         Log.e(TAG, "onDestroy()");
    }

    @Override
    public void injectView(WeakReference<RestaurantProfileContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(RestaurantProfileContract.Model model) {
        this.model = model;
    }

}
