package es.ulpgc.da.fernando.foodieapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateMenuActivity extends AppCompatActivity {

    Button buttonCreateMenu,btnAddImag;
    ImageView imagenNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_createmenu);

        imagenNew = (ImageView) findViewById(R.id.imageView);

        buttonCreateMenu = findViewById(R.id.btnAddMenu);
        buttonCreateMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnAddImag= findViewById(R.id.btnAddImagen);
        btnAddImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });

    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("imagen/");
        startActivityForResult(intent.createChooser(intent,"seleccione la Aplicacion"),10);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode ==RESULT_OK){
            Uri path = data.getData();
            imagenNew.setImageURI(path);
        }
    }


}