package es.ulpgc.da.fernando.foodieapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.RegisterActivity;
import es.ulpgc.da.fernando.foodieapp.RestaurantProfileActivity;

public class LoginActivity
        extends AppCompatActivity implements LoginContract.View {

    public static String TAG = LoginActivity.class.getSimpleName();

    private LoginContract.Presenter presenter;

    ImageView loginImage;
    EditText email, password;
    TextInputLayout emailTextInputLayout, passwTextInputLayout;
    Button btnLogin, btnRegister;

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
        setUpLoginLayout();


        // do the setup
        LoginScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();
        } else {
            presenter.onRestart();
        }
    }


    private void initLayout() {
        loginImage = findViewById(R.id.loginImage);
        email = findViewById(R.id.emailSignUpText);
        password = findViewById(R.id.passwordSignUpText);
        emailTextInputLayout = findViewById(R.id.emailSignUpTextInputLayout);
        passwTextInputLayout = findViewById(R.id.passwordSignUpTextInputLayout);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
    }

    //Fijamos texto y botones del layout
    private void setUpLoginLayout() {
        btnLogin.setText(R.string.iniciar_sesion);
        btnRegister.setText(R.string.no_tengo_cuenta);
    }

    private void enableLayoutButtons() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToRestaurantProfile();
                //TODO: verificar registro e ir al perfil
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToRegister();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        presenter.onResume();
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
    public void injectPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void onLoginClicked(View view) {
        Log.e(TAG, "inicia bien");
        String correo = email.getText().toString();
        String passw = password.getText().toString();
        presenter.checkLogin(correo, passw);

    }

    //Errores a razón de los campos que estén vacíos
    @Override
    public void setErrorLayoutInputs(int i) {
        switch (i) {
            case 0:
                emailTextInputLayout.setError(getResources().getString(R.string.emailSignUpError));
                break;
            case 1:
                passwTextInputLayout.setError(getResources().getString(R.string.passwSignUpError));
                break;
            case 2:
                emailTextInputLayout.setError(getResources().getString(R.string.emailSignUpError));
                passwTextInputLayout.setError(getResources().getString(R.string.passwSignUpError));
                break;
            default:
                break;


        }

    }

    @Override
    public void displayData(LoginViewModel viewModel) {
        Toast.makeText(getApplicationContext(), viewModel.message,Toast.LENGTH_LONG).show();
    }
}