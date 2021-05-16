package es.ulpgc.da.fernando.foodieapp.data;

import android.content.Context;

import androidx.room.Room;

import es.ulpgc.da.fernando.foodieapp.database.CatalogDatabase;

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

}
