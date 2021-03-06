package jp.ac.titech.itpro.sdl.gles10ex;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, SensorEventListener {
    private final static String TAG = "MainActivity";

    private GLSurfaceView glView;
    private SimpleRenderer renderer;
    private SeekBar rotationBarX, rotationBarY, rotationBarZ;
    private TextView accuracyView;
    private int flg = 0;

    private SensorManager sensorMgr;
    private Sensor gravitymeter;

    private float v;
    private long prevts;

    private final static float alpha = 0.75F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        glView = (GLSurfaceView) findViewById(R.id.glview);

        rotationBarX = (SeekBar) findViewById(R.id.rotation_bar_x);
        rotationBarY = (SeekBar) findViewById(R.id.rotation_bar_y);
        rotationBarZ = (SeekBar) findViewById(R.id.rotation_bar_z);
        rotationBarX.setOnSeekBarChangeListener(this);
        rotationBarY.setOnSeekBarChangeListener(this);
        rotationBarZ.setOnSeekBarChangeListener(this);

        renderer = new SimpleRenderer();
        renderer.addObj(new Cube   (0.5f, 0, 0.2f, -3));
        renderer.addObj(new Pyramid(0.5f, -1, 0, 0));
        renderer.addObj(new Add    (0.2f, 1, 1, 1));
        glView.setRenderer(renderer);


        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        /* init sensors */
        gravitymeter  = sensorMgr.getDefaultSensor(Sensor.TYPE_GRAVITY);

        Button btClick = (Button) findViewById(R.id.btClick);
        HelloListener listener = new HelloListener();
        btClick.setOnClickListener(listener);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        glView.onResume();
        sensorMgr.registerListener(this, gravitymeter, SensorManager.SENSOR_DELAY_FASTEST);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        glView.onPause();
        sensorMgr.unregisterListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (flg == 0) {
            if (seekBar == rotationBarX)
                renderer.setRotationX(progress);
            else if (seekBar == rotationBarY)
                renderer.setRotationY(progress);
            else if (seekBar == rotationBarZ)
                renderer.setRotationZ(progress);

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
    @Override
    public void onSensorChanged(SensorEvent event){
        switch(event.sensor.getType()){
            case Sensor.TYPE_GRAVITY:
                v = alpha * v + (1-alpha)* event.values[0];
                float ax = v * 5.5f;
                float ay = v * 7.7f;
                float az = v * 9.9f;
                if(flg == 1) {
                    renderer.setRotationX(ax);
                    renderer.setRotationY(ay);
                    renderer.setRotationZ(az);
                }
                break;

        }
        prevts = event.timestamp;
        return;
    }

    /**
     * ボタンをクリックしたときのリスナクラス。
     */
    private class HelloListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            flg = 1;
        }
    }



}
