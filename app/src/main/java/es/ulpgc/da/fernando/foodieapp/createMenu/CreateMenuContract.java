package es.ulpgc.da.fernando.foodieapp.createMenu;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public interface CreateMenuContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void showToast(CreateMenuViewModel state);

        void showToastThread(CreateMenuViewModel state);

        void navigateToMyMenus();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void createMenu(String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida);

        void onBackPressed();

        void onPause();

        void onDestroy();
    }

    interface Model {
        String getEmptyAdvice();

        void createMenu(RestaurantItem restaurant, String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida, RepositoryContract.CreateMenuCallback callback);

        String getCreatedAdvice();

        String getErrorAdvice();
    }

}