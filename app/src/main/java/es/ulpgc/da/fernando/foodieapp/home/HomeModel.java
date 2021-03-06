package es.ulpgc.da.fernando.foodieapp.home;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class HomeModel implements HomeContract.Model {

    public static String TAG = HomeModel.class.getSimpleName();

    private final RepositoryContract repository;
    private final String error = "Error obteniendo JSON";

    public HomeModel(RepositoryContract repository) {
        this.repository = repository;
    }


    @Override
    public void fetchJSON(final RepositoryContract.FetchJSONCallback callback) {
        Log.e(TAG, "fetchJSON()");
        //carga los datos del catalogo a la bbdd y notifica
        repository.loadCatalog(true, callback);
    }

    @Override
    public String getJSONWarning() {
        Log.e(TAG, "getJSONWarning()");
        return error;
    }
}
