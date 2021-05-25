package es.ulpgc.da.fernando.foodieapp.myMenus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import es.ulpgc.da.fernando.foodieapp.R;
import es.ulpgc.da.fernando.foodieapp.createMenu.CreateMenuActivity;
import es.ulpgc.da.fernando.foodieapp.data.MenuItem;
import es.ulpgc.da.fernando.foodieapp.editMenu.EditMenuActivity;

public class MyMenusActivity
        extends AppCompatActivity implements MyMenusContract.View {

    public static String TAG = MyMenusActivity.class.getSimpleName();

    private MyMenusContract.Presenter presenter;
    private MyMenusListAdapter listAdapter;


    FloatingActionButton fabAddMenu;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_menus);

    /*
    if(savedInstanceState == null) {
      AppMediator.resetInstance();
    }
    */

        initAdapter();
        initLayout();
        enableLayoutButtons();

        // do the setup
        MyMenusScreen.configure(this);

        if (savedInstanceState == null) {
            presenter.onStart();
        } else {
            presenter.onRestart();
        }

        // do some work
        presenter.fetchMyMenusListData();
    }

    public void initAdapter() {
        //onclick
        listAdapter = new MyMenusListAdapter(view -> {
            MenuItem item = (MenuItem) view.getTag();
            Log.d(TAG, "listAdapterOnClick: " + item);

            if (view.getId() == R.id.menuEdit) {
                //listener de la lista cuando se pulsa obtiene el tag y lo pasa
                presenter.goToEditMenu(item);
            }
            if (view.getId() == R.id.menuDelete) {
                presenter.deleteMenu(item);
            }
        });
    }

    public void initLayout() {
        fabAddMenu = findViewById(R.id.fab_addMenu);

        recyclerView = findViewById(R.id.myMenusRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(listAdapter);
    }

    public void enableLayoutButtons() {
        fabAddMenu.setOnClickListener(view -> presenter.goToCreateMenu());
    }

    @Override
    protected void onResume() {
        super.onResume();
        // load the data
        presenter.onResume();
    }

    @Override
    public void displayMyMenusListData(final MyMenusViewModel viewModel) {
        Log.e(TAG, "displayMyMenusListData()");
        runOnUiThread(() -> {
            // deal with the data
            //obtiene el restaurante del viewmodel
            listAdapter.setItems(viewModel.menus);  //rellena la lista con los menus
        });
    }

    @Override
    public void showToastThread(MyMenusViewModel viewModel) {
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
    public void onDataUpdated(MyMenusViewModel viewModel) {
        Log.e(TAG, "onDataUpdated()");
        // deal with the data
        //((TextView) findViewById(R.id.data)).setText(viewModel.data);
    }


    @Override
    public void navigateToEditMenu() {
        Intent intent = new Intent(this, EditMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToCreateMenu() {
        Intent intent = new Intent(this, CreateMenuActivity.class);
        startActivity(intent);
    }

    @Override
    public void injectPresenter(MyMenusContract.Presenter presenter) {
        this.presenter = presenter;
    }
}