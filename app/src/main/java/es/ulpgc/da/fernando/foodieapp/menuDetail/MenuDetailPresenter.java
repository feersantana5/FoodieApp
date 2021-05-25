package es.ulpgc.da.fernando.foodieapp.menuDetail;

import android.util.Log;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;

public class MenuDetailPresenter implements MenuDetailContract.Presenter {

    public static String TAG = MenuDetailPresenter.class.getSimpleName();

    private WeakReference<MenuDetailContract.View> view;
    private MenuDetailState state;
    private MenuDetailContract.Model model;
    private final FoodieMediator mediator;

    public MenuDetailPresenter(FoodieMediator mediator) {
        //obtiene los datos del screen del mediador
        this.mediator = mediator;
        state = mediator.getMenuDetailState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");
        // initialize the state if is necessary
        if (state == null) {
            state = new MenuDetailState();
        }
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");
    }

    //obtiene los datos del producto
    @Override
    public void fetchMenuDetailData() {
        Log.e(TAG, "fetchMenuDetailData()");
        // set passed state
        MenuItem menu = getDataFromRestaurantCartaScreen();
        if (menu != null) {
            state.menu = menu;
        }
        // update view
        view.get().displayMenuDetailData(state);
    }
    // obtiene los datos del producto almacenado en el mediador
    private MenuItem getDataFromRestaurantCartaScreen() {
        MenuItem menu = mediator.getMenu();
        return menu;
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
    public void injectView(WeakReference<MenuDetailContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(MenuDetailContract.Model model) {
        this.model = model;
    }
}
