package es.ulpgc.da.fernando.foodieapp.home;

import java.lang.ref.WeakReference;

public interface HomeContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void navigateToRestaurantList();

        void navigateToLogin();
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

        void goToLogin();

        void goToRestaurantList();
    }

    interface Model {
        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

}