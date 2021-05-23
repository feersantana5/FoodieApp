package es.ulpgc.da.fernando.foodieapp.restaurantProfile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ulpgc.da.fernando.foodieapp.EditAccountActivity;
import es.ulpgc.da.fernando.foodieapp.MyMenusActivity;
import es.ulpgc.da.fernando.foodieapp.R;

public class RestaurantProfileActivity
        extends AppCompatActivity implements RestaurantProfileContract.View {

    public static String TAG = RestaurantProfileActivity.class.getSimpleName();

    private RestaurantProfileContract.Presenter presenter;

    Button btnMyMenus;
    FloatingActionButton fabEditAccount;
    TextView email, nombre, password, descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

        initLayout();
        enableLayoutButtons();

        // do the setup
        RestaurantProfileScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();
        } else {
            presenter.onRestart();
        }

        // do some work
        //presenter.fetchUserData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        presenter.onResume();
    }

    public void initLayout() {
        email = findViewById(R.id.emailProfile);
        nombre = findViewById(R.id.nombreProfile);
        password = findViewById(R.id.passwordProfile);
        descripcion = findViewById(R.id.descriptionProfile);

        fabEditAccount = findViewById(R.id.fab_editProfile);
        btnMyMenus = findViewById(R.id.btnMyMenus);
    }

    public void enableLayoutButtons() {
        btnMyMenus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.goToMyMenus();
            }
        });

        fabEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToEditAccount();
            }
        });
    }

    //metodo que modifica el activity segun el estado
    @Override
    public void displayData(RestaurantProfileViewModel viewModel) {
        Log.e(TAG, "displayData()");
        // deal with the answer
        email.setText(viewModel.user.email);
        nombre.setText(viewModel.restaurant.title);
        password.setText(viewModel.user.password);
        descripcion.setText(viewModel.restaurant.description);
    }

    public void navigateToMyMenus() {
        Intent intent = new Intent(this, MyMenusActivity.class);
        startActivity(intent);
    }

    public void navigateToEditAccount() {
        Intent intent = new Intent(this, EditAccountActivity.class);
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
    public void onDataUpdated(RestaurantProfileViewModel viewModel) {
        Log.e(TAG, "onDataUpdated()");
        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void injectPresenter(RestaurantProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }
}