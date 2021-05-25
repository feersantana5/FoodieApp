package es.ulpgc.da.fernando.foodieapp.restaurantsList;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.CatalogRepository;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class RestaurantsListScreen {

    public static void configure(RestaurantsListContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        FoodieMediator mediator = FoodieMediator.getInstance();
        RestaurantsListContract.Presenter presenter = new RestaurantsListPresenter(mediator);

        RepositoryContract repository = CatalogRepository.getInstance(context.get());
        RestaurantsListContract.Model model = new RestaurantsListModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}