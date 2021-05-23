package es.ulpgc.da.fernando.foodieapp.restaurantProfile;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.CatalogRepository;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class RestaurantProfileScreen {

    public static void configure(RestaurantProfileContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        FoodieMediator mediator = FoodieMediator.getInstance();
        RestaurantProfileContract.Presenter presenter = new RestaurantProfilePresenter(mediator);

        RepositoryContract repository = CatalogRepository.getInstance(context.get());
        RestaurantProfileContract.Model model = new RestaurantProfileModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}