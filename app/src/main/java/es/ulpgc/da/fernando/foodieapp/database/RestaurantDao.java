package es.ulpgc.da.fernando.foodieapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

@Dao
public interface RestaurantDao {

    @Query("SELECT * FROM restaurants")
    List<RestaurantItem> loadRestaurants();

    @Insert(onConflict = OnConflictStrategy.REPLACE) // si inserto restaurante que ya existe, la machaco, sino saldria error
    void insertRestaurant(RestaurantItem restaurant);

}
