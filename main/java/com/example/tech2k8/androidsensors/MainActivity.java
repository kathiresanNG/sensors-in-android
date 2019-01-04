package com.example.tech2k8.androidsensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements SensorEventListener,BottomNavigationView.OnNavigationItemSelectedListener{

    private TextView sensorValues;
    private Sensor proximityValue;
    MediaPlayer player;
    private BottomNavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorValues=findViewById(R.id.sensor_output);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setOnNavigationItemSelectedListener(MainActivity.this);
        handlerSensors();

    }
    private void handlerSensors()
    {
        SensorManager manager =(SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximityValue=manager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        manager.registerListener(this,proximityValue,100);
    }

    void loadMediaFiles()
    {
         player=MediaPlayer.create(this,R.raw.music);
//        try {
//            player.prepare();
            player.start();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(this, "unable to play", Toast.LENGTH_SHORT).show();
//        }


    }
    void stopPlayer()
    {
        if (player.isPlaying())
        {
            player.stop();
        }
    }
    @Override
    public void onSensorChanged(SensorEvent event) {

        String senValues="X:"+event.values[0]+"Y:"+event.values[1]+"Z: "+event.values[2]+"";
        sensorValues.setText(senValues);

//        if (event.values[0]>0)
//        {
//            loadMediaFiles();
//        }
//
//        if (event.values[0]==0)
//        {
//            stopPlayer();
//        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if (menuItem.getItemId()==R.id.nav_1)
        {
            Toast.makeText(this, "home screen", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
