package com.yunda.activity.cn;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
import com.yunda.imp.cn.ImpAbnormal;
import com.yunda.untils.Abnorma;
import com.yunda.untils.MD5transformation;
import com.yunda.untils.ReadFileSN;
import com.yunda.untils.RoundProgressBar;
import com.yunda.xlistview.cn.AbnormalAdapter;

import android.R.integer;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
public class AbnormalActivity extends Activity {
	private ListView mListView;
    private static final String URL_INTENT="http://opws.yundasys.com:9900/ws/ws.jsp";
    private String conpanyCode;
    private String userID;
    private String passWord;
    private String androidId;
    private ImpAbnormal mImpAbnormal;
    private AbnormalAdapter mProblemListAdapter;
    private List<Abnorma> mList;
    private SharedPreferences mSharedPreferences;
    private int q;
    private RoundProgressBar mProgressBar;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	//取消状态栏   
	  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
	    WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	setContentView(R.layout.abnormalactivity);
		//初始化
	findViewById();
	//数据下载
	if (q==0) {
		downLoad();
		mProgressBar.setVisibility(View.VISIBLE);
	}
	//查询数据适配
	lookdb();
}
private void findViewById() {
	androidId=ReadFileSN.ReadFile();
	mListView=(ListView) findViewById(R.id.abnorListView);
	mProgressBar=(RoundProgressBar) findViewById(R.id.roundProgressBar5);
	mImpAbnormal=new ImpAbnormal(this);
	userID=getIntent().getStringExtra("userID");
	passWord=getIntent().getStringExtra("passWord");
	conpanyCode=getIntent().getStringExtra("conpanyCode");
	mSharedPreferences=getSharedPreferences("abn", MODE_PRIVATE);
	q=mSharedPreferences.getInt("q", 0);
	mProgressBar.setMax(23);
	mProgressBar.setVisibility(View.GONE);
}
private void downLoad() {
	byte[] b = new byte[1024]; 
	InputStream mInputStream=null;
	try {
		mInputStream=getResources().getAssets().open("abnormal.xml");
		             mInputStream.read(b);
		             mInputStream.close();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	String xml=new String(b).trim()
			.replace("mcompany",conpanyCode)
	     	.replace("muser", userID)
			.replace("mpass", new MD5transformation().MD5(passWord))
			.replace("mdev1", androidId)
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
					Toast.makeText(AbnormalActivity.this, "网络异常请检查网络！",
							Toast.LENGTH_SHORT).show();
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					Log.i("xmlInfoParaseModel", arg0.result);
					xmlProblemListParse(arg0.result);
				}
			});
}
private void xmlProblemListParse(String result) {
	// xml解析
	 XmlPullParser parser = Xml.newPullParser();	
		try {
			parser.setInput(new ByteArrayInputStream(result.getBytes("UTF-8")), "UTF-8");
			int eventType =parser.getEventType();
			String d0=null;
			String d1=null;
			int j =0;
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
						int a= (int) mImpAbnormal.create(d1,d0);	
						Log.i("mImpUserRegistration", d0+"="+d1+"="+a+"");
						if (mProgressBar.getProgress()==mProgressBar.getMax()) {
							mProgressBar.setVisibility(View.GONE);
						}else {
							mProgressBar.setProgress(j);
						}
					}
					}
					eventType = parser.next();	
				 	j++;
				}
					q=1;
			mSharedPreferences.edit().putInt("q", q).commit();		
	     } catch (Exception e) {
	    	 Log.i("mImpUserRegistration", e.toString());
			e.printStackTrace();
		}	
	}
private void lookdb(){
mList=mImpAbnormal.QueryProfession();
Log.i("mList", mList.size()+"");
mProblemListAdapter=new AbnormalAdapter(this, mList);
mListView.setAdapter(mProblemListAdapter);
}
}
