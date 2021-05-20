package es.ulpgc.da.fernando.foodieapp.menuDetail;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.RestaurantProfileActivity;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.home.HomeActivity;
import es.ulpgc.da.fernando.foodieapp.restaurantCarta.RestaurantCartaActivity;

public class MenuDetailActivity
        extends AppCompatActivity implements MenuDetailContract.View {

    public static String TAG = MenuDetailActivity.class.getSimpleName();

    private MenuDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        BottomNavigationView buttonNavBar;
        buttonNavBar = findViewById(R.id.bottomNavViewMyNav);
        //buttonNavBar.setVisibility(View.GONE);
        buttonNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(android.view.MenuItem item) {
                if (item.getItemId() == R.id.nav_menu_inicio) {
                    goToHome();
                }
                if (item.getItemId() == R.id.nav_menu_profile) {
                    goProfile();
                }
                if (item.getItemId() == R.id.nav_menu_menu) {
                    goMenu();
                }
                if (item.getItemId() == R.id.nav_menu_out) {
                    showAlertDialog();
                }
                return true;
            }
        });

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

        // do the setup
        MenuDetailScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();
        } else {
            presenter.onRestart();
        }

        // do some work
        presenter.fetchMenuDetailData();
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

    //actualiza la vista
    @Override
    public void displayMenuDetailData(MenuDetailViewModel viewModel) {
        Log.e(TAG, "displayMenuDetailData()");

        // deal with the data
        MenuItem menu = viewModel.menu;//obtiene el menu
        if (menu != null) {
            //textos
            ((TextView) findViewById(R.id.menuTitle)).setText(menu.name);
            ((TextView) findViewById(R.id.menuPrice)).setText(String.valueOf(menu.price).concat(" €"));
            ((TextView) findViewById(R.id.menuBeaverages)).setText(menu.beverage);
            ((TextView) findViewById(R.id.menuStarters)).setText(menu.starter);
            ((TextView) findViewById(R.id.menuFirstCourse)).setText(menu.firstCourse);
            ((TextView) findViewById(R.id.menuSecondCourse)).setText(menu.secondCourse);
            ((TextView) findViewById(R.id.menuDesserts)).setText(menu.dessert);
            //añade imagen
            loadImageFromURL((ImageView) findViewById(R.id.detalleimagen), menu.image);
        }
    }

    private void loadImageFromURL(ImageView imageView, String imageUrl) { //metodo para añadir la imagen usando Glide
        RequestManager reqManager = Glide.with(imageView.getContext());
        RequestBuilder reqBuilder = reqManager.load(imageUrl);
        RequestOptions reqOptions = new RequestOptions();
        reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        reqBuilder.apply(reqOptions);
        reqBuilder.into(imageView);
    }

    @Override
    public void injectPresenter(MenuDetailContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void goProfile() {
        Intent intent = new Intent(this, RestaurantProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void goMenu() {
        Intent intent = new Intent(this, RestaurantCartaActivity.class);
        startActivity(intent);
        finish();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MenuDetailActivity.this);
        builder.setTitle("Cerrar Sesión");
        builder.setMessage("¿Está seguro que desea cerrar su sesión?");

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO: pendiente modificar
                goToHome();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO: pendiente modificar
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}