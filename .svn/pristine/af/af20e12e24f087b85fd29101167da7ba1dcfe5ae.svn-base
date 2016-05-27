package com.yunda.activity.cn;

import com.example.yunda_app_2016_3_18.R;
import com.example.yunda_app_2016_3_18.R.id;
import com.example.yunda_app_2016_3_18.R.layout;
import com.example.yunda_app_2016_3_18.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class RecordActivity extends Activity {
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_record);
	}
    public void onclick(View v){
	switch (v.getId()) {
	case R.id.yetongbutton:
		Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
		break;
	case R.id.jscanbutton:
		Intent jsIntent=new Intent(RecordActivity.this,ScansearchActivity.class);
		jsIntent.putExtra("userID", getIntent().getStringExtra("userID"));
		jsIntent.putExtra("profession", getIntent().getStringExtra("profession"));
		jsIntent.putExtra("professionID", getIntent().getStringExtra("professionID"));
		jsIntent.putExtra("passWord", getIntent().getStringExtra("passWord"));
		jsIntent.putExtra("conpanyCode", getIntent().getStringExtra("conpanyCode"));
		startActivity(jsIntent);
		break;

	default:
		break;
	}
	
}
}
