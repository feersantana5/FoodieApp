package es.ulpgc.da.fernando.foodieapp.app;

import es.ulpgc.da.fernando.foodieapp.home.HomeState;
import es.ulpgc.da.fernando.foodieapp.login.LoginState;

public class FoodieMediator {

    //instancia del mediador
    private static FoodieMediator INSTANCE;

    //screen states
    private HomeState homeState =  new HomeState();
    private LoginState loginState = new LoginState();

    //elementos
    //private MenuItem menu;
    //private TestaurantItem restaurant;


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
    public HomeState getHomeState() {return homeState;}
    public LoginState getLoginState() {return loginState;}



//    public NextToState getNextScreenState() {
//        return null;
//    }
//    public void setNextScreenState(ToNextState state) { }
//    public void setPreviousScreenState(ToPreviousState state) { }
//    public PreviousToState getPreviousScreenState() {
//        return null;
//    }

}
