package es.ulpgc.da.fernando.foodieapp.data;

//config tabla room

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "menus", //nombre tabla
        foreignKeys = @ForeignKey(  //valor que obtiene de otra tabla y sus caract aqui
                entity = RestaurantItem.class,
                parentColumns = "id",
                childColumns = "restaurant_Id", //tabla relacion 1-n
                onDelete = CASCADE
        )
)
public class MenuItem {

    @PrimaryKey(autoGenerate = true) //identificador unico e irrepetible, se crearan secuenciales
    public int id;

    public String name;
    public int price;
    public String image;
    public String starter;
    public String firstCourse;
    public String secondCourse;
    public String dessert;
    public String beverage;

    @ColumnInfo(name = "restaurant_Id")
    //columna customizada, obtiene el foreignkey que lo relaciona con la otra tabla
    public int restaurantId;


}
