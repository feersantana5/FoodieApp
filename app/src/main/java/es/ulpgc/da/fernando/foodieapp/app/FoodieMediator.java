package es.ulpgc.da.fernando.foodieapp.app;

import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.home.HomeState;
import es.ulpgc.da.fernando.foodieapp.login.LoginState;
import es.ulpgc.da.fernando.foodieapp.restaurantsList.RestaurantsListState;

public class FoodieMediator {

    //instancia del mediador
    private static FoodieMediator INSTANCE;

    //screen states
    private HomeState homeState = new HomeState();
    private LoginState loginState = new LoginState();
    private RestaurantsListState restaurantsListState = new RestaurantsListState();

    //elementos
    private RestaurantItem restaurant;
    private MenuItem menu;

    private FoodieMediator() { }

    public static void resetInstance() {
        INSTANCE = null;
    }


    public static FoodieMediator getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FoodieMediator();
        }
        return INSTANCE;
    }

    //delvuelve el estado del screen en constructor del presenter
    public HomeState getHomeState() {
        return homeState;
    }

    public LoginState getLoginState() {
        return loginState;
    }

    public RestaurantsListState getRestaurantsListState() {
        return restaurantsListState;
    }

    //modifica la info a enviar a RestaurantCarta
    public void setRestaurant(RestaurantItem item) {
        restaurant = item;
    }
    //devuelve la categoria a la que pertenece en RestaurantCarta
    public RestaurantItem getRestaurant() {
        RestaurantItem item = restaurant;
        //category = null;
        return item;
    }

    //modifica la info a enviar a MenuDetail
    public void setMenu(MenuItem item) {
        menu = item;
    }
    //devuelve el producto al que pertenece en el detalle
    public MenuItem getMenu() {
        MenuItem item = menu;
        //product = null;
        return item;
    }




}
