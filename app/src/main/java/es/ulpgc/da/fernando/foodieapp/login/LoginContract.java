package es.ulpgc.da.fernando.foodieapp.login;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void navigateToRestaurantProfile();

        void navigateToRegister();

        void setErrorLayoutInputs(int i);

        void displayData(LoginViewModel viewModel);
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

        void goToRegister();

        void goToRestaurantProfile();

        void checkLogin(String correo, String passw);

        void signIn(String correo, String passw);
    }

    interface Model {

        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

}
