package es.ulpgc.da.fernando.foodieapp.restaurantsList;

import android.util.Log;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class RestaurantsListModel implements RestaurantsListContract.Model {

    public static String TAG = RestaurantsListModel.class.getSimpleName();

    //instancia al repositorio (es el unico que accede, la via de acceso)
    private RepositoryContract repository;

    public RestaurantsListModel(RepositoryContract repository) {
        //lo enlaza con el repositorio
        this.repository = repository;
    }

    @Override
    public void fetchRestaurantsListData(final RepositoryContract.GetRestaurantsListCallback callback) {
        Log.e(TAG, "fetchRestaurantsListData()");

        //carga los datos al catalogo y notifica
        repository.loadCatalog(true, new RepositoryContract.FetchCatalogDataCallback() {

            @Override //de repository
            public void onCatalogDataFetched(boolean error) {
                if(!error) {
                    repository.getRestaurantsList(callback); //cuando tiene la info notifica
                }
            }
        });

    }
}