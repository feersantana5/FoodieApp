package es.ulpgc.da.fernando.foodieapp.createMenu;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.da.fernando.foodieapp.R;

public class CreateMenuActivity
        extends AppCompatActivity implements CreateMenuContract.View {

    public static String TAG = CreateMenuActivity.class.getSimpleName();

    private CreateMenuContract.Presenter presenter;

    private Button btnCreateMenu;
    private EditText nombre, precio, imagen, entrante, primero, segundo, bebida, postre;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_createmenu);

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

        initLayout();
        enableLayoutButtons();

        // do the setup
        CreateMenuScreen.configure(this);

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
        btnCreateMenu = findViewById(R.id.btnAddMenu);

        nombre = findViewById(R.id.menuNameCreateMenu);
        precio = findViewById(R.id.priceCreateMenu);
        imagen = findViewById(R.id.imagenCreateMenu);
        entrante = findViewById(R.id.starterCreateMenu);
        primero = findViewById(R.id.firstCourseCreateMenu);
        segundo = findViewById(R.id.secondCourseCreateMenu);
        bebida = findViewById(R.id.beverageCreateMenu);
        postre = findViewById(R.id.dessertCreateMenu);
    }

    private void enableLayoutButtons() {
        btnCreateMenu.setOnClickListener(v -> {
            String nombreMenu = nombre.getText().toString().trim();
            int precioMenu = Integer.parseInt(precio.getText().toString().trim());
            String imagenMenu = imagen.getText().toString().trim();
            String entranteMenu = entrante.getText().toString().trim();
            String primeroMenu = primero.getText().toString().trim();
            String segundoMenu = segundo.getText().toString().trim();
            String postreMenu = postre.getText().toString().trim();
            String bebidaMenu = bebida.getText().toString().trim();
            presenter.createMenu(nombreMenu, precioMenu, imagenMenu, entranteMenu, primeroMenu, segundoMenu, postreMenu, bebidaMenu);
        });
    }

    public void showToast(CreateMenuViewModel viewModel) {
        Log.e(TAG, "showToast()");
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, viewModel.toast, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void showToastThread(CreateMenuViewModel viewModel) {
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
    public void navigateToMyMenus() {
        Log.e(TAG, "navigateToMyMenus()");
        finish();
    }

    @Override
    public void injectPresenter(CreateMenuContract.Presenter presenter) {
        this.presenter = presenter;
    }
}