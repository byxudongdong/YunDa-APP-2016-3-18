package com.yunda.activity.cn;

import com.example.yunda_app_2016_3_18.R;
import com.yundadialog.BaseAnimatorSet;
import com.yundadialog.BounceTopEnter;
import com.yundadialog.OnBtnClickL;
import com.yundadialog.SlideBottomExit;
import com.yundadialog.T;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Selection;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class ServerSettingActivity extends Activity implements OnClickListener{
private EditText mDataSend;//数据收发
private EditText mSoftware_upgrade;//软件升级
private EditText mExpress_query_server;//快件查询
private EditText mAutomatic_package;//自动集包地址
private EditText mWebserver;//Web服务器地址
private SharedPreferences sp;
private Button determineButton;//确定
private Button finshiButton;//返回
private BaseAnimatorSet bas_out;
private BaseAnimatorSet bas_in;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	//取消状态栏   
	  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
	    WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	  	setContentView(R.layout.serversetting);
	  	findViewById();
}
private void findViewById() {
	mDataSend=(EditText) findViewById(R.id.datasend);
	mSoftware_upgrade=(EditText) findViewById(R.id.Software_upgrade);
	mExpress_query_server=(EditText) findViewById(R.id.Express_query_server);
	mAutomatic_package=(EditText) findViewById(R.id.Automatic_package);
	mWebserver=(EditText) findViewById(R.id.serverweb);
	determineButton=(Button) findViewById(R.id.determine);
	finshiButton=(Button) findViewById(R.id.finshi);
	sp=getSharedPreferences("host", MODE_PRIVATE);
	 bas_in = new BounceTopEnter();
     bas_out = new SlideBottomExit();
     determineButton.setOnClickListener(this);
     finshiButton.setOnClickListener(this);
     mDataSend.setText(sp.getString("datadend","http://qz.yundasys.com:9900/ws/ws.jsp"));
     mSoftware_upgrade.setText(sp.getString("softwareupgrade", "暂未设置"));
     mExpress_query_server.setText(sp.getString("expressqueryserver", "暂未设置"));
     mAutomatic_package.setText(sp.getString("automaticpackage", "暂未设置"));
     mWebserver.setText(sp.getString("webserver", "暂未设置"));
     Selection.setSelection(mDataSend.getText(), mDataSend.length());
     Selection.setSelection(mSoftware_upgrade.getText(), mSoftware_upgrade.length());
     Selection.setSelection(mExpress_query_server.getText(), mExpress_query_server.length());
     Selection.setSelection(mAutomatic_package.getText(), mAutomatic_package.length());
     Selection.setSelection(mWebserver.getText(), mWebserver.length());
     
   
}
@Override
public void onClick(View v) {
	switch (v.getId()) {
	case R.id.determine:
		NormalDialogStyleTwo();
		break;
	case R.id.finshi:
		finish();
		break;

	}
}
private void NormalDialogStyleTwo() {
    final com.yundadialog.NormalDialog dialog = new com.yundadialog.NormalDialog(ServerSettingActivity.this);
    dialog.content("为保证数据上传不出现故障，确定要修改服务器地址吗？")//
            .style(com.yundadialog.NormalDialog.STYLE_TWO)//
            .titleTextSize(23)//
            .showAnim(bas_in)//
            .dismissAnim(bas_out)//
            .show();

    dialog.setOnBtnClickL(
            new OnBtnClickL() {
                @Override
                public void onBtnClick() {
                    dialog.dismiss();
                }
            },
            new OnBtnClickL() {
                @Override
                public void onBtnClick() {
                	sp.edit().putString("datadend", mDataSend.getText().toString()).commit();//数据收发地址
                	sp.edit().putString("softwareupgrade", mSoftware_upgrade.getText().toString()).commit();//软件你升级地址
                	sp.edit().putString("expressqueryserver", mExpress_query_server.getText().toString()).commit();//快件查询
                	sp.edit().putString("automaticpackage", mAutomatic_package.getText().toString()).commit();//自动集包地址
                	sp.edit().putString("webserver", mWebserver.getText().toString()).commit();//web服务器地址
                   //
                	//dialog.dismiss();
                    finish();
                }
            });

}
}
