package es.ulpgc.da.fernando.foodieapp.restaurantCarta;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public class RestaurantCartaPresenter implements RestaurantCartaContract.Presenter {

    public static String TAG = RestaurantCartaPresenter.class.getSimpleName();

    private WeakReference<RestaurantCartaContract.View> view;
    private RestaurantCartaState state;
    private RestaurantCartaContract.Model model;
    private FoodieMediator mediator;

    public RestaurantCartaPresenter(FoodieMediator mediator) {
        //obtiene el estado del screen del mediador
        this.mediator = mediator;
        state = mediator.getRestaurantCartaState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");// initialize the state if is necessary
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");// update the model if is necessary
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");// use passed state if is necessary
    }

    @Override
    public void fetchMenuListData() {
        Log.e(TAG, "fetchMenuListData()");

        // set passed state
        //obtiene el restaurante al que pertenece, almacenada en el mediador
        RestaurantItem restaurant = getDataFromRestaurantListScreen();
        if (restaurant != null) {
            //modify state
            state.restaurant = restaurant;
        }

        // call the model
        //llama al modelo para que obtenga los datos de forma async usandp patron obs
        model.fetchMenuListData(state.restaurant, new RepositoryContract.GetMenuListCallback() {

            @Override
            public void setMenuList(List<MenuItem> menus) {
                // set state
                state.menus = menus;
                // update view
                view.get().displayMenuListData(state);
            }
        });
    }

    //obtiene el restaurante almacenado en el mediador
    private RestaurantItem getDataFromRestaurantListScreen() {
        RestaurantItem restaurant = mediator.getRestaurant();
        return restaurant;
    }

    //notificado de que un item es seleccionado
    @Override
    public void selectMenuListData(MenuItem item) {
        //almacena el item en el mediadior
        passDataToProductDetailScreen(item);
        //cambia de activity
        view.get().navigateToMenuDetail();
    }

    private void passDataToProductDetailScreen(MenuItem item) {
        mediator.setMenu(item);
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
    public void injectView(WeakReference<RestaurantCartaContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(RestaurantCartaContract.Model model) {
        this.model = model;
    }

}
