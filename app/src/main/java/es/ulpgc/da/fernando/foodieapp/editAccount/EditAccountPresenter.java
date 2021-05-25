package es.ulpgc.da.fernando.foodieapp.editAccount;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.data.UserItem;

public class EditAccountPresenter implements EditAccountContract.Presenter {

    public static String TAG = EditAccountPresenter.class.getSimpleName();

    private WeakReference<EditAccountContract.View> view;
    private EditAccountState state;
    private EditAccountContract.Model model;
    private final FoodieMediator mediator;

    public EditAccountPresenter(FoodieMediator mediator) {
        this.mediator = mediator;
        state = mediator.getEditAccountState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");
        // initialize the state if is necessary
        if (state == null) {
            state = new EditAccountState();
        }
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");
        RestaurantItem restaurant = getUserRestaurantData();
        UserItem user = getUserData();
        if (restaurant != null && user != null) {
            //modify state
            state.restaurant = restaurant;
            state.user = user;
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
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed()");
    }

    @Override
    public void editAccount(String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo) {
        Log.e(TAG, "editAccount()");
        if (email.isEmpty() || password.isEmpty() || ubicacion.isEmpty() || webpage.isEmpty() || descripcion.isEmpty() || nombre.isEmpty() || logo.isEmpty()) {
            state.toast = model.getEmptyAdvice();
            view.get().showToast(state);
        } else {
            model.editUserAccount(state.restaurant.id, email, password, ubicacion, webpage, descripcion, nombre, logo, (error, restaurantEdited, userEdited) -> {
                if (!error) {
                    state.toast = model.getUpdatedAdvice();
                    state.restaurant = restaurantEdited;
                    state.user = userEdited;
                    passRestaurantDataToOthers(restaurantEdited);
                    passUserDataToOthers(userEdited);
                    view.get().showToastThread(state);
                    goToRestaurantProfile();
                } else {
                    state.toast = model.getErrorAdvice();
                    view.get().showToastThread(state);
                }
            });
        }
    }

    //almacena en ek mediador la info a pasar
    private void passRestaurantDataToOthers(RestaurantItem item) {
        mediator.setRestaurant(item);
    }

    //almacena en ek mediador la info a pasar
    private void passUserDataToOthers(UserItem item) {
        mediator.setUser(item);
    }

    @Override
    public void goToRestaurantProfile() {
        Log.e(TAG, "goToRestaurantProfile()");
        view.get().navigateToRestaurantProfile();
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
    public void injectView(WeakReference<EditAccountContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(EditAccountContract.Model model) {
        this.model = model;
    }

}
