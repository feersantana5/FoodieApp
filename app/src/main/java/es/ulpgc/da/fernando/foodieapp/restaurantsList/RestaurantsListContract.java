package es.ulpgc.da.fernando.foodieapp.restaurantsList;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public interface RestaurantsListContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayRestaurantsListData(RestaurantsListViewModel viewModel);

        void navigateToRestaurantCarta();

        void navigateToHomeNav();

        void navigateToProfileNav();

        void navigateToMenuNav();

        void showAlertDialogNav();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void fetchRestaurantsListData();

        void selectRestaurantListData(RestaurantItem item);

        void goToHomeNav();

        void goToProfileNav();

        void goToMyMenusListNav();

        void closeSession();

        void onBackPressed();

        void onPause();

        void onDestroy();


    }

    interface Model {
        //obtiene la lista de categorias
        void fetchRestaurantsListData(RepositoryContract.GetRestaurantsListCallback callback);
    }

}