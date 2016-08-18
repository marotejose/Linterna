package com.example.marote.marotorch;

import android.app.Activity;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.Menu;
import android.widget.ToggleButton;

import java.security.Policy;

public class MainActivity extends Activity {

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

        ToggleButton button = (ToggleButton) findViewById(R.id.btn_on_off);
        button.setOnClickListener(this);



    }

    @Override
    public void onClick(View view){

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();

        camera.stopPreview();
        camera.release();
    }


}
