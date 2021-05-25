package es.ulpgc.da.fernando.foodieapp.register;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class RegisterModel implements RegisterContract.Model {

    public static String TAG = RegisterModel.class.getSimpleName();

    private final RepositoryContract repository;

    public RegisterModel(RepositoryContract repository) {
        this.repository = repository;
    }

    String vacio = "Debe rellenar todos los campos";
    String register = "Registrado correctamente";
    String error = "Ha ocurrido un error, inténtelo de nuevo";

    @Override
    public String getEmptyAdvice() {
        Log.e(TAG, "getEmptyAdvice()");
        return vacio;
    }

    @Override
    public void registrarUsuario(String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo, RepositoryContract.RegistroUsuarioCallback callback) {
        Log.e(TAG, "registrarUsuario()");
        repository.registrarUsuario(email, password, ubicacion, webpage, descripcion, nombre, logo, callback);
    }

    @Override
    public String getRegisterAdvice() {
        Log.e(TAG, "getRegisterAdvice()");
        return register;
    }

    @Override
    public String getErrorAdvice() {
        Log.e(TAG, "getErrorAdvice()");
        return error;
    }

}