package es.ulpgc.da.fernando.foodieapp.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import es.ulpgc.da.fernando.foodieapp.database.CatalogDatabase;
import es.ulpgc.da.fernando.foodieapp.database.MenuDao;
import es.ulpgc.da.fernando.foodieapp.database.RestaurantDao;
import es.ulpgc.da.fernando.foodieapp.database.UserDao;

public class CatalogRepository implements RepositoryContract {

    public static String TAG = CatalogRepository.class.getSimpleName();

    //archivo de la bbdd
    public static final String DB_FILE = "foodie.db";
    //archivo json donde se almacena la informacion de la bbdd
    public static final String JSON_FILE = "catalog.json";
    // elemento padre del json
    public static final String JSON_ROOT = "restaurants";

    //instancia del repositorio
    private static CatalogRepository INSTANCE;

    private final CatalogDatabase database;
    private final Context context;

    // crea el repositorio la primera vez que se le llama
    public static RepositoryContract getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CatalogRepository(context);
        }
        return INSTANCE;
    }

    //crea la bbdd
    private CatalogRepository(Context context) {
        this.context = context;
        //room builder: clase donde se encuentra la info, fichero a almacenar
        database = Room.databaseBuilder(context, CatalogDatabase.class, DB_FILE).build();
        //hace referencia a la clase catalog de la bbdd
    }

    @Override
    public void loadCatalog(final boolean clearFirst, final FetchJSONCallback callback) {
        Log.e(TAG, "loadCatalog()");
        //crea hilo asincrono
        //ejecuta el hilo
        AsyncTask.execute(() -> {
            //si es true, elimina la info existente en la tabla
            if (clearFirst) {
                database.clearAllTables();
            }

            boolean error = false;

            //si no hay restaurantes, error al cargar al json pq esta vacio
            if (getRestaurantDao().loadRestaurants().size() == 0) {
                error = !loadCatalogFromJSON(loadJSONFromAsset());
            }

            if (callback != null) {
                callback.onJSONFetched(error); //notifica si hay error o no
            }
        });
    }

    private RestaurantDao getRestaurantDao() {
        return database.restaurantDao();
    }

    private UserDao getUserDao() {
        return database.userDao();
    }

    //carga el json desde assets
    private String loadJSONFromAsset() {
        Log.e(TAG, "loadJSONFromAsset()");

        String json = null;
        try {
            InputStream is = context.getAssets().open(JSON_FILE);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException error) {
            Log.e(TAG, "error: " + error);
        }
        return json;
    }

    private boolean loadCatalogFromJSON(String json) {
        Log.e(TAG, "loadCatalogFromJSON()");

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROOT); //elemento padre del json

            if (jsonArray.length() > 0) { //si hay contenido

                //convierte la lista de restaurantes en una lista serializable, convirtiendo los elementos a string
                final RestaurantItem[] restaurants = gson.fromJson(jsonArray.toString(), RestaurantItem[].class);

                //recorre los restaurantes e inserta
                for (RestaurantItem restaurant : restaurants) {
                    getRestaurantDao().insertRestaurant(restaurant);
                }

                //recorre los menus e inserta en la base de datos
                for (RestaurantItem restaurant : restaurants) {
                    for (MenuItem menu : restaurant.items) {
                        //la clave foranea
                        menu.restaurantId = restaurant.id;
                        getMenuDao().insertMenu(menu);
                    }
                }
                try {
                    JSONArray jsonSegundoArray = jsonObject.getJSONArray("users"); //elemento padre del json
                    if (jsonSegundoArray.length() > 0) { //si hay contenido
                        //convierte la lista de restaurantes en una lista serializable, convirtiendo los elementos a string
                        final UserItem[] users = gson.fromJson(jsonSegundoArray.toString(), UserItem[].class);
                        //recorre los usuarios e inserta
                        for (UserItem user : users) {
                            getUserDao().insertUser(user);
                        }
                        return true;
                    }
                } catch (JSONException error) {
                    Log.e(TAG, "error: " + error);
                }
                return false;
            }
            //si esta vacio
        } catch (JSONException error) {
            Log.e(TAG, "error: " + error);
        }
        return false;
    }

    private MenuDao getMenuDao() {
        return database.menuDao();
    }

    //obtiene la lista de categorias y notifica cuando las tiene
    @Override
    public void getRestaurantsList(final GetRestaurantsListCallback callback) {
        Log.e(TAG, "getRestaurantsList()");
        AsyncTask.execute(() -> {
            if (callback != null) {
                callback.setRestaurantsList(getRestaurantDao().loadRestaurants());
            }
        });

    }

    //desde el modelo, obtiene la lista de modelos segun el restaurante y notifica
    @Override
    public void getMenuList(final RestaurantItem restaurant, final GetMenuListCallback callback) {
        Log.e(TAG, "getMenuList()");
        getMenuList(restaurant.id, callback);
    }

    //llamado desde el de arriba
    @Override
    public void getMenuList(final int restaurantId, final GetMenuListCallback callback) {
        AsyncTask.execute(() -> {
            if (callback != null) {
                callback.setMenuList(getMenuDao().loadMenus(restaurantId));
            }
        });
    }


    @Override
    public void registrarUsuario(String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo, RegistroUsuarioCallback registroUsuarioCallback) {
        Log.e(TAG, "registrarUsuario()");
        //crea hilo asincrono
        //ejecuta el hilo
        AsyncTask.execute(() -> {
            RestaurantItem newRestaurant = new RestaurantItem();
            newRestaurant.logo = logo;
            newRestaurant.location = ubicacion;
            newRestaurant.description = descripcion;
            newRestaurant.title = nombre;
            newRestaurant.webpage = webpage;
            getRestaurantDao().insertRestaurant(newRestaurant);

            UserItem newUser = new UserItem();
            newUser.email = email;
            newUser.password = password;
            newUser.restaurantId = getRestaurantDao().getId(nombre);
            getUserDao().insertUser(newUser);

            if (registroUsuarioCallback != null) {
                registroUsuarioCallback.userAdded(false, newRestaurant, newUser);
            }
        });
    }

    @Override
    public void logIn(String email, String password, LogInCallback logInCallback) {
        Log.e(TAG, "logIn()");
        //crea hilo asincrono
        //ejecuta el hilo
        AsyncTask.execute(() -> {

            if (logInCallback != null) {
                boolean existe = getUserDao().checkLogIn(email, password);
                if (existe) {
                    UserItem userLogged = getUserDao().getLogIn(email, password);
                    RestaurantItem restaurantLogged = getRestaurantDao().getRestaurantWithId(userLogged.restaurantId);
                    logInCallback.logInCheck(!getUserDao().checkLogIn(email, password), restaurantLogged, userLogged);
                } else {
                    RestaurantItem restaurantnull = new RestaurantItem();
                    UserItem usernull = new UserItem();
                    logInCallback.logInCheck(true, restaurantnull, usernull);
                }
            }

//                if(getUserDao().checkLogIn(email, password)){
//                    logInCallback.logInCheck(false,restaurantLogged,userLogged);
//                }else{
//                    logInCallback.logInCheck(true,restaurantLogged,userLogged);
//                }
        });
    }

    @Override
    public void editUser(int idRestaurant, String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo, EditUserCallback editUserCallback) {
        Log.e(TAG, "editUser()");
        //crea hilo asincrono
        //ejecuta el hilo
        AsyncTask.execute(() -> {
            RestaurantItem editRestaurant = getRestaurantDao().getRestaurantWithId(idRestaurant);
            UserItem editUser = getUserDao().getUserWithRestaurantId(idRestaurant);

            editRestaurant.location = ubicacion;
            editRestaurant.webpage = webpage;
            editRestaurant.description = descripcion;
            editRestaurant.title = nombre;
            editRestaurant.logo = logo;
            getRestaurantDao().updateRestaurant(editRestaurant);

            editUser.password = password;
            editUser.email = email;
            getUserDao().updateUser(editUser);

            if (editUserCallback != null) {
                editUserCallback.changeData(false, editRestaurant, editUser);
            }
        });
    }

    @Override
    public void getMyMenuList(RestaurantItem restaurant, GetMyMenusListCallback callback) {
        Log.e(TAG, "getMyMenuList()");
        AsyncTask.execute(() -> {
            if (callback != null) {
                callback.setMenuList(getMenuDao().loadMenus(restaurant.id));
            }
        });
    }

    @Override
    public void deleteMenu(MenuItem menuItem, DeleteMenuCallback deleteMenuCallback) {
        Log.e(TAG, "deleteMenu()");
        AsyncTask.execute(() -> {
            int restaurantId = menuItem.restaurantId;
            getMenuDao().deleteMenu(menuItem);

            List<MenuItem> menusActualizados = getMenuDao().loadMenus(restaurantId);

            if (deleteMenuCallback != null) {
                deleteMenuCallback.setMyMenuList(false, menusActualizados);
            }
        });
    }

    @Override
    public void editMenu(int idMenu, String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida, EditMenuCallback editMenuCallback) {
        Log.e(TAG, "editMenu()");
        AsyncTask.execute(() -> {
            MenuItem editedMenu = getMenuDao().loadMenuWithId(idMenu);
            editedMenu.name = nombre;
            editedMenu.price = precio;
            editedMenu.image = imagen;
            editedMenu.starter = entrante;
            editedMenu.firstCourse = primero;
            editedMenu.secondCourse = segundo;
            editedMenu.dessert = postre;
            editedMenu.beverage = bebida;
            getMenuDao().updateMenu(editedMenu);

            int restaurantId = editedMenu.restaurantId;
            //RestaurantItem restaurantUpdated = getRestaurantDao().getRestaurantWithId(restaurantId);
            List<MenuItem> menusActualizados = getMenuDao().loadMenus(restaurantId);

            if (editMenuCallback != null) {
                editMenuCallback.setMyMenuListEdited(false, menusActualizados);
            }
        });
    }

    @Override
    public void createMenu(RestaurantItem restaurant, String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida, CreateMenuCallback createMenuCallback) {
        Log.e(TAG, "createMenu()");
        //crea hilo asincrono
        //ejecuta el hilo
        AsyncTask.execute(() -> {
            MenuItem newMenu = new MenuItem();
            newMenu.restaurantId = getRestaurantDao().getId(restaurant.title);
            newMenu.name = nombre;
            newMenu.price = precio;
            newMenu.image = imagen;
            newMenu.starter = entrante;
            newMenu.firstCourse = primero;
            newMenu.secondCourse = segundo;
            newMenu.dessert = postre;
            newMenu.beverage = bebida;

            getMenuDao().insertMenu(newMenu);
            List<MenuItem> menusActualizados = getMenuDao().loadMenus(newMenu.restaurantId);


            if (createMenuCallback != null) {
                createMenuCallback.addMenu(false, menusActualizados);
            }
        });
    }
}
