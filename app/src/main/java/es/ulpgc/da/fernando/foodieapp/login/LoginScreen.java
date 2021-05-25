package es.ulpgc.da.fernando.foodieapp.login;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.CatalogRepository;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class LoginScreen {

    public static void configure(LoginContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        FoodieMediator mediator = FoodieMediator.getInstance();
        LoginContract.Presenter presenter = new LoginPresenter(mediator);

        RepositoryContract repository = CatalogRepository.getInstance(context.get());
        LoginContract.Model model = new LoginModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}
