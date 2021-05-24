package es.ulpgc.da.fernando.foodieapp.editMenu;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.CatalogRepository;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class EditMenuScreen {

    public static void configure(EditMenuContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        FoodieMediator mediator = FoodieMediator.getInstance();
        EditMenuContract.Presenter presenter = new EditMenuPresenter(mediator);

        RepositoryContract repository = CatalogRepository.getInstance(context.get());
        EditMenuContract.Model model = new EditMenuModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}