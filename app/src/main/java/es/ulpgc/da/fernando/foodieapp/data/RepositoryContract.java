package es.ulpgc.da.fernando.foodieapp.data;

import java.util.List;

public interface RepositoryContract {

    // llamada en el presenter
    // modifica la lista de restaurantes con los datos obtenidos
    interface GetRestaurantsListCallback {
        void setRestaurantsList(List<RestaurantItem> products);
    }

    //llamado en CatalogRepository para obtener los datos del catalogo
    interface FetchCatalogDataCallback {
        void onCatalogDataFetched(boolean error);
    }

    //llamado en listmodel
    //metodo que elimina lo que hay almacenado y relaciona con el repositorio del catalogo
    void loadCatalog(boolean clearFirst, CatalogRepository.FetchCatalogDataCallback callback);

    //metodo que obtiene del repositorio la lista de categorias y notifica
    void getRestaurantsList(CatalogRepository.GetRestaurantsListCallback callback);
}
