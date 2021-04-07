package es.ulpgc.da.fernando.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button buttonGoToRestaurants, buttonIamRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonGoToRestaurants = findViewById(R.id.botonVerRestaurantes);
        buttonIamRestaurant = findViewById(R.id.botonSoyRestaurante);

        buttonGoToRestaurants.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToRestaurantsListActivity();
            }
        });
        buttonIamRestaurant.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToLoginActivity();
            }
        });
    }

    private void goToRestaurantsListActivity() {
        Intent intent = new Intent(this, RestaurantsListActivity.class);
        startActivity(intent);
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}