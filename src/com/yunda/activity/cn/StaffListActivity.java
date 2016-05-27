package com.yunda.activity.cn;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.http.entity.StringEntity;
import org.xmlpull.v1.XmlPullParser;

import com.example.yunda_app_2016_3_18.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yunda.imp.cn.ImpUserRegistration;
import com.yunda.untils.MD5transformation;
import com.yunda.untils.ReadFileSN;
import com.yunda.untils.RoundProgressBar;
import com.yunda.untils.UserList;
import com.yunda.xlistview.cn.UserListAdapter;
import com.yunda.xlistview.cn.XListView;
import com.yunda.xlistview.cn.XListView.IXListViewListener;

import android.R.integer;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.Window;

public class StaffListActivity extends Activity implements IXListViewListener {
	private XListView stuffListView1;
	private String URL_INTENTSTAFF="http://opws.yundasys.com:9900/ws/ws.jsp";
	public static final int MSG_INIT = 0;
	private ImpUserRegistration mImpUserRegistration;
	private int g;
	private int j=5;
	private int k=g+g*j;
	private List<UserList> mUserLists;
	private UserListAdapter mUserListAdapter;
	private String userID;
	private String passWord;
	private String conpanyCode;
	private RoundProgressBar mProgressBar;
	private SharedPreferences mSharedPreferences;
	   private int q;
	   private int h;
	   private boolean flag=true;
  private Handler mHandler=new Handler(){
	public void handleMessage(android.os.Message msg) {
		switch (msg.what) {
		case MSG_INIT:
			String result=(String) msg.obj;
			XmlPullParser(result);
		Log.i("result", result)	;
			break;
		
		}
	};
};
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.stafflistactivity);
	stuffListView1=(XListView) findViewById(R.id.stuffListView1);
	mProgressBar=(RoundProgressBar) findViewById(R.id.roundProgressBar5);
	mProgressBar.setVisibility(View.GONE);
	mImpUserRegistration=new ImpUserRegistration(this);
	userID=getIntent().getStringExtra("userID");
	passWord=getIntent().getStringExtra("passWord");
	conpanyCode=getIntent().getStringExtra("conpanyCode");
	Log.i("conpanyCode", conpanyCode+"   "+userID);
	mSharedPreferences=getSharedPreferences("abn", MODE_PRIVATE);
	q=mSharedPreferences.getInt("q", 0);
	//开始下载
	//startDownload();
	if (q==0) {
		mProgressBar.setVisibility(View.VISIBLE);
		mProgressBar.setMax(161);
	new InitThread(userID,passWord,conpanyCode).start();
	
	}
	stuffListView1.setXListViewListener(this);
	stuffListView1.setPullLoadEnable(true);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
	
}
@Override
protected void onStart() {
	super.onStart();
	mUserLists=mImpUserRegistration.userLists( k, j);
	mUserListAdapter=new UserListAdapter(this, mUserLists);
	stuffListView1.setAdapter(mUserListAdapter);
	mUserListAdapter.notifyDataSetChanged();
	g++;
}
private  class InitThread extends Thread	{
private String  xml;
private byte [] c;
private byte[] b;
private InitThread(String userName,String passWord,String conpanyCode){
	b = new byte[1024]; 
	try {
		InputStream inputStream = getResources().getAssets().open("stafflist.xml");
		inputStream.read(b);
		inputStream.close();
	} catch (IOException e1) {
		e1.printStackTrace();
	
	
	}
	xml=new String(b).trim()
			.replace("mcompany",conpanyCode)
			.replace("muser", userName)
			.replace("mpass", new MD5transformation().MD5(passWord))
			.replace("mdev1", ReadFileSN.ReadFile())
			.replace("mdev2", "1009");
	Log.i("abcd",xml);
}
@Override
public void run() {
	super.run();
	HttpURLConnection conn = null;
	InputStream mInputStream=null;
	OutputStream mOutputStream=null;
	BufferedReader mReader=null;
	ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
	 String a ;
	try {
		URL url = new URL(URL_INTENTSTAFF);
		conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setRequestProperty("Content-Type", "application/soap+xml; charset=utf-8");
//		conn.setRequestProperty("Content-Length", String.valueOf(xml.getBytes().length));
		mOutputStream=conn.getOutputStream();
		mOutputStream.write(xml.getBytes());
		mInputStream = conn.getInputStream();	
		 c = new byte[1024*1024];
		new String(c).trim();
		mReader=new BufferedReader(new InputStreamReader(mInputStream));
		while ((a=mReader.readLine())!=null) {
			outputStream.write(a.getBytes());
//			 mBuffer=new StringBuffer();
//			 mBuffer.append(a);
			Log.i("resultXml",a);
			
		}
		byte[] b=outputStream.toByteArray();
		String kString=new String(b);	
		Log.i("kString", kString.substring(kString.length()-10));
		q=1;
		mSharedPreferences.edit().putInt("q", q).commit();
		mHandler.obtainMessage(MSG_INIT,kString).sendToTarget();
	} catch (Exception e) {
		e.printStackTrace();
		Log.i("abcd","e2 = "+e.toString());
	}finally
	{
		if (conn != null)
		{
			conn.disconnect();
		}
		if (mOutputStream != null&&mInputStream!=null)
		{
			try
			{
				mOutputStream.close();
				mInputStream.close();
				mReader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
}
//xml解析
protected void XmlPullParser( String result ) {
    XmlPullParser parser = Xml.newPullParser();
     try {
		parser.setInput(new ByteArrayInputStream(result.getBytes("UTF-8")), "UTF-8");
		int eventType =parser.getEventType();
		String d1=null;
		String d0=null;
		while (eventType!=XmlPullParser.END_DOCUMENT) {
			if (eventType==XmlPullParser.START_TAG) {
				if("o".equals(parser.getName())){
					parser.next();
					for (int i = 0; i <2;i++, parser.next()) {				
						if (i==0) {
							d0=parser.nextText();
						}else if(i==1){
							d1=parser.nextText();
						}
					}
					int a =(int) mImpUserRegistration.insertUser(d0, d1);
					Log.i("mImpUserRegistration",d0+d1);	
					int b;
//					if (a!=-1) {
//						b++;
//					}
					
				}
				}
			 eventType = parser.next();
			 	h++;
			 	if (h>50&&flag==true) {
					k=g+g*j;
					mUserLists=mImpUserRegistration.userLists(k, j);
					mUserListAdapter.notifyDataSetChanged();	
					g++;
					onLoad();
					flag=false;
				}
				if (mProgressBar.getProgress()==mProgressBar.getMax()) {
					mProgressBar.setVisibility(View.GONE);
				}else {
					mProgressBar.setProgress(h);
				}
				
			}
		Log.i("mImpUserRegistration",h+"");	
     } catch (Exception e) {
		e.printStackTrace();
		Log.i("mImpUserRegistration",h+"");
	}
}
private void onLoad() {
	stuffListView1.stopRefresh();
	stuffListView1.stopLoadMore();
	stuffListView1.setRefreshTime("刚刚");
}
@Override
public void onRefresh() {
	onLoad();
	
}
@Override
public void onLoadMore() {
	k=g+g*j;
	mUserLists=mImpUserRegistration.userLists(k, j);
	mUserListAdapter.notifyDataSetChanged();	
	g++;
	onLoad();
}
}
