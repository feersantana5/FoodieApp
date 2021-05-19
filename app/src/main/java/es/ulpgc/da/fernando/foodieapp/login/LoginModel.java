package es.ulpgc.da.fernando.foodieapp.login;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class LoginModel implements LoginContract.Model {

    public static String TAG = LoginModel.class.getSimpleName();

    private String data;
    private RepositoryContract repository;

    public LoginModel(String data) {
        this.data = data;
    }

    @Override
    public String getStoredData() {
        Log.e(TAG, "getStoredData()");
        return data;
    }

    @Override
    public void signIn(String correo, String passw, RepositoryContract.OnSignInCallback onSignInCallback) {
        //repository.signIn(correo,passw,onSignInCallback);
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
