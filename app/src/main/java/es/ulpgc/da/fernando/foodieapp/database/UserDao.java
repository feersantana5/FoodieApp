package es.ulpgc.da.fernando.foodieapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import es.ulpgc.da.fernando.foodieapp.data.UserItem;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
        //si inserto menu que ya existe, la machaco, sino saldria error
    void insertUser(UserItem menu);

    @Query("SELECT * FROM users Where email = :email AND password = :password")
    boolean checkLogIn(String email, String password);

    // @Query("SELECT * FROM users WHERE restaurant_Id=:restaurantId")
    // List<UserItem> loadUsers(final int restaurantId);

    //TODO: hacer esto para editar cuenta
    //@Delete
    //void editMenu(MenuItem menu);
    //@Update
    //void updateMenu(MenuItem menu);
}
