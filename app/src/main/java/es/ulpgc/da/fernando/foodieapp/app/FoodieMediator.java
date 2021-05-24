package es.ulpgc.da.fernando.foodieapp.app;

import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.data.UserItem;
import es.ulpgc.da.fernando.foodieapp.editAccount.EditAccountState;
import es.ulpgc.da.fernando.foodieapp.home.HomeState;
import es.ulpgc.da.fernando.foodieapp.login.LoginState;
import es.ulpgc.da.fernando.foodieapp.menuDetail.MenuDetailState;
import es.ulpgc.da.fernando.foodieapp.myMenus.MyMenusState;
import es.ulpgc.da.fernando.foodieapp.register.RegisterState;
import es.ulpgc.da.fernando.foodieapp.restaurantCarta.RestaurantCartaState;
import es.ulpgc.da.fernando.foodieapp.restaurantProfile.RestaurantProfileState;
import es.ulpgc.da.fernando.foodieapp.restaurantsList.RestaurantsListState;

public class FoodieMediator {

    //instancia del mediador
    private static FoodieMediator INSTANCE;

    //screen states
    private HomeState homeState = new HomeState();
    private LoginState loginState = new LoginState();
    private RegisterState registerState = new RegisterState();
    private RestaurantProfileState restaurantProfileState = new RestaurantProfileState();
    private EditAccountState editAccountState = new EditAccountState();
    private MyMenusState myMenusState = new MyMenusState();


    private RestaurantsListState restaurantsListState = new RestaurantsListState();
    private RestaurantCartaState restaurantCartaState = new RestaurantCartaState();
    private MenuDetailState menuDetailState = new MenuDetailState();

    //elementos
    private RestaurantItem restaurant;
    private MenuItem menu;
    private UserItem user;


    private FoodieMediator() {
    }

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

    public RegisterState getRegisterState() {
        return registerState;
    }

    public RestaurantProfileState getRestaurantProfileState() {
        return restaurantProfileState;
    }

    public EditAccountState getEditAccountState() {
        return editAccountState;
    }

    public MyMenusState getMyMenusState() {
        return myMenusState;
    }


    public RestaurantsListState getRestaurantsListState() {
        return restaurantsListState;
    }

    public RestaurantCartaState getRestaurantCartaState() {
        return restaurantCartaState;
    }

    public MenuDetailState getMenuDetailState() {
        return menuDetailState;
    }


    //modifica la info a enviar a RestaurantCarta
    public void setRestaurant(RestaurantItem item) {
        restaurant = item;
    }

    //devuelve la categoria a la que pertenece en RestaurantCarta
    public RestaurantItem getRestaurant() {
        RestaurantItem item = restaurant;
        //restaurant = null;
        return item;
    }


    //modifica la info a enviar a MenuDetail
    public void setMenu(MenuItem item) {
        menu = item;
    }

    //devuelve el producto al que pertenece en el detalle
    public MenuItem getMenu() {
        MenuItem item = menu;
        //menu = null;
        return item;
    }

    public void setUser(UserItem item) {
        user = item;
    }

    public UserItem getUser() {
        UserItem item = user;
        //user = null;
        return item;
    }

}
