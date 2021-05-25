package es.ulpgc.da.fernando.foodieapp.editAccount;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public interface EditAccountContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void showToast(EditAccountViewModel state);

        void showToastThread(EditAccountViewModel viewModel);

        void displayData(EditAccountViewModel viewModel);

        void navigateToRestaurantProfile();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void editAccount(String emailUser, String passwordUser, String ubicacionUser, String webpageUser, String descripcionUser, String nombreUser, String logoUser);

        void goToRestaurantProfile();

        void onBackPressed();

        void onPause();

        void onDestroy();
    }

    interface Model {
        String getEmptyAdvice();

        void editUserAccount(int idRestaurant, String email, String password, String ubicacion, String webpage, String descripcion, String nombre, String logo, RepositoryContract.EditUserCallback callback);

        String getUpdatedAdvice();

        String getErrorAdvice();
    }

}