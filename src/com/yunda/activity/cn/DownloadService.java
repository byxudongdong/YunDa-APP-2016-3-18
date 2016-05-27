package com.yunda.activity.cn;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import org.apache.http.entity.StringEntity;
import org.xmlpull.v1.XmlPullParser;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yunda.imp.cn.ImpUserRegistration;
import com.yunda.untils.MD5transformation;
import com.yunda.untils.ReadFileSN;

import android.R.integer;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;
public class DownloadService extends Service {
private String URL_INTENTCOMPANY="http://opws.yundasys.com:9900/ws/ws.jsp";
private String userName;
private  String userPassword;
private String comPanyCdode;
private ImpUserRegistration mImpUserRegistration;
private final static int  DWONLOAD=3;
private static String INITVIEW="com.yunda.activity.cn.CompangListActivity";
private int num;
private int index;
private Binders mBinders;
private String st="";
private String res;
@Override
	public void onCreate() {
		super.onCreate();
		mImpUserRegistration=new ImpUserRegistration(this);	
		mBinders=new Binders();
	Log.i("onCreate", "onCreate")	;
	}
@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("onDestroy", "onDestroy");
	}
@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}
	@Override
	public IBinder onBind(Intent intent) {
		userName=intent.getStringExtra("userID");
		userPassword=intent.getStringExtra("passWord");
		comPanyCdode=intent.getStringExtra("conpanyCode");
		startDownload();
		return mBinders;
	}
class Binders extends Binder{
	public DownloadService getDownloadService(){
		return DownloadService.this;
	}
}
	private void startDownload() {
		byte[] bs=new byte[1204*1024];
		InputStream mInputStream=null;
			String xml=null;
		try {
			mInputStream=getResources().getAssets().open("companydownload.xml");
			mInputStream.read(bs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		xml=new String(bs).trim()
				.replace("mcompany",comPanyCdode)
				.replace("muser", userName)
				.replace("mpass", new MD5transformation().MD5(userPassword))
				.replace("mdev1", ReadFileSN.ReadFile())
				.replace("mdev2", "1009");
		HttpUtils mHttpUtils=new HttpUtils(5000);
		RequestParams mRequestParams=new RequestParams();
		try {
			mRequestParams.setBodyEntity(new StringEntity(xml, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mHttpUtils.send(HttpMethod.POST, URL_INTENTCOMPANY, mRequestParams, new RequestCallBack<String>() {
			@Override
			public void onFailure(HttpException arg0, String arg1) {
					
					startDownload();	
					
			}
			@Override
			public void onSuccess(final ResponseInfo<String> arg0) {
						Log.i("data", arg0.result);
					new Mythread( arg0.result).start();			
			}
		});
	}
	//xml解析
public class Mythread extends Thread{
private String result;		
	public Mythread(String result){
		this.result=result;
	}
	@Override
		public void run() {
			super.run();
			Looper.prepare();
			  XmlPullParser parser = Xml.newPullParser();	
			try {
				parser.setInput(new ByteArrayInputStream(result.getBytes("UTF-8")), "UTF-8");
				int eventType =parser.getEventType();
				String d1=null;
				String d0=null;
				while (eventType!=XmlPullParser.END_DOCUMENT) {
					if (eventType==XmlPullParser.START_TAG) {
						if ("dta".equals(parser.getName())) {
							st=parser.getAttributeValue(0);
							res=parser.getAttributeValue(1);
							Log.i("res", st);
						}
						if (st.equals("ok")) {
							if("o".equals(parser.getName())){
								parser.next();
								for (int i = 0; i < 3;i++, parser.next()) {				
									if (i==0) {
										d0=parser.nextText();
									}else if(i==1){
										d1=parser.nextText();
									}
								}
								int a= (int) mImpUserRegistration.createUser(d1,d0);	
								Log.i("mImpUserRegistration", d0+"="+d1+"="+a+"");
							}	
						}
					if(st.equals("er")){
							
							Toast.makeText(getApplicationContext(), "此设备未注册或网络异常请检查", Toast.LENGTH_SHORT).show();
							Intent mIntent=new Intent(INITVIEW);
							mIntent.putExtra("st", st);
							sendBroadcast(mIntent);	
							Looper.loop();
							return ;
						}
						}
					 eventType = parser.next();	
					 		index++;
					}
						num=1;
						Intent mIntent=new Intent(INITVIEW);
						mIntent.putExtra("num", num);
						mIntent.putExtra("st", st);
						Toast.makeText(getApplicationContext(), "数据下载完毕！", Toast.LENGTH_SHORT).show();
						sendBroadcast(mIntent);	
						Looper.loop();
		     } catch (Exception e) {
		    	 Log.i("mImpUserRegistration", e.toString());
				e.printStackTrace();
			}	
		}	
}	
	public int getIndex(){
		return index;
}
}
