package com.yunda.bluetoothpairing.cn;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.example.yunda_app_2016_3_18.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.AdapterView.OnItemClickListener;
public class BluetoothprinterActivity extends Activity implements OnItemClickListener{
	private ListView xListView;
	private BluetoothAdapter xBluetoothAdapter;
	private List<BluetoothDevice> xBluetoothDevicesList;
	private ProgressBar xProgressBar;
	private static final int REQUEST_ENABLE_BT = 0x0001;
	private MyPrintAdapter mPrintAdapter;
	private BroadcastReceiver receiver=new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			ProgressDialog progressDialog = null; 
			String action = intent.getAction();
			if (BluetoothDevice.ACTION_FOUND.equals(action)) {
				BluetoothDevice device = intent
						.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					xBluetoothDevicesList.add(device);
					xListView.setAdapter(mPrintAdapter);
					mPrintAdapter.notifyDataSetChanged();
			}else if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)){
				  progressDialog = ProgressDialog.show(context, "请稍等...",    
	                        "搜索蓝牙设备中...", true); 
			}else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
				progressDialog.dismiss();    
			}
		}
	};
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE); 
	setContentView(R.layout.printerlistivew);
	findViewById();
}
@Override
protected void onStart() {
	super.onStart();
	initIntentFilter();
}
@Override
protected void onResume(){
	
	super.onResume();
	if (!xBluetoothAdapter.enable()) {
		Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		startActivityForResult(mIntent, REQUEST_ENABLE_BT);
	}
}
private void findViewById() {
	xProgressBar=(ProgressBar) findViewById(R.id.progressBar1);
	xListView=(ListView) findViewById(R.id.printlistview);
	xBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
	if (xBluetoothAdapter.enable()) {
		xBluetoothAdapter.startDiscovery();
	}
	xBluetoothDevicesList=new ArrayList<BluetoothDevice>();
	//mPrintAdapter=new MyPrintAdapter(this, xBluetoothDevicesList);
	xListView.setOnItemClickListener(this);
}
private void initIntentFilter() {
	IntentFilter intentFilter = new IntentFilter();
	intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
	intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
	intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
	intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
	registerReceiver(receiver, intentFilter);
}
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		BluetoothDevice mBluetoothDevice=xBluetoothDevicesList.get(position);
		Method createBondMethod;
		try {
			createBondMethod = BluetoothDevice.class
					.getMethod("createBond");
			createBondMethod.invoke(mBluetoothDevice);
		} catch (Exception e) {
			e.printStackTrace();
		}
}

@Override
protected void onDestroy() {
	super.onDestroy();
	xBluetoothAdapter.cancelDiscovery();
	unregisterReceiver(receiver);
}
}
