package es.ulpgc.da.fernando.foodieapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.ulpgc.da.fernando.foodieapp.data.MenuItem;

@Dao
public interface MenuDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        // si inserto menu que ya existe, la machaco, sino saldria error
    void insertMenu(MenuItem menu);

    @Query("SELECT * FROM menus WHERE restaurant_Id=:restaurantId")
    List<MenuItem> loadMenus(final int restaurantId);

    @Query("SELECT * FROM menus WHERE id=:id")
    MenuItem loadMenuWithId(final int id);

    @Delete
    void deleteMenu(MenuItem menu);

    @Update
    void updateMenu(MenuItem menu);
}
