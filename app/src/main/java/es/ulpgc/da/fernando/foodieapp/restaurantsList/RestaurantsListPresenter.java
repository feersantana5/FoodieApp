package es.ulpgc.da.fernando.foodieapp.restaurantsList;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;

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
         Log.e(TAG, "onStart()");

        // initialize the state if is necessary
        if (state == null) {
            state = new RestaurantsListState();
        }

        // call the model and update the state
        state.data = model.getStoredData();

        // use passed state if is necessary
        PreviousToRestaurantsListState savedState = getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromPreviousScreen(savedState.data);

            // update the state if is necessary
            state.data = savedState.data;
        }
    }

    @Override
    public void onRestart() {
         Log.e(TAG, "onRestart()");
        // update the model if is necessary
        model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
         Log.e(TAG, "onResume()");
        // use passed state if is necessary
        NextToRestaurantsListState savedState = getStateFromNextScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromNextScreen(savedState.data);

            // update the state if is necessary
            state.data = savedState.data;
        }

        // call the model and update the state
        //state.data = model.getStoredData();

        // update the view
        view.get().onDataUpdated(state);

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

    private NextToRestaurantsListState getStateFromNextScreen() {
        return mediator.getNextRestaurantsListScreenState();
    }

    private void passStateToNextScreen(RestaurantsListToNextState state) {
        mediator.setNextRestaurantsListScreenState(state);
    }

    private void passStateToPreviousScreen(RestaurantsListToPreviousState state) {
        mediator.setPreviousRestaurantsListScreenState(state);
    }

    private PreviousToRestaurantsListState getStateFromPreviousScreen() {
        return mediator.getPreviousRestaurantsListScreenState();
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
