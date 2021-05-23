package es.ulpgc.da.fernando.foodieapp.restaurantProfile;

import java.lang.ref.WeakReference;

public interface RestaurantProfileContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(RestaurantProfileViewModel viewModel);

        void navigateToNextScreen();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

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