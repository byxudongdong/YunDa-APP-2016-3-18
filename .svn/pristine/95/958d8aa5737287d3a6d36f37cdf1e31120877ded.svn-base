package com.yunda.activity.cn;

import com.example.yunda_app_2016_3_18.R;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;
public class SettingActivity extends Activity implements OnClickListener{
private Button mSynTimeButton;//同步时间
private Button mSoftware_upgrade;//软件升级
private Button minstructionsButton;//使用说明
private Button mBluetooth_pairing;//蓝牙配对
private Button mSoundsettingButton;//声音设置
private Button unloadButton;//立即上传
private Button wifiButton;//无线网设置
private Button backlightButton;//背光设置
private Button cleanButton;//清楚历史记录
private Button w16Button;//指环连接
private Button btn_mbutton16;//返回主菜单
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//取消状态栏   
		  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
		    WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.setting);
		//初始化
		findViewById();
	}
	private void findViewById() {
		mSynTimeButton=(Button) findViewById(R.id.SynTimeButton);
		mSoftware_upgrade=(Button) findViewById(R.id.mSoftware_upgrade);
		minstructionsButton=(Button) findViewById(R.id.minstructionsButton);
		mBluetooth_pairing=(Button) findViewById(R.id.mBluetooth_pairing);
		mSoundsettingButton=(Button) findViewById(R.id.mSoundsettingButton);
		unloadButton=(Button) findViewById(R.id.unloadButton);
		wifiButton=(Button) findViewById(R.id.wifiButton);
		backlightButton=(Button) findViewById(R.id.backlightButton);
		cleanButton=(Button) findViewById(R.id.cleanButton);
		w16Button=(Button) findViewById(R.id.w16Button);
		btn_mbutton16=(Button) findViewById(R.id.btn_mbutton16);
		mSynTimeButton.setOnClickListener(this);
		mSoftware_upgrade.setOnClickListener(this);
		minstructionsButton.setOnClickListener(this);
		mBluetooth_pairing.setOnClickListener(this);
		mSoundsettingButton.setOnClickListener(this);
		unloadButton.setOnClickListener(this);
		wifiButton.setOnClickListener(this);
		backlightButton.setOnClickListener(this);
		cleanButton.setOnClickListener(this);
		w16Button.setOnClickListener(this);
		btn_mbutton16.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
	switch (v.getId()) {
			case R.id.SynTimeButton:
				Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
				break;
			case R.id.mSoftware_upgrade:
				Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
				break;
			case R.id.minstructionsButton:
				Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
				break;
			case R.id.mBluetooth_pairing:
				Intent mBluetooth_pairingIntent=new Intent(SettingActivity.this,BluetoothSettingActivity.class);
				startActivity(mBluetooth_pairingIntent);
	
				break;
			case R.id.mSoundsettingButton:
				Intent soundIntent=new Intent();
				soundIntent.setAction("com.android.settings.SOUND_SETTINGS");
				startActivity(soundIntent);	
				break;
			case R.id.unloadButton:
				Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
				break;
			case R.id.wifiButton:
				Intent wifiIntent=new Intent();
				wifiIntent.setAction("android.settings.WIFI_SETTINGS");
				startActivity(wifiIntent);
				break;
			case R.id.backlightButton:
				Intent lightIntent=new Intent();
				lightIntent.setAction("com.android.settings.DISPLAY_SETTINGS");
				startActivity(lightIntent);	
				break;
			case R.id.cleanButton:
				Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
				break;
			case R.id.w16Button:
				Intent w16Intent=new Intent();
				ComponentName mName=new ComponentName("com.example.hellojni", "com.example.hellojni.HelloJni");
				w16Intent.setComponent(mName);
				w16Intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(w16Intent);
				break;
			case R.id.btn_mbutton16:
				finish();
				break;
	}
	}
}
