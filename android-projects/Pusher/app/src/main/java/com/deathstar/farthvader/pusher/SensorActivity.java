package com.deathstar.farthvader.pusher;

import android.content.Context;
import android.database.MatrixCursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private List<Sensor> sensorList;
    private String[] myStringArray;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        String[] columns = new String[] { "sensor", "reading" };
        int[] toViews = {R.id.sensor, R.id.reading};

        MatrixCursor matrixCursor = new MatrixCursor(columns);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        myStringArray = new String[sensorList.size()];
        for (int i = 0; i < sensorList.size(); i++) {
            matrixCursor.addRow(new Object[] { sensorList.get(i).toString(), "" } );
        }

        adapter = new SimpleCursorAdapter(
                this, R.layout.sensor_reading, matrixCursor, columns, toViews
        );

        ListView listView = (ListView)findViewById(R.id.string_list);
        listView.setAdapter(adapter);
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        // Position of sensor in `sensorList` is synonymous with View rendered under ListView
        myStringArray[sensorList.indexOf(event.sensor)] = String.valueOf(event.values[0]);
        // Notify the adapter that data has changed
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        for(Sensor s: sensorList) {
            mSensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

}
