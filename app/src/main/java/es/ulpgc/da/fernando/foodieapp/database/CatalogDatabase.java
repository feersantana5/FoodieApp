package es.ulpgc.da.fernando.foodieapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

//nombre de la bbdd y referencia a las clases donde se encuentran sus tablas
@Database(entities = {RestaurantItem.class, MenuItem.class}, version = 1)

//extiende de RoomDatabase
public abstract class CatalogDatabase extends RoomDatabase {

    //crea DAO de categoria y productos aue es lo que permite acceder a ellas
    public abstract RestaurantDao restaurantDao();
    public abstract MenuDao menuDao();
}
