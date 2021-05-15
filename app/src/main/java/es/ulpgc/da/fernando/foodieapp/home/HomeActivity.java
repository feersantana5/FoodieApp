package es.ulpgc.da.fernando.foodieapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.RestaurantsListActivity;
import es.ulpgc.da.fernando.foodieapp.login.LoginActivity;

public class HomeActivity
        extends AppCompatActivity implements HomeContract.View {

    public static String TAG = HomeActivity.class.getSimpleName();

    private HomeContract.Presenter presenter;

    private ImageView logoImagen, homeImagen;
    private Button btnUser, btnRestaurante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

        initLayout();
        enableLayoutButtons();

        // do the setup
        HomeScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();
        } else {
            presenter.onRestart();
        }
    }

    private void initLayout() {
        logoImagen = findViewById(R.id.logoApp);
        homeImagen = findViewById(R.id.imagenInicio);
        btnUser = findViewById(R.id.botonVerRestaurantes);
        btnRestaurante = findViewById(R.id.botonSoyRestaurante);
    }

    private void enableLayoutButtons() {
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToRestaurantList();
            }
        });

        btnRestaurante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.goToLogin();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        //TODO: REPOSITORY?
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
    public void navigateToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToRestaurantList() {
        Intent intent = new Intent(this, RestaurantsListActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

}