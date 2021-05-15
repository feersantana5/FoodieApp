package es.ulpgc.da.fernando.foodieapp.home;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;

public class HomeScreen {

    public static void configure(HomeContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        FoodieMediator mediator = FoodieMediator.getInstance();
        HomeContract.Presenter presenter = new HomePresenter(mediator);

        HomeContract.Model model = new HomeModel();

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
