package es.ulpgc.da.fernando.foodieapp.restaurantCarta;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public class RestaurantCartaModel implements RestaurantCartaContract.Model {

    public static String TAG = RestaurantCartaModel.class.getSimpleName();

    private RepositoryContract repository;


    public RestaurantCartaModel(RepositoryContract repository) {
        this.repository = repository;
    }

    //obtiene la lista de menus del repositorio
    @Override
    public void fetchMenuListData(RestaurantItem restaurant, RepositoryContract.GetMenuListCallback callback) {
        Log.e(TAG, "fetchMenuListData()");
        repository.getMenuList(restaurant, callback);
    }

}