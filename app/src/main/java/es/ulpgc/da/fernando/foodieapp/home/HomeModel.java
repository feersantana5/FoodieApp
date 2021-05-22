package es.ulpgc.da.fernando.foodieapp.home;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class HomeModel implements HomeContract.Model {

    public static String TAG = HomeModel.class.getSimpleName();

    private RepositoryContract repository;

    public HomeModel(RepositoryContract repository) {
        this.repository = repository;
    }

    @Override
    public void fetchJSON(final RepositoryContract.FetchJSONCallback callback){
        Log.e(TAG, "fetchJSON()");
        //carga los datos del catalogo a la bbdd y notifica
        repository.loadCatalog(true, callback);
    }

    @Override
    public String getStoredData() {
        // Log.e(TAG, "getStoredData()");
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
