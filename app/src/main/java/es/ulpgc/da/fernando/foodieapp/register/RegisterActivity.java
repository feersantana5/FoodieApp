package es.ulpgc.da.fernando.foodieapp.register;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.RestaurantProfileActivity;

public class RegisterActivity
        extends AppCompatActivity implements RegisterContract.View {

    public static String TAG = RegisterActivity.class.getSimpleName();

    private RegisterContract.Presenter presenter;

    private EditText email, password, ubicacion, webpage, descripcion, nombre, logoURL;
    private Button crearBtn;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

        initLayout();
        enableLayoutButtons();

        // do the setup
        RegisterScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();
        } else {
            presenter.onRestart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        presenter.onResume();
    }


    public void initLayout() {
        email = findViewById(R.id.emailRegister);
        password = findViewById(R.id.passwordRegister);
        ubicacion = findViewById(R.id.locationRegister);
        webpage = findViewById(R.id.webpageRegister);
        descripcion = findViewById(R.id.descripcionRegister);
        nombre = findViewById(R.id.restaurantenameRegister);
        logoURL = findViewById(R.id.logoURLRegister);


        crearBtn = findViewById(R.id.btnCreateAccount);
    }

    public void enableLayoutButtons() {
        crearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailUser = email.getText().toString().trim();
                String passwordUser = password.getText().toString().trim();
                String ubicacionUser = ubicacion.getText().toString().trim();
                String webpageUser = webpage.getText().toString().trim();
                String descripcionUser = descripcion.getText().toString().trim();
                String nombreUser = nombre.getText().toString().trim();
                String logoUser = logoURL.getText().toString().trim();

                presenter.createRestaurant(emailUser, passwordUser, ubicacionUser, webpageUser, descripcionUser, nombreUser, logoUser);
            }
        });
    }

    public void showToast(RegisterViewModel viewModel) {
        Log.e(TAG, "showToast()");

        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, viewModel.toast, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToastThread(RegisterViewModel viewModel) {
        Log.e(TAG, "showToastThread()");
        runOnUiThread(new Runnable() {
            public void run() {
                //Do something on UiThread
                Toast.makeText(getApplicationContext(), viewModel.toast, Toast.LENGTH_SHORT).show();
            }
        });
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
    public void onDataUpdated(RegisterViewModel viewModel) {
        Log.e(TAG, "onDataUpdated()");
        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToRestaurantProfile() {
        Intent intent = new Intent(this, RestaurantProfileActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(RegisterContract.Presenter presenter) {
        this.presenter = presenter;
    }

}