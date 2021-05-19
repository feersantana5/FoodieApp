package es.ulpgc.da.fernando.foodieapp.menuDetail;

import java.lang.ref.WeakReference;

public interface MenuDetailContract {

    interface View {
        void injectPresenter(Presenter presenter);

        void displayMenuDetailData(MenuDetailViewModel viewModel);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);

        void injectModel(Model model);

        void onResume();

        void onStart();

        void onRestart();

        void fetchMenuDetailData();

        void onBackPressed();

        void onPause();

        void onDestroy();
    }

    interface Model { }

}