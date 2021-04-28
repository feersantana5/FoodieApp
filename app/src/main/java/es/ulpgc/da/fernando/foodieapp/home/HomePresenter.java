package es.ulpgc.da.fernando.foodieapp.home;

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
        // Log.e(TAG, "onStart()");

        // initialize the state if is necessary
        if (state == null) {
            state = new HomeState();
        }

        // call the model and update the state
        state.data = model.getStoredData();

        // use passed state if is necessary
        PreviousToHomeState savedState = getStateFromPreviousScreen();
        if (savedState != null) {

            // update the model if is necessary
            model.onDataFromPreviousScreen(savedState.data);

            // update the state if is necessary
            state.data = savedState.data;
        }
    }

    @Override
    public void onRestart() {
        // Log.e(TAG, "onRestart()");
        if (state == null) {
            state = new HomeState();
        }
            PreviousToHomeState savedState = getStateFromPreviousScreen();
        // update the model if is necessary
        model.onRestartScreen(state.data);
    }

    @Override
    public void onResume() {
        // Log.e(TAG, "onResume()");

        // use passed state if is necessary
        NextToHomeState savedState = getStateFromNextScreen();
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
        // Log.e(TAG, "onBackPressed()");
    }

    @Override
    public void onPause() {
        // Log.e(TAG, "onPause()");
    }

    @Override
    public void onDestroy() {
        // Log.e(TAG, "onDestroy()");
    }

    private NextToHomeState getStateFromNextScreen() {
        return mediator.getNextHomeScreenState();
    }

    private void passStateToNextScreen(HomeToNextState state) {
        mediator.setNextHomeScreenState(state);
    }

    private void passStateToPreviousScreen(HomeToPreviousState state) {
        mediator.setPreviousHomeScreenState(state);
    }

    private PreviousToHomeState getStateFromPreviousScreen() {
        return mediator.getPreviousHomeScreenState();
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

