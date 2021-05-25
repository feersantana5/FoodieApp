package es.ulpgc.da.fernando.foodieapp.editMenu;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.da.fernando.foodieapp.R;

public class EditMenuActivity
        extends AppCompatActivity implements EditMenuContract.View {

    public static String TAG = EditMenuActivity.class.getSimpleName();

    private EditMenuContract.Presenter presenter;

    Button buttonEditMenu;
    EditText nombre, precio, imagen, entrante, primero, segundo, bebida, postre;
    private Toast toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_editmenu);

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

        initLayout();
        enableLayoutButtons();

        // do the setup
        EditMenuScreen.configure(this);

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
        buttonEditMenu = findViewById(R.id.btnEditMenu);

        nombre = findViewById(R.id.menuNameEditMenu);
        precio = findViewById(R.id.priceEditMenu);
        imagen = findViewById(R.id.imagenMenuEditMenu);
        entrante = findViewById(R.id.starterEditMenu);
        primero = findViewById(R.id.firstCourseEditMenu);
        segundo = findViewById(R.id.secondCourseEditMenu);
        postre = findViewById(R.id.dessertEditMenu);
        bebida = findViewById(R.id.beverageEditMenu);
    }

    public void enableLayoutButtons() {
        buttonEditMenu.setOnClickListener(view -> {
            String nombreMenu = nombre.getText().toString().trim();
            int precioMenu = Integer.parseInt(precio.getText().toString().trim());
            String imagenMenu = imagen.getText().toString().trim();
            String entranteMenu = entrante.getText().toString().trim();
            String primeroMenu = primero.getText().toString().trim();
            String segundoMenu = segundo.getText().toString().trim();
            String postreMenu = postre.getText().toString().trim();
            String bebidaMenu = bebida.getText().toString().trim();
            presenter.editMenu(nombreMenu, precioMenu, imagenMenu, entranteMenu, primeroMenu, segundoMenu, postreMenu, bebidaMenu);
        });
    }

    @Override
    public void displayData(EditMenuViewModel viewModel) {
        nombre.setText(viewModel.menu.name);
        precio.setText((String.valueOf(viewModel.menu.price)));
        imagen.setText(viewModel.menu.image);
        entrante.setText(viewModel.menu.starter);
        primero.setText(viewModel.menu.firstCourse);
        segundo.setText(viewModel.menu.secondCourse);
        postre.setText(viewModel.menu.image);
        bebida.setText(viewModel.menu.beverage);
    }

    @Override
    public void showToast(EditMenuViewModel viewModel) {
        Log.e(TAG, "showToast()");
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, viewModel.toast, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void showToastThread(EditMenuViewModel viewModel) {
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
    public void injectPresenter(EditMenuContract.Presenter presenter) {
        this.presenter = presenter;
    }
}