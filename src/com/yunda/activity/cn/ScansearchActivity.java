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

public class ScansearchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_scansearch);
	}
	
  public void onclick(View v){
	  switch (v.getId()) {
	case R.id.cjscanbutton:
		Intent gIntent=new Intent(ScansearchActivity.this,SearchrecrodActivity.class);
		gIntent.putExtra("userID", getIntent().getStringExtra("userID"));
		gIntent.putExtra("profession", getIntent().getStringExtra("profession"));
		gIntent.putExtra("professionID", getIntent().getStringExtra("professionID"));
		gIntent.putExtra("passWord", getIntent().getStringExtra("passWord"));
		gIntent.putExtra("conpanyCode", getIntent().getStringExtra("conpanyCode"));
		startActivity(gIntent);
		break;
	case R.id.checkout:
		Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
		break;
	case R.id.autoscanbutton:
		Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
		break;

	default:
		break;
	}
  }

	
}
