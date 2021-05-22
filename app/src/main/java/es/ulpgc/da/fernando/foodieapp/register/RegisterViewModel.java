package es.ulpgc.da.fernando.foodieapp.register;

import java.util.List;

import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.data.UserItem;

public class RegisterViewModel {
    public List<UserItem> users; //lista de usuarios
    public List<RestaurantItem> restaurants; //lista de restaurantes

    String toast;
}
