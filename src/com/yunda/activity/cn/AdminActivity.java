package com.yunda.activity.cn;

import com.example.yunda_app_2016_3_18.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends Activity implements OnClickListener{
	private Button adminButton;
	private Button backButton;
	private EditText admiEditText;
	private EditText passwordEditText;
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	//取消状态栏   
	  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
	    WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	setContentView(R.layout.loginsetting);
	adminButton=(Button) findViewById(R.id.logindenglu);
	backButton=(Button) findViewById(R.id.backlogin);
	admiEditText=(EditText) findViewById(R.id.admintext);
	passwordEditText=(EditText) findViewById(R.id.adminpassword);
	adminButton.setOnClickListener(this);
	backButton.setOnClickListener(this);
}

@Override
public void onClick(View v) {
	switch (v.getId()) {
	case R.id.logindenglu:
		if (passwordEditText.getText().toString().equals("123695")) {
			Intent mIntent=new Intent(AdminActivity.this,AdminSettingActivity.class);
			startActivity(mIntent);
		}else if (passwordEditText.getText().toString().equals("963557")){
			Intent mIntent=new Intent(AdminActivity.this,AdminSettingActivity.class);
			startActivity(mIntent);
		}else {
			Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show();
		}
		break;

	case R.id.backlogin:
		finish();
		break;
	}
}



}
