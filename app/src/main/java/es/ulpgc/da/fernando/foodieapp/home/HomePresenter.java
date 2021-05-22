package es.ulpgc.da.fernando.foodieapp.home;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

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
    public void fetchJSON() {
        Log.e(TAG, "fetchJSON()");
        // call the model
        // pide al modelo de forma asincrona la lista y que cuando los tenga le notifique
        // (callback) del Repository Contract
        model.fetchJSON(new RepositoryContract.FetchJSONCallback() {

            @Override
            public void onJSONFetched(boolean error) {
                if (error) {
                    view.get().showToast("NO CONNECTION. DATA MAY BE OBSOLETE");
                } else {
                    Log.d(TAG, "catalogo descargado ok");
                }
            }
        });
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

