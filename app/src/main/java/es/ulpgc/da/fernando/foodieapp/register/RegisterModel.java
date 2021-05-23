package es.ulpgc.da.fernando.foodieapp.register;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class RegisterModel implements RegisterContract.Model {

    public static String TAG = RegisterModel.class.getSimpleName();

    private RepositoryContract repository;

    public RegisterModel(RepositoryContract repository) {
        this.repository = repository;
    }

    String vacio = "Debe rellenar todos los campos";
    String register = "Registrado correctamente";
    String error = "Ha ocurrido un error, inténtelo de nuevo.";



    @Override
    public String getEmptyAdvice() {
        return vacio;
    }

    @Override
    public void registrarUsuario(String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo, RepositoryContract.RegistroUsuarioCallback RegistroUsuarioCallback) {
        repository.registrarUsuario(email, password, ubicacion, webpage, descripcion, nombre, logo, RegistroUsuarioCallback);
    }

    @Override
    public String getRegisterAdvice() {
        return register;
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