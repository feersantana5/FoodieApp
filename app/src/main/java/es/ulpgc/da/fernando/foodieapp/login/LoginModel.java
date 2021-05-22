package es.ulpgc.da.fernando.foodieapp.login;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class LoginModel implements LoginContract.Model {

    public static String TAG = LoginModel.class.getSimpleName();

    private RepositoryContract repository;

    public LoginModel(RepositoryContract repository) {
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
