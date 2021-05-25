package es.ulpgc.da.fernando.foodieapp.myMenus;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;
import es.ulpgc.da.fernando.foodieapp.data.CatalogRepository;
import es.ulpgc.da.fernando.foodieapp.data.RepositoryContract;

public class MyMenusScreen {

    public static void configure(MyMenusContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);


        FoodieMediator mediator = FoodieMediator.getInstance();
        MyMenusContract.Presenter presenter = new MyMenusPresenter(mediator);

        RepositoryContract repository = CatalogRepository.getInstance(context.get());
        MyMenusContract.Model model = new MyMenusModel(repository);

        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);
    }
}