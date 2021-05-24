package es.ulpgc.da.fernando.foodieapp.myMenus;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;
import es.ulpgc.da.fernando.foodieapp.data.RestaurantItem;

public interface MyMenusContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayMyMenusListData(final MyMenusViewModel viewModel);

        void showToastThread(MyMenusViewModel viewModel);

        void onDataUpdated(MyMenusViewModel viewModel);

        void navigateToEditMenu();

        void navigateToCreateMenu();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void fetchMyMenusListData();

        void goToCreateMenu();

        void deleteMenu(MenuItem menuItem);

        void goToEditMenu(MenuItem menuItem);

        void onBackPressed();

        void onPause();

        void onDestroy();
    }

    interface Model {

        void fetchMyMenusListData(RestaurantItem restaurant, RepositoryContract.GetMyMenusListCallback callback);

        void deleteMenu(MenuItem item, RepositoryContract.DeleteMenuCallback deleteMenuCallback);

        String getDeletedAdvice();

//        void editMenu(MenuItem item, RepositoryContract.EditMenuCallback editMenuCallback);

        String getStoredData();

        void onDataFromNextScreen(String data);

        void onRestartScreen(String data);

        void onDataFromPreviousScreen(String data);
    }

}