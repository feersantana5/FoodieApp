package es.ulpgc.da.fernando.foodieapp.editAccount;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class EditAccountModel implements EditAccountContract.Model {

    public static String TAG = EditAccountModel.class.getSimpleName();

    private final RepositoryContract repository;

    String vacio = "Debe mantener rellenos todos los campos.";
    String updated = "Sus datos han sido actualizados correctamente";
    String error = "Ha ocurrido un error, inténtelo de nuevo";

    public EditAccountModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public String getEmptyAdvice() {
        Log.e(TAG, "getEmptyAdvice()");
        return vacio;
    }

    @Override
    public void editUserAccount(int idRestaurant, String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo, RepositoryContract.EditUserCallback callback) {
        Log.e(TAG, "editUserAccount()");
        repository.editUser(idRestaurant, email, password, ubicacion, webpage, descripcion, nombre, logo, callback);
    }

    @Override
    public String getUpdatedAdvice() {
        Log.e(TAG, "getUpdatedAdvice()");
        return updated;
    }

    @Override
    public String getErrorAdvice() {
        Log.e(TAG, "getErrorAdvice()");
        return error;
    }
}