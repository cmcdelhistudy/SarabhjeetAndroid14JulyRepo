package com.example.sarabhjeetsensorstudy;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

	TextView tvX, tvY, tvZ;

	SensorManager sensmgr;
	Sensor accSensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvX = (TextView) findViewById(R.id.tvX);
		tvY = (TextView) findViewById(R.id.tvY);
		tvZ = (TextView) findViewById(R.id.tvZ);

		sensmgr = (SensorManager) getSystemService(SENSOR_SERVICE);

		accSensor = sensmgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] sensorValues = event.values;
		

		tvX.setText("X:" + sensorValues[0]);

		tvY.setText("Y:" + sensorValues[1]);

		tvZ.setText("Z:" + sensorValues[2]);

	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {

	}

	@Override
	protected void onResume() {

		sensmgr.registerListener(this, accSensor,
				SensorManager.SENSOR_DELAY_NORMAL);

		super.onResume();
	}

	@Override
	protected void onPause() {
		sensmgr.unregisterListener(this, accSensor);
		super.onPause();
	}
}
