package es.ulpgc.da.fernando.foodieapp.restaurantCarta;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public interface RestaurantCartaContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayMenuListData(RestaurantCartaViewModel viewModel);

        void navigateToMenuDetail();

        void navigateToHomeNav();

        void navigateToProfileNav();

        void showAlertDialogNav();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onStart();

        void onResume();

        void onRestart();

        void fetchMenuListData();

        void selectMenuListData(MenuItem item);

        void goToProfileNav();

        void goToHomeNav();

        void closeSession();

        void onBackPressed();

        void onPause();

        void onDestroy();
    }

    interface Model {
        void fetchMenuListData(RestaurantItem restaurant, RepositoryContract.GetMenuListCallback callback);
    }

}