package es.ulpgc.da.fernando.foodieapp.restaurantProfile;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.data.UserItem;

public class RestaurantProfilePresenter implements RestaurantProfileContract.Presenter {

    public static String TAG = RestaurantProfilePresenter.class.getSimpleName();

    private WeakReference<RestaurantProfileContract.View> view;
    private RestaurantProfileState state;
    private RestaurantProfileContract.Model model;
    private final FoodieMediator mediator;

    public RestaurantProfilePresenter(FoodieMediator mediator) {
        this.mediator = mediator;
        state = mediator.getRestaurantProfileState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");
        // initialize the state if is necessary
        if (state == null) {
            state = new RestaurantProfileState();
        }
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");
        // update the model if is necessary
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");
        // use passed state if is necessary
        RestaurantItem restaurant = getUserRestaurantData();
        UserItem user = getUserData();
        if (restaurant != null && user != null) {
            //modify state
            state.restaurant = restaurant;
            state.user = user;
            Log.e(TAG, "onResume()" + restaurant.id);

            // update view
            view.get().displayData(state);
        }
    }

    //obtiene el restaurante almacenado en el mediador
    private RestaurantItem getUserRestaurantData() {
        RestaurantItem restaurant = mediator.getRestaurant();
        return restaurant;
    }

    //obtiene el usuario almacenado en el mediador
    private UserItem getUserData() {
        UserItem user = mediator.getUser();
        return user;
    }

    @Override
    public void goToMyMenus() {
        Log.e(TAG, "goToMyMenus()");
        passRestaurantDataToOthers(state.restaurant);
        Log.e(TAG, "goToMyMenus()" + state.restaurant.id);
        view.get().navigateToMyMenus();
    }

    @Override
    public void goToEditAccount() {
        Log.e(TAG, "goToEditAccount()");
        passRestaurantDataToOthers(state.restaurant);
        passUserDataToOthers(state.user);
        view.get().navigateToEditAccount();
    }

    //almacena en el mediador la info a pasar
    private void passRestaurantDataToOthers(RestaurantItem item) {
        mediator.setRestaurant(item);
    }

    //almacena en el mediador la info a pasar
    private void passUserDataToOthers(UserItem item) {
        mediator.setUser(item);
    }

    @Override
    public void goToHomeNav() {
        Log.e(TAG, "goToHomeNav()");
        //cambia de activity
        view.get().navigateToHomeNav();
    }

    @Override
    public void goToMyMenusListNav() {
        Log.e(TAG, "goToMyMenusListNav()");
        //cambia de activity
        view.get().navigateToMenuNav();
    }

    @Override
    public void closeSession() {
        Log.e(TAG, "closeSession()");
        //cambia de activity
        view.get().showAlertDialogNav();
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed()");
    }

    @Override
    public void onPause() {
        Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy()");
    }

    @Override
    public void injectView(WeakReference<RestaurantProfileContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(RestaurantProfileContract.Model model) {
        this.model = model;
    }

}
