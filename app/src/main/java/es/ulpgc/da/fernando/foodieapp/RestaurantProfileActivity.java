package es.ulpgc.da.fernando.foodieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RestaurantProfileActivity extends AppCompatActivity {

     Button myMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);


        myMenu = findViewById(R.id.mismenus);

        myMenu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                goToMyMenu();
            }
        });
    }

    private void goToMyMenu() {
        Intent intent = new Intent(this, MyMenusActivity.class);
        startActivity(intent);
    }

}