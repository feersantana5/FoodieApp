package es.ulpgc.da.fernando.foodieapp.data;

import java.util.List;

public interface RepositoryContract {

    // llamada en el restaurantlist presenter
    // modifica la lista de restaurantes con los datos obtenidos
    interface GetRestaurantsListCallback {
        void setRestaurantsList(List<RestaurantItem> products);
    }

    //llamado en home para obtener los datos del json y añadirlod a la bbdd
    interface FetchJSONCallback {
        void onJSONFetched(boolean error);
    }

    //llamado en listmodel
    //metodo que elimina lo que hay almacenado y relaciona con el repositorio del catalogo
    void loadCatalog(boolean clearFirst, RepositoryContract.FetchJSONCallback callback);

    //metodo que obtiene del repositorio la lista de categorias y notifica
    void getRestaurantsList(CatalogRepository.GetRestaurantsListCallback callback);

    //MENUS

    //obtiene la lista de menus y notifica, modifica la lista
    interface GetMenuListCallback {
        void setMenuList(List<MenuItem> menus);
    }

    //llamado desde el modelo de Menus
    void getMenuList(RestaurantItem restaurant, CatalogRepository.GetMenuListCallback callback);

    void getMenuList(int restaurantId, CatalogRepository.GetMenuListCallback callback);

    // llamada en el register presenter
    interface RegistroUsuarioCallback {
        void userAdded(boolean error);
    }

    // llamada en el register model
    void registrarUsuario(String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo, RegistroUsuarioCallback registroUsuarioCallback);

    // llamada en el register presenter
    interface LogInCallback {
        void logInCheck(boolean error);
    }

    // llamada en el register model
    void logIn(String email, String password, LogInCallback logInCallback);


}