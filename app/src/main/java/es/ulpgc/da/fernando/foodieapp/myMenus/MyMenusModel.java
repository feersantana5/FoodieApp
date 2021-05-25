package es.ulpgc.da.fernando.foodieapp.myMenus;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public class MyMenusModel implements MyMenusContract.Model {

    public static String TAG = MyMenusModel.class.getSimpleName();

    private final RepositoryContract repository;

    private final String borrado = "Menu borrado con éxito";

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
        Log.e(TAG, "deleteMenu()");
        repository.deleteMenu(item, deleteMenuCallback);
    }

    @Override
    public String getDeletedAdvice() {
        Log.e(TAG, "getDeletedAdvice()");
        return borrado;
    }

}