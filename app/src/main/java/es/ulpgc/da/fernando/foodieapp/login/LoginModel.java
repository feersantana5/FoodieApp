package es.ulpgc.da.fernando.foodieapp.login;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class LoginModel implements LoginContract.Model {

    public static String TAG = LoginModel.class.getSimpleName();

    private final RepositoryContract repository;

    String vacio = "Debe rellenar todos los campos";
    String error = "Credenciales incorrectos, regístrese o inténtelo de nuevo.";

    public LoginModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public String getEmptyAdvice() {
        Log.e(TAG, "getEmptyAdvice()");
        return vacio;
    }

    @Override
    public void logIn(String email, String password, RepositoryContract.LogInCallback callback) {
        Log.e(TAG, "logIn()");
        repository.logIn(email, password, callback);
    }

    @Override
    public String getErrorAdvice() {
        Log.e(TAG, "getErrorAdvice()");
        return error;
    }

}
