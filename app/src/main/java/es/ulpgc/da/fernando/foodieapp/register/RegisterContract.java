package es.ulpgc.da.fernando.foodieapp.register;

import java.lang.ref.WeakReference;

public interface RegisterContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void onDataUpdated(RegisterViewModel viewModel);

        void navigateToNextScreen();

        void showToast(RegisterViewModel state);
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

        void createRestaurant(String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo);
    }

    interface Model {
        String getEmptyAdvice();

        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

}