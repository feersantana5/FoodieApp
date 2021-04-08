package es.ulpgc.da.fernando.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RestaurantProfileActivity extends AppCompatActivity {

    Button myMenu;
    FloatingActionButton fabEditAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);


        myMenu = findViewById(R.id.mismenus);
        fabEditAccount = findViewById(R.id.fab_editProfile);

        myMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToMyMenu();
            }
        });

        fabEditAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RestaurantProfileActivity.this, EditAccountActivity.class);
                startActivity(intent);
            }
        });
    }

    private void goToMyMenu() {
        Intent intent = new Intent(this, MyMenusActivity.class);
        startActivity(intent);
    }

}