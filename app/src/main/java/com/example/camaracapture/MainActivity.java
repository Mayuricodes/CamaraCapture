package com.example.camaracapture;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    Button btnText;
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnText = findViewById(R.id.button);
        img = findViewById(R.id.imageView);


        EnableRuntimePermission();
        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //For CameraRequest you would most likely do
                Intent cameraintent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraintent, 7 );

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 7 && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");

            img.setImageBitmap(bitmap);

        }
    }

    private void EnableRuntimePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, android.Manifest.permission.CAMERA)) {
            Toast.makeText(MainActivity.this, "CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.CAMERA}, 1);
        }
    }


}



//ACTION_IMAGE_CAPTURE = Standard Intent action that can be sent to have the camera application capture an image and return it.

//startActivityForResult = requires a result from the second activity

//Request code = calling function identifier from where it is requested.
                 //helps you to identify from which Intent you came back.

//Result code = called function identifier  specifies  the result of processed operation

// Bitmap class = To store digital image in the form of pixel value