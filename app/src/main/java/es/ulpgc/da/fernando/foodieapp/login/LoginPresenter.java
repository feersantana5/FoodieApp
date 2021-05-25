package es.ulpgc.da.fernando.foodieapp.login;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.data.UserItem;


public class LoginPresenter implements LoginContract.Presenter {

    public static String TAG = LoginPresenter.class.getSimpleName();

    private WeakReference<LoginContract.View> view;
    private LoginState state;
    private LoginContract.Model model;
    private final FoodieMediator mediator;

    public LoginPresenter(FoodieMediator mediator) {
        this.mediator = mediator;
        state = mediator.getLoginState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");
        // initialize the state if is necessary
        if (state == null) {
            state = new LoginState();
        }
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");
    }


    @Override
    public void checkLogin(String email, String password) {
        Log.e(TAG, "checkLogin()");
        if (email.isEmpty() || password.isEmpty()) {
            state.toast = model.getEmptyAdvice();
            view.get().showToast(state);
        } else {
            logIn(email, password);
        }
    }

    public void logIn(String email, String password) {
        model.logIn(email, password, (error, restaurant, user) -> {
            if (!error) {
                state.sessionEnabled = true;
                //mediator.getLoginToOtherState();
                passRestaurantDataToOthers(restaurant);
                passUserDataToOthers(user);
                goToRestaurantProfile();
            } else {
                state.toast = model.getErrorAdvice();
                view.get().showToastThread(state);
            }
        });
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
    public void goToRestaurantProfile() {
        Log.e(TAG, "goToRestaurantProfile()");
        //TODO: verificar registro e ir al perfil
        view.get().navigateToRestaurantProfile();
    }

    @Override
    public void goToRegister() {
        Log.e(TAG, "goToRegister()");
        view.get().navigateToRegister();
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
    public void injectView(WeakReference<LoginContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(LoginContract.Model model) {
        this.model = model;
    }

}

