package es.ulpgc.da.fernando.foodieapp.myMenus;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public class MyMenusModel implements MyMenusContract.Model {

    public static String TAG = MyMenusModel.class.getSimpleName();

    private RepositoryContract repository;

    private String borrado = "Menu borrado con éxito";

    public MyMenusModel(RepositoryContract repository) {
        this.repository = repository;
    }

    //obtiene la lista de mis menus del repositorio
    @Override
    public void fetchMyMenusListData(RestaurantItem restaurant, RepositoryContract.GetMyMenusListCallback callback) {
        Log.e(TAG, "fetchMyMenusListData()");
        repository.getMyMenuList(restaurant, callback);
    }

    @Override
    public void deleteMenu(MenuItem item, RepositoryContract.DeleteMenuCallback deleteMenuCallback) {
        repository.deleteMenu(item, deleteMenuCallback);
    }

    @Override
    public String getDeletedAdvice() {
        return borrado;
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