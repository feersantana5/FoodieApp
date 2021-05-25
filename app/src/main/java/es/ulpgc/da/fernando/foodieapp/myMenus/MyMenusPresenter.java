package es.ulpgc.da.fernando.foodieapp.myMenus;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public class MyMenusPresenter implements MyMenusContract.Presenter {

    public static String TAG = MyMenusPresenter.class.getSimpleName();

    private WeakReference<MyMenusContract.View> view;
    private MyMenusState state;
    private MyMenusContract.Model model;
    private final FoodieMediator mediator;

    public MyMenusPresenter(FoodieMediator mediator) {
        this.mediator = mediator;
        state = mediator.getMyMenusState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");
        // initialize the state if is necessary
        if (state == null) {
            state = new MyMenusState();
        }
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");
        List<MenuItem> menus = getMenuListUpdated();
        if (menus != null) {
            //modify state
            state.menus = menus;
            // update view
            view.get().displayMyMenusListData(state);
        }
    }

    //obtiene el restaurante almacenado en el mediador
    private List<MenuItem> getMenuListUpdated() {
        List<MenuItem> menus = mediator.getMenuList();
        return menus;
    }

    @Override
    public void fetchMyMenusListData() {
        Log.e(TAG, "fetchMyMenusListData()");
        // set passed state
        //obtiene el restaurante al que pertenece, almacenada en el mediador
        RestaurantItem restaurant = getUserRestaurantData();
        if (restaurant != null) {
            Log.e(TAG, "fetchMyMenusListData()" + restaurant);

            //modify state
            state.restaurant = restaurant;
        }
        // call the model
        //llama al modelo para que obtenga los datos de forma async usandp patron obs
        model.fetchMyMenusListData(state.restaurant, menus -> {
            // set state
            state.menus = menus;
            // update view
            view.get().displayMyMenusListData(state);
        });
    }

    //obtiene el restaurante almacenado en el mediador
    private RestaurantItem getUserRestaurantData() {
        RestaurantItem restaurant = mediator.getRestaurant();
        return restaurant;
    }

    @Override
    public void deleteMenu(MenuItem menuItem) {
        Log.e(TAG, "deleteMenu(): " + menuItem);
        model.deleteMenu(menuItem, (error, menuItems) -> {
            if (!error) {
                // set state
                state.toast = model.getDeletedAdvice();
                state.menus = menuItems;
                // update view
                view.get().displayMyMenusListData(state);
                view.get().showToastThread(state);
            }
        });
    }

    @Override
    public void goToEditMenu(MenuItem menu) {
        Log.e(TAG, "goToEditMenu()");
        state.menu = menu;
        passMenuDataToOthers(state.menu);
        Log.e(TAG, state.menu.name);
        view.get().navigateToEditMenu();
    }

    //almacena en el mediador la info a pasar
    private void passMenuDataToOthers(MenuItem item) {
        mediator.setMenu(item);
    }

    @Override
    public void goToCreateMenu() {
        RestaurantItem myRestaurant = getUserRestaurantData();
        state.restaurant = myRestaurant;
        Log.e(TAG, "goToCreateMenu()" + state.restaurant);
        passRestaurantToOthers(state.restaurant);
        view.get().navigateToCreateMenu();
    }

    //almacena en el mediador la info a pasar
    private void passRestaurantToOthers(RestaurantItem item) {
        mediator.setRestaurant(item);
    }

    @Override
    public void goToHomeNav() {
        Log.e(TAG, "goToHomeNav()");
        //cambia de activity
        view.get().navigateToHomeNav();
    }

    @Override
    public void goToProfileNav() {
        Log.e(TAG, "goToProfileNav()");
        //cambia de activity
        view.get().navigateToProfileNav();
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
    public void injectView(WeakReference<MyMenusContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(MyMenusContract.Model model) {
        this.model = model;
    }

}
