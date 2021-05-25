package es.ulpgc.da.fernando.foodieapp.register;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.data.UserItem;

public class RegisterPresenter implements RegisterContract.Presenter {

    public static String TAG = RegisterPresenter.class.getSimpleName();

    private WeakReference<RegisterContract.View> view;
    private RegisterState state;
    private RegisterContract.Model model;
    private final FoodieMediator mediator;

    public RegisterPresenter(FoodieMediator mediator) {
        this.mediator = mediator;
        state = mediator.getRegisterState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");
        // initialize the state if is necessary
        if (state == null) {
            state = new RegisterState();
        }
    }

    //reinicia activity tras rotar
    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");
    }

    @Override
    public void createRestaurant(String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo) {
        Log.e(TAG, "createRestaurant()");
        if (email.isEmpty() || password.isEmpty() || ubicacion.isEmpty() || webpage.isEmpty() || descripcion.isEmpty() || nombre.isEmpty() || logo.isEmpty()) {
            state.toast = model.getEmptyAdvice();
            view.get().showToast(state);
        } else {
            verifyDatabase(email, password, ubicacion, webpage, descripcion, nombre, logo);
        }
    }

    public void verifyDatabase(String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo) {
        Log.e(TAG, "verifyDatabase()");
        // call the model
        //añade los datos de forma asincrona y cuando los tiene lo notifica
        model.registrarUsuario(email, password, ubicacion, webpage, descripcion, nombre, logo, (error, restaurant, user) -> {
            Log.e(TAG, "registrarUsuario()");
            if (!error) {
                state.toast = model.getRegisterAdvice();
                state.sessionEnabled = true;
                passRestaurantDataToOthers(restaurant);
                passUserDataToOthers(user);
                view.get().showToastThread(state);
                goToRestaurantProfile();
            } else {
                state.toast = model.getErrorAdvice();
                view.get().showToastThread(state);
            }
        });
    }
    public void goToRestaurantProfile() {
        Log.e(TAG, "goToRestaurantProfile()");
        view.get().navigateToRestaurantProfile();
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
    public void injectView(WeakReference<RegisterContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(RegisterContract.Model model) {
        this.model = model;
    }

}
