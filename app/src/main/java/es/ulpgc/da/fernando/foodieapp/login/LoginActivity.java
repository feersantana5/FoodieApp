package es.ulpgc.da.fernando.foodieapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.register.RegisterActivity;
import es.ulpgc.da.fernando.foodieapp.restaurantProfile.RestaurantProfileActivity;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

    public static String TAG = LoginActivity.class.getSimpleName();

    private LoginContract.Presenter presenter;

    private EditText email, password;
    private Button btnLogin, btnRegister;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

        initLayout();
        enableLayoutButtons();

        // do the setup
        LoginScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();
        } else {
            presenter.onRestart();
        }

    }

    private void initLayout() {
        email = findViewById(R.id.emailLoginText);
        password = findViewById(R.id.passwordLoginText);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }

    private void enableLayoutButtons() {
        btnLogin.setOnClickListener(v -> {
            String emailUser = email.getText().toString().trim();
            String passwordUser = password.getText().toString().trim();
            presenter.checkLogin(emailUser, passwordUser);
            //TODO: pasar ssesion enable
        });

        btnRegister.setOnClickListener(v -> presenter.goToRegister());
    }

    @Override
    public void showToast(LoginViewModel viewModel) {
        Log.e(TAG, "showToast()");
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, viewModel.toast, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showToastThread(LoginViewModel viewModel) {
        Log.e(TAG, "showToastThread()");
        runOnUiThread(() -> {
            //Do something on UiThread
            Toast.makeText(getApplicationContext(), viewModel.toast, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        presenter.onResume();
    }

    @Override
    public void navigateToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToRestaurantProfile() {
        Intent intent = new Intent(this, RestaurantProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void injectPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

}