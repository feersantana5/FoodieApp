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
import java.util.Arrays;
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

    private CatalogDatabase database;
    private Context context;

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
        AsyncTask.execute(new Runnable() {

            //ejecuta el hilo
            @Override
            public void run() {
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
                final List<RestaurantItem> restaurants = Arrays.asList(gson.fromJson(jsonArray.toString(), RestaurantItem[].class));

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
                        final List<UserItem> users = Arrays.asList(gson.fromJson(jsonSegundoArray.toString(), UserItem[].class));
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

/*
    public boolean getUserFromJson(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonSegundoArray = jsonObject.getJSONArray("users"); //elemento padre del json
            if (jsonSegundoArray.length() > 0) { //si hay contenido
                //convierte la lista de restaurantes en una lista serializable, convirtiendo los elementos a string
                final List<UserItem> users = Arrays.asList(gson.fromJson(jsonSegundoArray.toString(), UserItem[].class));
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
    }*/

    //obtiene la lista de categorias y notifica cuando las tiene
    @Override
    public void getRestaurantsList(final GetRestaurantsListCallback callback) {
        Log.e(TAG, "getRestaurantsList()");
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if (callback != null) {
                    callback.setRestaurantsList(getRestaurantDao().loadRestaurants());
                }
            }
        });

    }

//MENUS

    //desde el modelo, obtiene la lista de modelos segun el restaurante y notifica
    @Override
    public void getMenuList(final RestaurantItem restaurant, final GetMenuListCallback callback) {
        Log.e(TAG, "getMenuList()");
        getMenuList(restaurant.id, callback);
    }

    //llamado desde el de arriba
    @Override
    public void getMenuList(final int restaurantId, final GetMenuListCallback callback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                if (callback != null) {
                    callback.setMenuList(getMenuDao().loadMenus(restaurantId));
                }
            }
        });
    }


    @Override
    public void registrarUsuario(String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo, RegistroUsuarioCallback registroUsuarioCallback) {
        Log.e(TAG, "registrarUsuario()");
        //crea hilo asincrono
        AsyncTask.execute(new Runnable() {

            //ejecuta el hilo
            @Override
            public void run() {
                RestaurantItem newRestaurant = new RestaurantItem();
                //newRestaurant.id = (int) (Math.random()*1000000); //numero grande para evitar que coincida
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
            }
        });
    }

    @Override
    public void logIn(String email, String password, LogInCallback logInCallback) {
        Log.e(TAG, "logIn()");
        //crea hilo asincrono
        AsyncTask.execute(new Runnable() {

            //ejecuta el hilo
            @Override
            public void run() {
                UserItem userLogged = getUserDao().getLogIn(email, password);
                RestaurantItem restaurantLogged = getRestaurantDao().getRestaurantWithId(userLogged.restaurantId);
                if (logInCallback != null) {
                    logInCallback.logInCheck(!getUserDao().checkLogIn(email, password), restaurantLogged, userLogged);
                }

//                if(getUserDao().checkLogIn(email, password)){
//                    logInCallback.logInCheck(false,restaurantLogged,userLogged);
//                }else{
//                    logInCallback.logInCheck(true,restaurantLogged,userLogged);
//                }
            }
        });
    }

    @Override
    public void editUser(int idRestaurant, String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo, EditUserCallback editUserCallback) {
        Log.e(TAG, "editUser()");
        //crea hilo asincrono
        AsyncTask.execute(new Runnable() {

            //ejecuta el hilo
            @Override
            public void run() {
                RestaurantItem editRestaurant = getRestaurantDao().getRestaurantWithId(idRestaurant);
                UserItem editUser  = getUserDao().getUserWithRestaurantId(idRestaurant);

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
            }
        });
    }


}
