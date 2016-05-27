package com.yunda.activity.cn;

import com.example.yunda_app_2016_3_18.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class VehicleSchedulingActivity extends Activity implements OnClickListener{
	private Button mShuttle_bus_one;
	private Button mShuttle_bus_two;
	private Button mSetting_three;
	private Button mShuttle_bus_back_button;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.vehiclescheduling);
			findViewById();
		}
		private void findViewById() {
			mShuttle_bus_one=(Button) findViewById(R.id.yewubutton);
			mShuttle_bus_two=(Button) findViewById(R.id.jiluchaxunbutton);
			mSetting_three=(Button) findViewById(R.id.xinxichaxunbutton);
			mShuttle_bus_back_button=(Button) findViewById(R.id.Shuttle_bus_back_button);
			mShuttle_bus_one.setOnClickListener(this);
			mShuttle_bus_two.setOnClickListener(this);
			mSetting_three.setOnClickListener(this);
			mShuttle_bus_back_button.setOnClickListener(this);
		}
		@Override
		public void onClick(View arg0) {
			switch (arg0.getId()) {
			case R.id.yewubutton:
				Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
				break;

	case R.id.jiluchaxunbutton:
		Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();	
				break;
	case R.id.xinxichaxunbutton:
	
		break;
	case R.id.Shuttle_bus_back_button:
		finish();
		break;
			}
			
		}

}
