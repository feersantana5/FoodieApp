package es.ulpgc.da.fernando.foodieapp.restaurantCarta;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.CatalogRepository;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class RestaurantCartaScreen {

    public static void configure(RestaurantCartaContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        FoodieMediator mediator = FoodieMediator.getInstance();
        RestaurantCartaContract.Presenter presenter = new RestaurantCartaPresenter(mediator);

        RepositoryContract repository = CatalogRepository.getInstance(context.get());
        RestaurantCartaContract.Model model = new RestaurantCartaModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}