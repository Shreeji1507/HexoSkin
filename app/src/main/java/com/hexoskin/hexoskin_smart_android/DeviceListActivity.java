package com.hexoskin.hexoskin_smart_android;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Displays list of Hexoskin Devices
 */
public class DeviceListActivity extends AppCompatActivity {

    private BluetoothAdapter _bluetoothAdapter;
    private Handler _uiHandler;

    private RecyclerView _recyclerView;
    private Adapter _adapter;
    private RecyclerView.LayoutManager _layoutManager;
    private MenuItem _menuItem;

    private ArrayList<BluetoothDevice> _devices = new ArrayList<>();

    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        _bluetoothAdapter = ((BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();
        _uiHandler = new Handler();

        _recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        _recyclerView.setHasFixedSize(true);
        _layoutManager = new LinearLayoutManager(this);
        _recyclerView.setLayoutManager(_layoutManager);
        _adapter = new MyAdapter();
        _recyclerView.setAdapter(_adapter);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] { Manifest.permission.ACCESS_COARSE_LOCATION }, PERMISSION_REQUEST_COARSE_LOCATION);
        }

        // button to go to another activity
        Button navigateCourseSelection_button = (Button) findViewById(R.id.btnNavigateCourseSelection);
        navigateCourseSelection_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DeviceListActivity.this, CourseSelection.class));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[],
                                           int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    _menuItem.setEnabled(true);
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Location not granted");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover nearby bluetooth devices.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.show();

                    _menuItem.setEnabled(false);
                }
                return;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_device_list, menu);
        _menuItem = menu.findItem(R.id.scan);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.scan:
                scanLeDevice();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {
                @Override
                public void onLeScan(final BluetoothDevice device, int rssi,
                                     byte[] scanRecord) {

                    runOnUiThread(new Runnable() {
                        @Override

                        public void run() {
                            if (device.getName() != null && device.getName().startsWith("HX")) {

                              //if (device.getName() != null) {
                                boolean found = false;
                                for (BluetoothDevice d : _devices) {
                                    if (d.getName().equals(device.getName())) {
                                        found = true;
                                        break;
                                    }
                                }

                                if(!found) {
                                    _devices.add(device);
                                    _adapter.notifyDataSetChanged();
//                                    Context context = getApplicationContext();
//                                    CharSequence text = "Hello toast!";
//                                    int duration = Toast.LENGTH_SHORT;
//
//                                    Toast toast = Toast.makeText(context, text, duration);
//                                    toast.show();
                                }
                            }
                        }
                    });
                }
            };

    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;

    // Scan for BLE devices
    private void scanLeDevice() {
        // Clear devices
        _devices.clear();

        // reload table
        _adapter.notifyDataSetChanged();
        stopScan();

        // Stops scanning after a pre-defined scan period.
        _uiHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                stopScan();
            }
        }, SCAN_PERIOD);

        _menuItem.setEnabled(false);

        // scan
        _bluetoothAdapter.startLeScan(mLeScanCallback);
    }

    private void stopScan() {
        _menuItem.setEnabled(true);
        _bluetoothAdapter.stopLeScan(mLeScanCallback);
    }


    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView _textView;
            public BluetoothDevice _device;
            public MyViewHolder(View v) {
                super(v);
                _textView = (TextView) v.findViewById(R.id.row_item);
                v.findViewById(R.id.container).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        stopScan();

                        // Show Device Detail
                        Intent intent = new Intent(getApplicationContext(), DeviceActivity.class);
                        intent.putExtra("Device", _device);
                        startActivity(intent);
                    }
                });
            }
        }

        public MyAdapter() {
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            BluetoothDevice device = _devices.get(position);
            holder._textView.setText(device.getName());
            holder._device = device;
        }

        @Override
        public int getItemCount() {
            return _devices.size();
        }
    }
}
