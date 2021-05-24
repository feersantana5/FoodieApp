package es.ulpgc.da.fernando.foodieapp.editAccount;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.CatalogRepository;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class EditAccountScreen {

    public static void configure(EditAccountContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        FoodieMediator mediator = FoodieMediator.getInstance();
        EditAccountContract.Presenter presenter = new EditAccountPresenter(mediator);

        RepositoryContract repository = CatalogRepository.getInstance(context.get());
        EditAccountContract.Model model = new EditAccountModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}