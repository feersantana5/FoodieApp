package es.ulpgc.da.fernando.foodieapp.restaurantsList;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public class RestaurantsListPresenter implements RestaurantsListContract.Presenter {

    public static String TAG = RestaurantsListPresenter.class.getSimpleName();

    private WeakReference<RestaurantsListContract.View> view;
    private RestaurantsListState state;
    private RestaurantsListContract.Model model;
    private FoodieMediator mediator;

    public RestaurantsListPresenter(FoodieMediator mediator) {
        this.mediator = mediator;
        state = mediator.getRestaurantsListState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");// initialize the state if is necessary...
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");// update the model if is necessary...
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");// use passed state if is necessary...
    }

    //obtiene la lista restaurantes y las muestra
    @Override
    public void fetchRestaurantsListData() {
        Log.e(TAG, "fetchRestaurantsListData()");
        // call the model
        // pide al modelo de forma asincrona la lista y que cuando los tenga le notifique
        // (callback) del Repository Contract
        model.fetchRestaurantsListData(new RepositoryContract.GetRestaurantsListCallback() {

            @Override
            public void setRestaurantsList(List<RestaurantItem> restaurants) {
                // rellena la lista con las categprias almacenadas
                state.restaurants = restaurants;
                // update view
                view.get().displayRestaurantsListData(state);
            }
        });
    }

    //cuando se selecciona una categoria, para pasar a su detalle
    @Override
    public void selectRestaurantListData(RestaurantItem item) {
        //envia la info al mediador
        passDataToProductListScreen(item);
        //cambia de activity
        view.get().navigateToRestaurantCarta();
    }

    //almacena en ek mediador la info a pasar
    private void passDataToProductListScreen(RestaurantItem item) {
        mediator.setRestaurant(item);
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
    public void injectView(WeakReference<RestaurantsListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(RestaurantsListContract.Model model) {
        this.model = model;
    }

}
