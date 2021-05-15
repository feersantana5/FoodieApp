package es.ulpgc.da.fernando.foodieapp.login;

import androidx.fragment.app.FragmentActivity;

import java.lang.ref.WeakReference;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.app.FoodieMediator;

public class LoginScreen {

    public static void configure(LoginContract.View view) {

        WeakReference<FragmentActivity> context = new WeakReference<>((FragmentActivity) view);

        String data = context.get().getString(R.string.app_name);

        FoodieMediator mediator = FoodieMediator.getInstance();

        LoginContract.Presenter presenter = new LoginPresenter(mediator);
        LoginContract.Model model = new LoginModel(data);
        presenter.injectModel(model);
        presenter.injectView(new WeakReference<>(view));

        view.injectPresenter(presenter);

    }
}
