package com.yunda.activity.cn;

import java.io.File;

import com.example.yunda_app_2016_3_18.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PacketScanningActivity extends Activity implements OnClickListener{
	private Button mPacketscann_one;
	private Button mPacketscann_two;
	private Button mPacketscann_three;
	private Button mBack_button;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.packetscanning);
	findViewById();
}
private void findViewById() {
	mPacketscann_one=(Button) findViewById(R.id.yewubutton);
	mPacketscann_two=(Button) findViewById(R.id.jiluchaxunbutton);
	mPacketscann_three=(Button) findViewById(R.id.xinxichaxunbutton);
	mBack_button=(Button) findViewById(R.id.Shuttle_bus_back_button);
	mPacketscann_one.setOnClickListener(this);
	mPacketscann_two.setOnClickListener(this);
	mPacketscann_three.setOnClickListener(this);
	mBack_button.setOnClickListener(this);
}
@Override
public void onClick(View arg0) {
	switch (arg0.getId()) {
	case R.id.yewubutton:
		Intent mIntent=new Intent(PacketScanningActivity.this,ScanningActivity.class);
		mIntent.putExtra("userID", getIntent().getStringExtra("userID"));
		mIntent.putExtra("profession", getIntent().getStringExtra("profession"));
		mIntent.putExtra("professionID", getIntent().getStringExtra("professionID"));
		mIntent.putExtra("passWord", getIntent().getStringExtra("passWord"));
		mIntent.putExtra("conpanyCode", getIntent().getStringExtra("conpanyCode"));
		startActivity(mIntent);
		break;
	case R.id.jiluchaxunbutton:
		Toast.makeText(getApplicationContext(), "此功能暂未实现", Toast.LENGTH_SHORT).show();
//		Intent mIntent1=new Intent(PacketScanningActivity.this,ScanningActivity.class);
//		startActivity(mIntent1);
		break;
	case R.id.xinxichaxunbutton:
		Toast.makeText(getApplicationContext(), "此功能暂未实现", Toast.LENGTH_SHORT).show();
//		Intent mIntent11=new Intent(PacketScanningActivity.this,ScanningActivity.class);
//		startActivity(mIntent11);
		break;
	case R.id.Shuttle_bus_back_button:
		finish();
		break;
	}
	
}

}
