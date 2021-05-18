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
    public void loadCatalog(final boolean clearFirst, final FetchCatalogDataCallback callback) {
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

                //si no hay categorias, error al cargar al json pq esta vacio
                if (getRestaurantDao().loadRestaurants().size() == 0) {
                    error = !loadCatalogFromJSON(loadJSONFromAsset());
                }

                if (callback != null) {
                    callback.onCatalogDataFetched(error); //notifica si hay error o no
                }
            }
        });
    }

    private RestaurantDao getRestaurantDao() {
        return database.restaurantDao();
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

                //recorre los menus e inserta
                for (RestaurantItem restaurant : restaurants) {
                    for (MenuItem menu : restaurant.items) {
                        menu.restaurantId = menu.id;
                        getMenuDao().insertMenu(menu);
                    }
                }
                return true;
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


    //obtiene la lista de categorias y notifica cuando las tiene
    @Override
    public void getRestaurantsList(final GetRestaurantsListCallback callback) {
        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if (callback != null) {
                    callback.setRestaurantsList(getRestaurantDao().loadRestaurants());
                }
            }
        });

    }
}
