package es.ulpgc.da.fernando.foodieapp.home;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.CatalogRepository;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class HomeScreen {

    public static void configure(HomeContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        FoodieMediator mediator = FoodieMediator.getInstance();
        HomeContract.Presenter presenter = new HomePresenter(mediator);

        RepositoryContract repository = CatalogRepository.getInstance(context.get());
        HomeContract.Model model = new HomeModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}
