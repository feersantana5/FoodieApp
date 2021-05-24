package es.ulpgc.da.fernando.foodieapp.editMenu;

import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;
import es.ulpgc.da.fernando.foodieapp.data.UserItem;
import es.ulpgc.da.fernando.foodieapp.editAccount.EditAccountState;

public class EditMenuPresenter implements EditMenuContract.Presenter {

    public static String TAG = EditMenuPresenter.class.getSimpleName();

    private WeakReference<EditMenuContract.View> view;
    private EditMenuState state;
    private EditMenuContract.Model model;
    private FoodieMediator mediator;

    public EditMenuPresenter(FoodieMediator mediator) {
        this.mediator = mediator;
        state = mediator.getEditMenuState();
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");
        // initialize the state if is necessary
        if (state == null) {
            state = new EditMenuState();
        }
    }

    @Override
    public void onRestart() {
        Log.e(TAG, "onRestart()");
    }

    @Override
    public void onResume() {
        Log.e(TAG, "onResume()");
        MenuItem menu = getMenuData();
        if (menu != null) {
            //modify state
            state.menu = menu;
            // update view
            view.get().displayData(state);
        }
    }

    //obtiene el restaurante almacenado en el mediador
    private MenuItem getMenuData() {
        MenuItem menu = mediator.getMenu();
        Log.e(TAG, menu.name);
        return menu;
    }

    public void editMenu(String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida) {
        Log.e(TAG, "editMenu()");
        if (nombre.isEmpty() || imagen.isEmpty() || entrante.isEmpty() || primero.isEmpty() || segundo.isEmpty() || postre.isEmpty()|| bebida.isEmpty()) {
            state.toast = model.getEmptyAdvice();
            view.get().showToast(state);
        } else {
            model.editMenu(state.menu.id, nombre, precio, imagen, entrante, primero, segundo, postre, bebida, new RepositoryContract.EditMenuCallback() {
                @Override
                public void setMyMenuListEdited(boolean error, List<MenuItem>  listaActualizada) {
                    if (!error) {
                        // set state
                        state.toast = model.getUpdatedAdvice();
                        //state.restaurantItem = restauranteActualizado;
                        state.menus=listaActualizada;
                        //passRestaurantDataToOthers(restauranteActualizado);
                        passMenusDataToOthers(listaActualizada);
                        // update view
                        //volver pa atras
                        view.get().showToastThread(state);
                        goToMyMenus();
                    }
                }
            });
        }
    }
    //almacena en ek mediador la info a pasar
    private void passRestaurantDataToOthers(RestaurantItem item) {
        mediator.setRestaurant(item);
    }
    //almacena en ek mediador la info a pasar
    private void passMenusDataToOthers(List<MenuItem> items) {
        mediator.setMenuList(items);
    }

    public void goToMyMenus() {
        Log.e(TAG, "goToMyMenus()");
        //TODO: verificar registro e ir al perfil
        view.get().navigateToMyMenus();
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
    public void injectView(WeakReference<EditMenuContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(EditMenuContract.Model model) {
        this.model = model;
    }

}
