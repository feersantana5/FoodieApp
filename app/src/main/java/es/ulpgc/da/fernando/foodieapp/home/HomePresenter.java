package es.ulpgc.da.fernando.foodieapp.home;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;

public class HomePresenter implements HomeContract.Presenter {

    public static String TAG = HomePresenter.class.getSimpleName();

    private WeakReference<HomeContract.View> view;
    private HomeState state;
    private HomeContract.Model model;
    private FoodieMediator mediator;

    public HomePresenter(FoodieMediator mediator) {
        this.mediator = mediator;
        state = mediator.getHomeState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");
        // initialize the state if is necessary
        if (state == null) {
            state = new HomeState();
        }
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");
        if (state == null) {
            state = new HomeState();
        }
        // update the model if is necessary
        model.onRestartScreen(state.data);
    }

    @Override
    public void goToRestaurantList() {
        view.get().navigateToRestaurantList();
    }

    @Override
    public void goToLogin() {
        view.get().navigateToLogin();
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
    public void injectView(WeakReference<HomeContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(HomeContract.Model model) {
        this.model = model;
    }


}

