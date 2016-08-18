package com.example.marote.marotorch;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import java.security.Policy;

public class MainActivity extends AppCompatActivity {

    private Camera camera;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encender la linterna

        camera = Camera.open();
        Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Parameters.FLASH_MODE_TORCH);
        camera.setParameters(parameters);

        camera.startPreview();


    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();

        camera.stopPreview();
        camera.release();
    }


}
