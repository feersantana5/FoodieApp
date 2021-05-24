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
        void userAdded(boolean error, RestaurantItem restaurant, UserItem user);
    }

    // llamada en el register model
    void registrarUsuario(String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo, RegistroUsuarioCallback registroUsuarioCallback);

    // llamada en el register presenter
    interface LogInCallback {
        void logInCheck(boolean error, RestaurantItem restaurant, UserItem user);
    }

    // llamada en el register model
    void logIn(String email, String password, LogInCallback logInCallback);

    // llamada en el editaccount presenter
    interface EditUserCallback {
        void changeData(boolean error, RestaurantItem restaurantEdited, UserItem userEdited);
    }

    // llamada en el editaccount model
    void editUser(int idRestaurant, String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo, EditUserCallback editUserCallback);

    // llamada en el mymenus presenter
    interface GetMyMenusListCallback {
        void setMenuList(List<MenuItem> menus);
    }

    //llamado desde el modelo de Menus
    void getMyMenuList(RestaurantItem restaurant, CatalogRepository.GetMyMenusListCallback callback);

    // llamada en el mymenus presenter
    interface DeleteMenuCallback {
        void setMyMenuList(boolean error, List<MenuItem> menuItems);
    }

    //llamado desde el modelo de myMenus
    void deleteMenu(MenuItem menuItem, DeleteMenuCallback deleteMenuCallback);

    // llamada en el mymenus presenter
    interface EditMenuCallback {
        void setMyMenuListEdited(boolean error, List<MenuItem> menuItems);
    }

    //llamado desde el modelo de myMenus
    void editMenu(int idMenu, String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida, EditMenuCallback editMenuCallback);

    // llamada en el createmenu presenter
    interface CreateMenuCallback {
        void addMenu(boolean error, List<MenuItem> menuItems);
    }

    void createMenu(int idRestaurant, String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida, CreateMenuCallback createMenuCallback);

}

