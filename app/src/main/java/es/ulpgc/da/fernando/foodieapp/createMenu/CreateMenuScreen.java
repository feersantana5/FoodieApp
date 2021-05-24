package es.ulpgc.da.fernando.foodieapp.createMenu;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.CatalogRepository;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class CreateMenuScreen {

    public static void configure(CreateMenuContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);


        FoodieMediator mediator = FoodieMediator.getInstance();
        CreateMenuContract.Presenter presenter = new CreateMenuPresenter(mediator);

        RepositoryContract repository = CatalogRepository.getInstance(context.get());
        CreateMenuContract.Model model = new CreateMenuModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}