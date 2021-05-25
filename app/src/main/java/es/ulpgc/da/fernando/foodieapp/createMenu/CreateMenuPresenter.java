package es.ulpgc.da.fernando.foodieapp.createMenu;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;


public class CreateMenuPresenter implements CreateMenuContract.Presenter {

    public static String TAG = CreateMenuPresenter.class.getSimpleName();

    private WeakReference<CreateMenuContract.View> view;
    private final CreateMenuState state;
    private CreateMenuContract.Model model;
    private final FoodieMediator mediator;

    public CreateMenuPresenter(FoodieMediator mediator) {
        this.mediator = mediator;
        state = mediator.getCreateMenuState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");
        RestaurantItem restaurant = getRestaurantData();
        if (restaurant != null) {
            //modify state
            state.restaurant = restaurant;
        }
    }

    //obtiene el restaurante almacenado en el mediador
    private RestaurantItem getRestaurantData() {
        RestaurantItem restaurant = mediator.getRestaurant();
        return restaurant;
    }

    @Override
    public void createMenu(String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida) {
        Log.e(TAG, "createMenu()");
        if (nombre.isEmpty() || imagen.isEmpty() || entrante.isEmpty() || primero.isEmpty() || segundo.isEmpty() || postre.isEmpty() || bebida.isEmpty()) {
            state.toast = model.getEmptyAdvice();
            view.get().showToast(state);
        } else {
            model.createMenu(state.restaurant.id, nombre, precio, imagen, entrante, primero, segundo, postre, bebida, (error, menusActualizados) -> {
                Log.e(TAG, "userAdded()");
                if (!error) {
                    // set state
                    state.toast = model.getCreatedAdvice();
                    state.menus = menusActualizados;
                    passMenusDataToOthers(menusActualizados);
                    // update view
                    view.get().showToastThread(state);
                    goToMyMenus();
                } else {
                    state.toast = model.getErrorAdvice();
                    view.get().showToastThread(state);
                }
            });
        }
    }

    //almacena en ek mediador la info a pasar
    private void passMenusDataToOthers(List<MenuItem> items) {
        mediator.setMenuList(items);
    }


    public void goToMyMenus() {
        Log.e(TAG, "goToMyMenus()");
        view.get().navigateToMyMenus();
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
    public void injectView(WeakReference<CreateMenuContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(CreateMenuContract.Model model) {
        this.model = model;
    }

}
