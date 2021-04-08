package es.ulpgc.da.fernando.foodieapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CreateMenuActivity extends AppCompatActivity {

    Button buttonCreateMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createmenu);

        buttonCreateMenu = findViewById(R.id.btnAddMenu);
        buttonCreateMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


}