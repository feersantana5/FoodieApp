package es.ulpgc.da.fernando.foodieapp.editMenu;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class EditMenuModel implements EditMenuContract.Model {

    public static String TAG = EditMenuModel.class.getSimpleName();

    private final RepositoryContract repository;

    String vacio = "Debe mantener rellenos todos los campos.";
    String updated = "Su menu ha sido actualizados correctamente";



    public EditMenuModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public String getEmptyAdvice() {
        return vacio;
    }

    @Override
    public void editMenu(int idMenu, String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida, RepositoryContract.EditMenuCallback editMenuCallback) {
        repository.editMenu(idMenu, nombre, precio, imagen, entrante, primero, segundo, postre, bebida, editMenuCallback);
    }

    @Override
    public String getUpdatedAdvice() {
        return updated;
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