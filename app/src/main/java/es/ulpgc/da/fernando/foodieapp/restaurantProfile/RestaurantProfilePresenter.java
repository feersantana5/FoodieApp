package es.ulpgc.da.fernando.foodieapp.restaurantProfile;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.data.UserItem;

public class RestaurantProfilePresenter implements RestaurantProfileContract.Presenter {

    public static String TAG = RestaurantProfilePresenter.class.getSimpleName();

    private WeakReference<RestaurantProfileContract.View> view;
    private RestaurantProfileState state;
    private RestaurantProfileContract.Model model;
    private FoodieMediator mediator;

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

  /*  @Override
    public void fetchUserData() {
        Log.e(TAG, "fetchMenuListData()");
        model.getUserProfileData(new RepositoryContract.OnGetUserDataCallback() {
            @Override
            public void setUserData(boolean error, RestaurantItem restaurant, UserItem user) {
                if (!error) {
                    state.restaurant = restaurant;
                    state.user = user;
                    view.get().displayData(state);
                } else {
                    //view.get().showToast("Error retrieving data");
                }
            }
        });
        // set passed state
        //obtiene el restaurante al que pertenece, almacenada en el mediador
        RestaurantItem restaurant = getUserRestaurantData();
        UserItem user = getUserData();
        if (restaurant != null && user != null) {
            //modify state
            state.restaurant = restaurant;
            state.user = user;
            // update view
            view.get().displayData(state);
        }
    }*/

    public void goToMyMenus() {
        Log.e(TAG, "goToMyMenus()");
        view.get().navigateToMyMenus();
    }

    public void goToEditAccount() {
        Log.e(TAG, "goToEditAccount()");
        view.get().navigateToEditAccount();
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
