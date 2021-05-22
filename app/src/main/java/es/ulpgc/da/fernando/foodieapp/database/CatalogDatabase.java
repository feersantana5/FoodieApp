package es.ulpgc.da.fernando.foodieapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.data.UserItem;

//nombre de la bbdd y referencia a las clases donde se encuentran sus tablas
@Database(entities = {RestaurantItem.class, MenuItem.class, UserItem.class}, version = 1)

//extiende de RoomDatabase
public abstract class CatalogDatabase extends RoomDatabase {

    //crea DAO de restaurantes, menus y usuarios que es lo que permite acceder a ellas
    public abstract RestaurantDao restaurantDao();

    public abstract MenuDao menuDao();

    public abstract UserDao userDao();

}
