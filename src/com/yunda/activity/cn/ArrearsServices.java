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
import com.yunda.activity.cn.DownloadService.Binders;
import com.yunda.imp.cn.ImpArrears;
import com.yunda.untils.MD5transformation;
import com.yunda.untils.ReadFileSN;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

public class ArrearsServices extends Service {
	private static final String URL_INTENT = "http://opws.yundasys.com:9900/ws/ws.jsp";
	private String conpanyCode;
	private String userName ;
	private String passWord;
    private ImpArrears mImpArrears;
    private static String ARREARSINITVIEW="com.yunda.activity.cn.ArrearsCompangActivity";
    private final static int  ARREARDWONLOAD=3;
    private int n;
    private int index;
    private Binders mBinders;
    private Handler mHandler=new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		if (msg.what==ARREARDWONLOAD){
    			Intent mIntent=new Intent(ARREARSINITVIEW);
    			n=1;
    			mIntent.putExtra("n", n);
    			Toast.makeText(getApplicationContext(), "数据下载完毕！", Toast.LENGTH_SHORT).show();
    			sendBroadcast(mIntent);
			}
    		
    	};
    	
    };
@Override
public void onCreate() {
	// TODO Auto-generated method stub
	super.onCreate();
	mImpArrears=new ImpArrears(this);
	mBinders=new Binders();
}
class Binders extends Binder{
	public ArrearsServices getDownloadService(){
		return ArrearsServices.this;
	}
}
private void downLoad() {
	byte[] b = new byte[1024]; 
	InputStream mInputStream=null;
	try {
		mInputStream=getResources().getAssets().open("arrears.xml");
		             mInputStream.read(b);
		             mInputStream.close();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	String xml=new String(b).trim()
			.replace("mcompany",conpanyCode)
	     	.replace("muser", userName)
			.replace("mpass", new MD5transformation().MD5(passWord))
			.replace("mdev1", ReadFileSN.ReadFile())
			.replace("mdev2", "1009");	
	Log.i("xmlInfoParaseModel", xml);
	HttpUtils mHttpUtils=new HttpUtils(5000);
	RequestParams mRequestParams = new RequestParams();
	try {
		mRequestParams.setBodyEntity(new StringEntity(xml, "utf-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	mHttpUtils.send(HttpMethod.POST, URL_INTENT, mRequestParams,
			new RequestCallBack<String>() {
				@Override
				public void onFailure(HttpException arg0, String arg1) {
					Toast.makeText(ArrearsServices.this, "网络异常请检查网络！",
							Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					Log.i("xmlInfoParaseModel", arg0.result);
					new Mythread(arg0.result).start();
				}
			});	
}
public class Mythread extends Thread{
private String result;
public Mythread(String result){
	this.result=result;
}
	@Override
	public void run() {
		// xml解析
		 XmlPullParser parser = Xml.newPullParser();	
			try {
				parser.setInput(new ByteArrayInputStream(result.getBytes("UTF-8")), "UTF-8");
				int eventType =parser.getEventType();
				String d0=null;
				String d1=null;
				while (eventType!=XmlPullParser.END_DOCUMENT) {
					if (eventType==XmlPullParser.START_TAG) {
						if("o".equals(parser.getName())){
							parser.next();
							for (int i = 0; i <2;i++ ,parser.next()) {				
								if (i==0) {
									d0=parser.nextText();
								}else if(i==1){
									d1=parser.nextText();
								}
							}
							int a = (int) mImpArrears.createImpArrears(d0, d1);
							Log.i("mImpUserRegistration", d0+"="+d1+"="+"a="+a+"");
						}
						}
					 eventType = parser.next();	
					 	index++;
					}
						//q=1;
				//mSharedPreferences.edit().putInt("q", q).commit();	
				mHandler.obtainMessage(ARREARDWONLOAD).sendToTarget();
		     } catch (Exception e) {
		    	 Log.i("mImpUserRegistration", e.toString());
				e.printStackTrace();
			}	
	}
}
public int getIndex(){
	return index;
}
@Override
public int onStartCommand(Intent intent, int flags, int startId) {
	
	return super.onStartCommand(intent, flags, startId);
}

	@Override
	public IBinder onBind(Intent intent) {
		userName =intent.getStringExtra("userID");
		passWord=intent.getStringExtra("passWord");
		conpanyCode=intent.getStringExtra("conpanyCode");
		Log.i("onBind", "userName="+userName+" "+"passWord="+passWord+" "+"conpanyCode="+conpanyCode);
		//下载
		downLoad();
		return mBinders;
	}

}
