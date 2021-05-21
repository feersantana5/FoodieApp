package es.ulpgc.da.fernando.foodieapp.register;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class RegisterModel implements RegisterContract.Model {

    public static String TAG = RegisterModel.class.getSimpleName();

    private RepositoryContract repository;

    public RegisterModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public String getStoredData() {
        Log.e(TAG, "getStoredData()");
        return "data";
    }

    @Override
    public void onRestartScreen(String data) {
        Log.e(TAG, "onRestartScreen()");
    }

    @Override
    public void onDataFromNextScreen(String data) {
        Log.e(TAG, "onDataFromNextScreen()");
    }

    @Override
    public void onDataFromPreviousScreen(String data) {
        Log.e(TAG, "onDataFromPreviousScreen()");
    }
}