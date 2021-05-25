package es.ulpgc.da.fernando.foodieapp.createMenu;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class CreateMenuModel implements CreateMenuContract.Model {

    public static String TAG = CreateMenuModel.class.getSimpleName();

    private final RepositoryContract repository;

    String vacio = "Debe rellenar todos los campos";
    String creado = "Menu creado correctamente";
    String error = "Ha ocurrido un error, inténtelo de nuevo.";


    public CreateMenuModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public String getEmptyAdvice() {
        return vacio;
    }

    @Override
    public void createMenu(int restaurantId, String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida, RepositoryContract.CreateMenuCallback createMenuCallback) {
        repository.createMenu(restaurantId, nombre, precio, imagen, entrante, primero, segundo, postre, bebida, createMenuCallback);
    }

    @Override
    public String getCreatedAdvice() {
        return creado;
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