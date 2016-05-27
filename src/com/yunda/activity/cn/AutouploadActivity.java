package com.yunda.activity.cn;

import com.example.yunda_app_2016_3_18.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class AutouploadActivity extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	//取消状态栏   
	  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
	    WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	  requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.autouploadbutton);
}
}