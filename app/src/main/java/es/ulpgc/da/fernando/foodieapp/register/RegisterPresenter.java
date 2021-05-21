package es.ulpgc.da.fernando.foodieapp.register;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;

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

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");
        // update the model if is necessary
        // model.onRestartScreen(state.data);
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
