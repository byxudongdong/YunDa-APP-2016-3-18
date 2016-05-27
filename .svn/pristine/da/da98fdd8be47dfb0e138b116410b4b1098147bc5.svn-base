package com.yunda.activity.cn;

import com.example.yunda_app_2016_3_18.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class TrucktypeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.trucktype);
		
	}
    public void onclick(View v){
     switch (v.getId()) {
	case R.id.ztruckscanbutton:
	Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
		break;
	case R.id.ftruckscanbutton:
		startActivity(new Intent(TrucktypeActivity.this,InvoicevoucherscanActivity.class));
			break;
	case R.id.jytruckscanbutton:
		Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
			break;
	case R.id.ctruckscanbutton:
		Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
			break;
	case R.id.trucktypeback_button:
		startActivity(new Intent(TrucktypeActivity.this,Truckloading.class));
			break;

	default:
		break;
	}	
    	
    }
	

}
