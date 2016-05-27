package com.yunda.activity.cn;

import com.example.yunda_app_2016_3_18.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class Truckloading extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.truckloading);
	}
	public void onclick(View v){
		switch (v.getId()) {
		case R.id.truckscanbutton:
			startActivity(new Intent(Truckloading.this,TrucktypeActivity.class));
			break;
		case R.id.truckfinish:
			Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
			break;
		case R.id.truckloadingback_button:
			startActivity(new Intent(Truckloading.this,BusinessActivity.class));
			break;

		default:
			break;
		}
	}

}
