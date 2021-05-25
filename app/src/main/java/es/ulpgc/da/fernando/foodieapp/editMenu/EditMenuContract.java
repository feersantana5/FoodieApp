package es.ulpgc.da.fernando.foodieapp.editMenu;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public interface EditMenuContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayData(EditMenuViewModel viewModel);

        void showToast(EditMenuViewModel viewModel);

        void showToastThread(EditMenuViewModel viewModel);

        void navigateToMyMenus();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void editMenu(String nombreMenu, int precioMenu, String magenMenu, String entranteMenu, String primeroMenu, String segundoMenu, String postreMenu, String bebidaMenu);

        void onBackPressed();

        void onPause();

        void onDestroy();
    }

    interface Model {
        String getEmptyAdvice();

        void editMenu(int idMenu, String nombre, int precio, String imagen, String entrante, String primero, String segundo, String postre, String bebida, RepositoryContract.EditMenuCallback editMenuCallback);

        String getUpdatedAdvice();
    }

}