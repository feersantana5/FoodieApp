package es.ulpgc.da.fernando.foodieapp.home;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public interface HomeContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void showToast(String toast);

        void navigateToRestaurantList();

        void navigateToLogin();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void fetchJSON();


        void onBackPressed();

        void onPause();

        void onDestroy();

        void goToLogin();

        void goToRestaurantList();
    }

    interface Model {

        void fetchJSON(final RepositoryContract.FetchJSONCallback callback);

        String getJSONWarning();
    }

}
