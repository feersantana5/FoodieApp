package es.ulpgc.da.fernando.foodieapp.login;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class LoginModel implements LoginContract.Model {

    public static String TAG = LoginModel.class.getSimpleName();

    private RepositoryContract repository;

    String vacio = "Debe rellenar todos los campos";
    String error = "Credenciales incorrectos, regístrese o inténtelo de nuevo.";

    public LoginModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public String getEmptyAdvice() {
        return vacio;
    }

    @Override
    public void logIn(String email, String password, RepositoryContract.LogInCallback callback) {
        repository.logIn(email, password, callback);
    }

    @Override
    public String getErrorAdvice() {
        return error;
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
