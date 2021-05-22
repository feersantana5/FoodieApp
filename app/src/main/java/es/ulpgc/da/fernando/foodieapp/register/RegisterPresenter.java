package es.ulpgc.da.fernando.foodieapp.register;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.data.UserItem;

public class RegisterPresenter implements RegisterContract.Presenter {

    public static String TAG = RegisterPresenter.class.getSimpleName();

    private WeakReference<RegisterContract.View> view;
    private RegisterState state;
    private RegisterContract.Model model;
    private FoodieMediator mediator;

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

        // call the model and update the state
        // state.data = model.getStoredData();

        // use passed state if is necessary
        // PreviousToRegisterState savedState = getStateFromPreviousScreen();
        //if (savedState != null) {
        // update the model if is necessary
        //  model.onDataFromPreviousScreen(savedState.data);
        // update the state if is necessary
        //state.data = savedState.data;
        // }
    }

    //reinicia activity tras rotar
    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");
        //update the model if is necessary
        //model.setToastState(state.toast);
        Log.e(TAG, "Toast: " + state.toast);

    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");
        // use passed state if is necessary
        //NextToRegisterState savedState = getStateFromNextScreen();
        // if (savedState != null) {
        // update the model if is necessary
        // model.onDataFromNextScreen(savedState.data);
        // update the state if is necessary
        // state.data = savedState.data;
        // }

        // call the model and update the state
        //state.data = model.getStoredData();
        // update the view
        //view.get().onDataUpdated(state);
    }

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
        //añade los datos al modelo de forma asincrona y cuando los tiene lo notifica
        /*model.registrarUsuario(email, password, ubicacion, webpage, descripcion, nombre, logo, new RepositoryContract.RegistroUsuarioCallback() {
            @Override
            public void setUserLists(List<RestaurantItem> restaurants, List<UserItem> users) {
                // rellena la lista con las categorias creadas
                state.restaurants = restaurants;
                state.users = users;

                // update view
                //view.get().displayRestaurantsListData(state);
            }
        });*/
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
