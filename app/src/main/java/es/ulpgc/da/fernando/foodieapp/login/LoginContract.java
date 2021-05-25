package es.ulpgc.da.fernando.foodieapp.login;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public interface LoginContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void showToast(LoginViewModel state);

        void showToastThread(LoginViewModel state);

        void navigateToRestaurantProfile();

        void navigateToRegister();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void checkLogin(String email, String password);

        void goToRegister();

        void goToRestaurantProfile();

        void onBackPressed();

        void onPause();

        void onDestroy();

    }

    interface Model {
        String getEmptyAdvice();

        void logIn(String email, String password, RepositoryContract.LogInCallback logInCallback);

        String getErrorAdvice();
    }

}
