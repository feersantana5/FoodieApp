package es.ulpgc.da.fernando.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MenuDetailActivity extends AppCompatActivity {

    TextView textStartersMenu, txtDessertMenu, txtBeaveragesMenu, txtSecondCourseMenu, txtFirstCourseMenu, txtPriceMenu, txtTitleMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        txtTitleMenu = (TextView) findViewById(R.id.menuTitle);
        txtPriceMenu = (TextView) findViewById(R.id.menuPrice);
        textStartersMenu = (TextView) findViewById(R.id.menuStarters);
        txtBeaveragesMenu = (TextView) findViewById(R.id.menuBeaverages);
        txtFirstCourseMenu = (TextView) findViewById(R.id.menuFirstCourse);
        txtSecondCourseMenu = (TextView) findViewById(R.id.menuSecondCourse);
        txtDessertMenu = (TextView) findViewById(R.id.menuDessert);

//Todo: acabar de hacer los intents de cada elementos
        //Intent intent = getIntent();
        //String message = intent.getStringExtra(RestaurantCartaActivity.title);
        //TextView textView = findViewById(R.id.me);
        //textView.setText(message);
    }
}