package es.ulpgc.da.fernando.foodieapp.menuDetail;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;

public class MenuDetailScreen {

    public static void configure(MenuDetailContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        FoodieMediator mediator = FoodieMediator.getInstance();
        MenuDetailContract.Presenter presenter = new MenuDetailPresenter(mediator);

        //no tiene repositorio, obtiene datos de previas activities
        MenuDetailContract.Model model = new MenuDetailModel();

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}