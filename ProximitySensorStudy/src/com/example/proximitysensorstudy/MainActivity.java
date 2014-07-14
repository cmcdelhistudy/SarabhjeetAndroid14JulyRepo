package com.example.proximitysensorstudy;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {
	TextView info;

	SensorManager sensmgr;
	Sensor proxSensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		info = (TextView) findViewById(R.id.tvInfo);

		sensmgr = (SensorManager) getSystemService(SENSOR_SERVICE);
		proxSensor = sensmgr.getDefaultSensor(Sensor.TYPE_PROXIMITY);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float[] sensorValues = event.values;

		info.setText("" + sensorValues[0]);

	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		Toast.makeText(getBaseContext(), "Accuracy Changed", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	protected void onResume() {

		sensmgr.registerListener(this, proxSensor,
				SensorManager.SENSOR_DELAY_NORMAL);

		super.onResume();
	}

	@Override
	protected void onPause() {
		sensmgr.unregisterListener(this, proxSensor);
		super.onPause();
	}

}
