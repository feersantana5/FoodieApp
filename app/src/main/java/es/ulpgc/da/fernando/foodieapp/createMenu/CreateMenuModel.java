package es.ulpgc.da.fernando.foodieapp.createMenu;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

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
    public void createMenu(RestaurantItem restaurant, String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida, RepositoryContract.CreateMenuCallback createMenuCallback) {
        Log.e(TAG, "createMenu()");
        repository.createMenu(restaurant, nombre, precio, imagen, entrante, primero, segundo, postre, bebida, createMenuCallback);
    }

    @Override
    public String getCreatedAdvice() {
        Log.e(TAG, "getCreatedAdvice()");
        return creado;
    }

    @Override
    public String getErrorAdvice() {
        Log.e(TAG, "getErrorAdvice()");
        return error;
    }

}