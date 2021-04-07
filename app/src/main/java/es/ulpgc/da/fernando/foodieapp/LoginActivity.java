package es.ulpgc.da.fernando.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    Button buttonLogin, buttonGoToRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        buttonLogin = findViewById(R.id.btnLogin);
        buttonGoToRegister = findViewById(R.id.btnRegister);

        buttonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToRestaurantProfile();
            }
        });
        buttonGoToRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });
    }

    private void goToRestaurantProfile() {
        Intent intent = new Intent(this, RestaurantProfileActivity.class);
        startActivity(intent);
    }

    private void goToRegister() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}