package es.ulpgc.da.fernando.foodieapp.login;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;


public class LoginPresenter implements LoginContract.Presenter {

    public static String TAG = LoginPresenter.class.getSimpleName();

    private WeakReference<LoginContract.View> view;
    private LoginState state;
    private LoginContract.Model model;
    private FoodieMediator mediator;

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
        // call the model and update the state
        //state.data = model.getStoredData();
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");
        // update the model if is necessary
        // model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");
    }

    //se comprueba si se han introducidos los campos requeridos
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
        model.logIn(email, password, new RepositoryContract.LogInCallback() {
            @Override
            public void logInCheck(boolean error) {
                if (!error) {
                    state.sessionEnabled = true;
                    goToRestaurantProfile();
                } else {
                    state.toast = model.getErrorAdvice();
                    view.get().showToastThread(state);
                }
            }
        });
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

