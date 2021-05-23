package es.ulpgc.da.fernando.foodieapp.restaurantProfile;

import java.lang.ref.WeakReference;

public interface RestaurantProfileContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(RestaurantProfileViewModel viewModel);


        void onDataUpdated(RestaurantProfileViewModel viewModel);

        void navigateToMyMenus();

        void navigateToEditAccount();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void goToEditAccount();

        void goToMyMenus();


        //void fetchUserData();

        void onBackPressed();

        void onPause();

        void onDestroy();
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

}