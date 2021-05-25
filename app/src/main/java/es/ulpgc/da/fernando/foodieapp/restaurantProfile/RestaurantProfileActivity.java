package es.ulpgc.da.fernando.foodieapp.restaurantProfile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.editAccount.EditAccountActivity;
import es.ulpgc.da.fernando.foodieapp.myMenus.MyMenusActivity;

public class RestaurantProfileActivity
        extends AppCompatActivity implements RestaurantProfileContract.View {

    public static String TAG = RestaurantProfileActivity.class.getSimpleName();

    private RestaurantProfileContract.Presenter presenter;

    Button btnMyMenus;
    FloatingActionButton fabEditAccount;
    TextView email, nombre, password, descripcion;
    ImageView logo;

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
        logo = findViewById(R.id.logoProfile);

        fabEditAccount = findViewById(R.id.fab_editProfile);
        btnMyMenus = findViewById(R.id.btnMyMenus);
    }

    public void enableLayoutButtons() {
        btnMyMenus.setOnClickListener(v -> presenter.goToMyMenus());
        fabEditAccount.setOnClickListener(v -> presenter.goToEditAccount());
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
        loadImageFromURL(logo, viewModel.restaurant.logo);
    }

    private void loadImageFromURL(ImageView imageView, String imageUrl) { //metodo para añadir la imagen usando Glide
        RequestManager reqManager = Glide.with(imageView.getContext());
        RequestBuilder reqBuilder = reqManager.load(imageUrl);
        RequestOptions reqOptions = new RequestOptions();
        reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        reqBuilder.apply(reqOptions);
        reqBuilder.into(imageView);
    }


    public void navigateToMyMenus() {
        Log.e(TAG, "navigateToMyMenus()");
        Intent intent = new Intent(this, MyMenusActivity.class);
        startActivity(intent);
    }

    public void navigateToEditAccount() {
        Log.e(TAG, "navigateToEditAccount()");
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
    public void injectPresenter(RestaurantProfileContract.Presenter presenter) {
        this.presenter = presenter;
    }
}