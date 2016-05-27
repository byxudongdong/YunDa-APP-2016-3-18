package com.yunda.activity.cn;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.http.entity.StringEntity;
import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextPaint;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yunda_app_2016_3_18.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yunda.imp.cn.ImpUserRegistration;
import com.yunda.untils.MD5transformation;
import com.yunda.untils.MyApplication;
import com.yunda.untils.ReadFileSN;
import com.yunda.untils.User;
public class LoginActivity extends Activity implements OnClickListener{
   // private TextView topText;//提示文字（ 通过安全、快捷的服务，传爱心、送温暖、更便利）
    private TextPaint tp;//获取画笔
    private Button loginbtn;//登录
    private Button mRegister;//设置
    private EditText username;//用户民
    private EditText password;//密码
    private Drawable mIconPerson;//用户输入框显示图标
    private Drawable mIconLock;//密码输入框显示图标
    private String cardNumStr;//获取用户名
    private String passwordStr;//获取密码
    private String comPanyCode;//获取公司编码
    private CheckBox mCheckBox;//保存用户名和密码
    private SharedPreferences sp;//保存用户名和密码
    private EditText mcomPanyCode;
    private ImpUserRegistration mImpUserRegistration;
    private TextView mDeviceId;
    private LinearLayout login_dialog_progress_line;
    private String  androidId;
    private static String URL_INTENT="";
    public static final int MSG_INIT = 0;
    private User mUser;
    private SharedPreferences ps;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//取消状态栏   
		  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
		    WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		sp = this.getSharedPreferences("passwordFile", MODE_PRIVATE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//不随屏幕旋转
		setContentView(R.layout.main);
		mIconPerson=getResources().getDrawable(R.drawable.txt_person_icon);
		mIconPerson.setBounds(5, 1, 60, 50);
		mIconLock=getResources().getDrawable(R.drawable.txt_lock_icon);
		mIconLock.setBounds(5, 1, 60, 50);
		login_dialog_progress_line = (LinearLayout) findViewById(R.id.login_dialog_progress_line);
		login_dialog_progress_line.setVisibility(View.GONE);
		username=(EditText)findViewById(R.id.username);
		username.setCompoundDrawables(mIconPerson, null, null, null);
	    password=(EditText)findViewById(R.id.password);
	    password.setCompoundDrawables(mIconLock, null, null, null);
	    password.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (keyCode==KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN) {
					cardNumStr=username.getText().toString();
					passwordStr=password.getText().toString();
					comPanyCode=mcomPanyCode.getText().toString();
					if((cardNumStr == null||cardNumStr.equalsIgnoreCase("")) || (passwordStr == null||passwordStr.equalsIgnoreCase(""))){
						Toast.makeText(LoginActivity.this, "用户名密码必须输入！",
								Toast.LENGTH_SHORT).show();
					}else{
						validatelogon(cardNumStr,new MD5transformation().MD5(passwordStr),comPanyCode,androidId);
						
						if (mCheckBox.isChecked()) {// 登陆成功才保存密码
								sp.edit().putString("cardNumStr", cardNumStr).commit();
								sp.edit().putString("passwordStr", passwordStr).commit();
								sp.edit().putString("conpanyCode", comPanyCode).commit();
							}
						
						
						}	
							return true;
				}
				
				return false;
			}
		});
	    mImpUserRegistration=new ImpUserRegistration(this);
	    mDeviceId=(TextView) findViewById(R.id.devieid);
	  	androidId=ReadFileSN.ReadFile();
	  	mDeviceId.setText("设备ID："+androidId);	  
	  	Log.i("androidId", androidId);
		init();
	}
    public void init(){
    	mCheckBox=(CheckBox) findViewById(R.id.savePasswordCB);
    	mcomPanyCode=(EditText) findViewById(R.id.companycode);
	    mRegister=(Button) findViewById(R.id.mRegister);
        loginbtn=(Button)findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mCheckBox.setChecked(sp.getBoolean("mCheckBox", false));
        if (mCheckBox.isChecked()==true) {
		    username.setText(sp.getString("cardNumStr", ""));
	        password.setText(sp.getString("passwordStr", ""));
	        mcomPanyCode.setText(sp.getString("conpanyCode", ""));
        }
    }
      @Override
    	protected void onStart() {
    		super.onStart();
    		ps=getSharedPreferences("host", MODE_PRIVATE);
  		  URL_INTENT=ps.getString("datadend","http://qz.yundasys.com:9900/ws/ws.jsp");
    		Log.i("onStart", URL_INTENT);
    	}
@Override
protected void onDestroy() {
	super.onDestroy();
	if (mCheckBox.isChecked()) {
		sp.edit().putBoolean("mCheckBox", true).commit();
		
	}else {
		sp.edit().putBoolean("mCheckBox", false).commit();	
	}
}
	@Override
public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginbtn:
			cardNumStr=username.getText().toString();
			passwordStr=password.getText().toString();
			comPanyCode=mcomPanyCode.getText().toString();
			if((cardNumStr == null||cardNumStr.equalsIgnoreCase("")) || (passwordStr == null||passwordStr.equalsIgnoreCase(""))){
				Toast.makeText(LoginActivity.this, "用户名密码必须输入！",
						Toast.LENGTH_SHORT).show();
			}else{
				validatelogon(cardNumStr,new MD5transformation().MD5(passwordStr),comPanyCode,androidId);
				if (mCheckBox.isChecked()) {// 登陆成功才保存密码
						sp.edit().putString("cardNumStr", cardNumStr).commit();
						sp.edit().putString("passwordStr", passwordStr).commit();
						sp.edit().putString("conpanyCode", comPanyCode).commit();
					}
				}
					break;	
		case R.id.mRegister:
			Intent adminIntent=new Intent(LoginActivity.this,AdminSettingActivity.class);
			startActivity(adminIntent);
			break;
		}
	}
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 0x000){
				login_dialog_progress_line.setVisibility(View.GONE);
			}
		};
	};
public void validatelogon(String userName,String passWord,String conpanyCode,final String androidId){
	byte[] b = new byte[1024]; 
	InputStream mInputStream=null;
	 mUser=new User();
	try {
		mInputStream=getResources().getAssets().open("login.xml");
		             mInputStream.read(b);
		             mInputStream.close();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	login_dialog_progress_line.setVisibility(View.VISIBLE);
	String xml=new String(b).trim()
			.replace("mcompany",conpanyCode)
	     	.replace("muser", userName)
			.replace("mpass", passWord)
			.replace("mdev1", androidId)
			.replace("mdev2", "1009");
		Log.i("xml", xml +androidId);
     HttpUtils mHttpUtils=new HttpUtils(5000);
     RequestParams mRequestParams=new RequestParams();
	try {
		mRequestParams.setBodyEntity(new StringEntity(xml, "utf-8"));
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	   mHttpUtils.send(HttpMethod.POST, URL_INTENT, mRequestParams,new RequestCallBack<String>() {
		@Override
		public void onFailure(HttpException arg0, String arg1) {
			handler.sendEmptyMessage(0x000);
			Toast.makeText(LoginActivity.this, "网络异常请检查网络！",
					Toast.LENGTH_SHORT).show();
			Log.i("onFailure", arg1);
		}

		@Override
		public void onSuccess(ResponseInfo<String> arg0) {
			xmlInfoParaseModel(arg0.result);
			Log.i("onSuccess", arg0.result);
			handler.sendEmptyMessage(0x000);
			try {
				if (mUser.getSt().equals("ok")) {
					Intent mIntent=new Intent(LoginActivity.this,BusinessActivity.class);	
					mIntent.putExtra("name", mUser.getName());
					mIntent.putExtra("time", mUser.getTime());
					mIntent.putExtra("username", username.getText().toString());
					mIntent.putExtra("password", password.getText().toString());
					mIntent.putExtra("comPanyCode", mcomPanyCode.getText().toString());
					mIntent.putExtra("androidId", androidId);
					startActivity(mIntent);
					Toast.makeText(LoginActivity.this, "成功登录！",
							Toast.LENGTH_SHORT).show();	
				}else if(mUser.getOp().equals("1")){
					Toast.makeText(LoginActivity.this, "账号或密码错误！",
							Toast.LENGTH_SHORT).show();
				}	
			} catch (Exception e) {
			}
		
		
		}

	

	});
}

protected void xmlInfoParaseModel(String result) {
	// xml解析
    String st = null;
    String op = null;
    String ver = null;
    String time = null;
    XmlPullParser parser = Xml.newPullParser();
     try {
		parser.setInput(new ByteArrayInputStream(result.getBytes("UTF-8")), "UTF-8");
		int eventType =parser.getEventType();
		while (eventType!=XmlPullParser.END_DOCUMENT) {
			if (eventType==XmlPullParser.START_TAG) {
				if ("dta".equals(parser.getName())) {
					st=parser.getAttributeValue(0);
					op=parser.getAttributeValue(1);
					mUser.setSt(st);
					mUser.setOp(op);
				}else if("ver".equals(parser.getName())){
					ver=parser.nextText();
					mUser.setVer(ver);
				}else if("time".equals(parser.getName())){
					time=parser.nextText();
					mUser.setTime(time);
				}else if("d".equals(parser.getName())){
					mUser.setName(parser.nextText());
			}
				}
			 eventType = parser.next();	
			}	
	 
     } catch (Exception e) {
		e.printStackTrace();
	}
}

}	

