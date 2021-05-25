package es.ulpgc.da.fernando.foodieapp.restaurantsList;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class RestaurantsListModel implements RestaurantsListContract.Model {

    public static String TAG = RestaurantsListModel.class.getSimpleName();

    //instancia al repositorio (es el unico que accede, la via de acceso)
    private final RepositoryContract repository;

    public RestaurantsListModel(RepositoryContract repository) {
        //iniciali repo
        this.repository = repository;
    }

    @Override
    public void fetchRestaurantsListData(final RepositoryContract.GetRestaurantsListCallback callback) {
        Log.e(TAG, "fetchRestaurantsListData()");
        //obtiene la lista de restaurantes y notifica
        repository.getRestaurantsList(callback);
    }
}