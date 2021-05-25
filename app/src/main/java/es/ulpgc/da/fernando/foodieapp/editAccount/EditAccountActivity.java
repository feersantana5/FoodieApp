package es.ulpgc.da.fernando.foodieapp.editAccount;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.da.fernando.foodieapp.R;

public class EditAccountActivity
        extends AppCompatActivity implements EditAccountContract.View {

    public static String TAG = EditAccountActivity.class.getSimpleName();

    private EditAccountContract.Presenter presenter;

    private Button btnEditAccount;
    private EditText email, password, ubicacion, webpage, descripcion, nombre, logoURL;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_editaccount
        );

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */
        initLayout();
        enableLayoutButtons();

        // do the setup
        EditAccountScreen.configure(this);

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

    private void initLayout() {
        btnEditAccount = findViewById(R.id.btnedit);

        email = findViewById(R.id.emailEditAccount);
        password = findViewById(R.id.passwordEditAccount);
        ubicacion = findViewById(R.id.locationEditAccount);
        webpage = findViewById(R.id.webpageEditAccount);
        descripcion = findViewById(R.id.descripcionEditAccount);
        nombre = findViewById(R.id.restaurantNameEditAccount);
        logoURL = findViewById(R.id.logoEditAccount);
    }

    private void enableLayoutButtons() {
        btnEditAccount.setOnClickListener(v -> {
            String emailUser = email.getText().toString().trim();
            String passwordUser = password.getText().toString().trim();
            String ubicacionUser = ubicacion.getText().toString().trim();
            String webpageUser = webpage.getText().toString().trim();
            String descripcionUser = descripcion.getText().toString().trim();
            String nombreUser = nombre.getText().toString().trim();
            String logoUser = logoURL.getText().toString().trim();
            presenter.editAccount(emailUser, passwordUser, ubicacionUser, webpageUser, descripcionUser, nombreUser, logoUser);
        });
    }

    @Override
    public void displayData(EditAccountViewModel viewModel) {
        email.setText(viewModel.user.email);
        password.setText(viewModel.user.password);
        ubicacion.setText(viewModel.restaurant.location);
        webpage.setText(viewModel.restaurant.webpage);
        descripcion.setText(viewModel.restaurant.description);
        nombre.setText(viewModel.restaurant.title);
        logoURL.setText(viewModel.restaurant.logo);
    }

    @Override
    public void showToast(EditAccountViewModel viewModel) {
        Log.e(TAG, "showToast()");
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, viewModel.toast, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showToastThread(EditAccountViewModel viewModel) {
        Log.e(TAG, "showToastThread()");
        runOnUiThread(() -> {
            //Do something on UiThread
            Toast.makeText(getApplicationContext(), viewModel.toast, Toast.LENGTH_SHORT).show();
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
    public void navigateToRestaurantProfile() {
        Log.e(TAG, "navigateToRestaurantProfile()");
        finish();
    }

    @Override
    public void injectPresenter(EditAccountContract.Presenter presenter) {
        this.presenter = presenter;
    }

}