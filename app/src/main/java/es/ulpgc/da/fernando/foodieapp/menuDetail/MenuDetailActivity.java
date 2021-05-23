package es.ulpgc.da.fernando.foodieapp.menuDetail;

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

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;

public class MenuDetailActivity
        extends AppCompatActivity implements MenuDetailContract.View {

    public static String TAG = MenuDetailActivity.class.getSimpleName();

    private MenuDetailContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

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

}