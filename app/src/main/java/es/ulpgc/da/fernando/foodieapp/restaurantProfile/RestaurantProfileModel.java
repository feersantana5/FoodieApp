package es.ulpgc.da.fernando.foodieapp.restaurantProfile;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class RestaurantProfileModel implements RestaurantProfileContract.Model {

    public static String TAG = RestaurantProfileModel.class.getSimpleName();

    private RepositoryContract repository;

    public RestaurantProfileModel(RepositoryContract repository) {
        //iniciali repo
        this.repository = repository;
    }

    @Override
    public String getStoredData() {
        Log.e(TAG, "getStoredData()");
        return "data";
    }

    @Override
    public void onRestartScreen(String data) {
        // Log.e(TAG, "onRestartScreen()");
    }

    @Override
    public void onDataFromNextScreen(String data) {
        // Log.e(TAG, "onDataFromNextScreen()");
    }

    @Override
    public void onDataFromPreviousScreen(String data) {
        // Log.e(TAG, "onDataFromPreviousScreen()");
    }
}