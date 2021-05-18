package es.ulpgc.da.fernando.foodieapp.data;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//config tabla room
@Entity(tableName = "restaurants") //nombre de la tabla
public class RestaurantItem {

    @PrimaryKey //identificador unico e irrepetible
    public int id;

    public String title;
    public String logo;
    public String description;

    public String location;
    public String webpage;

    @Ignore //hace que no aparezca en la tabla
    @SerializedName("menus")
    //unica  etiqueta json, hace referencia a la identificacion del elem en json
    public List<MenuItem> items;


}
