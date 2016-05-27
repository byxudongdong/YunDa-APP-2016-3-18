package com.yunda.activity.cn;
import com.example.yunda_app_2016_3_18.R;
import com.yunda.imp.cn.ImpUserRegistration;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class RegistActivity extends Activity implements OnClickListener{
private Button mRegister2;
private EditText mUser_NmaEditText;
private EditText mPasswordEditText;
private EditText mRepeatEditText;
private TextView mTopTextView;
private TextPaint mTextPaint;
private Button mCancleButton;
private ImpUserRegistration mImpUserRegistration;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mregisteractivity);
		//初始化
		findViewById();
		mImpUserRegistration=new ImpUserRegistration(this);
	}
	private void findViewById() {
		mRegister2=(Button) findViewById(R.id.mRegister2);
		mUser_NmaEditText=(EditText) findViewById(R.id.regist_username);
		mPasswordEditText=(EditText) findViewById(R.id.regist_password1);
		mRepeatEditText=(EditText) findViewById(R.id.regist_password2);
		mTopTextView=(TextView) findViewById(R.id.regist_topname);
		mCancleButton=(Button) findViewById(R.id.quxiaobutton);
		mTopTextView.setTextColor(Color.RED);
		mTopTextView.setTextSize(20.0f);
		mTopTextView.setTypeface(Typeface.MONOSPACE,Typeface.BOLD_ITALIC);
		mTextPaint=mTopTextView.getPaint();
		mTextPaint.setFakeBoldText(true);
		mRegister2.setOnClickListener(this);
		mCancleButton.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mRegister2:
			String username = mUser_NmaEditText.getText().toString();
			String password = mPasswordEditText.getText().toString();
			String conPassword = mRepeatEditText.getText().toString();
			if((username == null||username.equalsIgnoreCase("")) || (password == null||password.equalsIgnoreCase("")) || (conPassword == null||conPassword.equalsIgnoreCase(""))){
				Toast.makeText(RegistActivity.this,"用户名密码必须输入.",
						Toast.LENGTH_SHORT).show();
			}else {
				Cursor cursor =mImpUserRegistration .getDiary(username);
				if(cursor.moveToFirst()){
					Toast.makeText(RegistActivity.this, "用户名称已存在.",
							Toast.LENGTH_SHORT).show();
				}else if (!password.equals(conPassword)) {
					Toast.makeText(RegistActivity.this, "两次输入的密码不一致，	请重新输入.",
							Toast.LENGTH_SHORT).show();
				}else{
					mImpUserRegistration.createUser(username, password);
					Toast.makeText(RegistActivity.this, "注册成功！",
							Toast.LENGTH_SHORT).show();
					finish();
				}
			}
			break;

		case R.id.quxiaobutton:
			finish();
			break;
		}
	}
}
