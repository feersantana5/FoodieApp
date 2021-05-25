package es.ulpgc.da.fernando.foodieapp.editMenu;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class EditMenuModel implements EditMenuContract.Model {

    public static String TAG = EditMenuModel.class.getSimpleName();

    private final RepositoryContract repository;

    private final String vacio = "Debe mantener rellenos todos los campos.";
    private final String updated = "Su menu ha sido actualizados correctamente";

    public EditMenuModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public String getEmptyAdvice() {
        Log.e(TAG, "getEmptyAdvice()");
        return vacio;
    }

    @Override
    public void editMenu(int idMenu, String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida, RepositoryContract.EditMenuCallback editMenuCallback) {
        Log.e(TAG, "editMenu()");
        repository.editMenu(idMenu, nombre, precio, imagen, entrante, primero, segundo, postre, bebida, editMenuCallback);
    }

    @Override
    public String getUpdatedAdvice() {
        Log.e(TAG, "getUpdatedAdvice()");
        return updated;
    }
}