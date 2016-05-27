package com.yunda.activity.cn;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.yunda.imp.cn.ImpProblem;
import com.yunda.untils.MD5transformation;
import com.yunda.untils.Problem;
import com.yunda.untils.ReadFileSN;
import com.yunda.untils.User;
import com.yunda.xlistview.cn.ProblemListAdapter;
import com.yunda.xlistview.cn.XListView;
import com.yunda.xlistview.cn.XListView.IXListViewListener;
import com.yunda.xlistview.cn.XListView.OnXScrollListener;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ProblemListActivity extends Activity {
	private ListView mListView;
	private ProgressBar mProgressBar;
	private TextView problemTextView;
    private static final String URL_INTENT="http://opws.yundasys.com:9900/ws/ws.jsp";
    private String userID;
	private String passWord;
	private String conpanyCode;
    private ImpProblem mImpProblem;
    private ProblemListAdapter mProblemListAdapter;
    private List<Problem> mList;
    private SharedPreferences mSharedPreferences;
    private int s;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.problemlistactivity);
	//初始化
	findViewById();
	//下载资料
	if (s==0) {
		problemTextView.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.VISIBLE);
		downLoad();
	}
	//查询数据适配
	lookdb();
}

private void findViewById() {
	mListView=(ListView) findViewById(R.id.problemListView);
	mProgressBar=(ProgressBar) findViewById(R.id.problemprogressBar1);
	problemTextView=(TextView) findViewById(R.id.problemdengluid);
	mImpProblem=new ImpProblem(this);
	mSharedPreferences=getSharedPreferences("pro", MODE_PRIVATE);
	s=mSharedPreferences.getInt("s", 0);
	userID=getIntent().getStringExtra("userID");
	passWord=getIntent().getStringExtra("passWord");
	conpanyCode=getIntent().getStringExtra("conpanyCode");
}
private void downLoad() {
	byte[] b = new byte[1024]; 
	InputStream mInputStream=null;
	try {
		mInputStream=getResources().getAssets().open("problem.xml");
		             mInputStream.read(b);
		             mInputStream.close();
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	String xml=new String(b).trim()
			.replace("mcompany",conpanyCode)
	     	.replace("muser", userID)
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
					Toast.makeText(ProblemListActivity.this, "网络异常请检查网络！",
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
			String d2=null;
			String d3=null;
			
			while (eventType!=XmlPullParser.END_DOCUMENT) {
				if (eventType==XmlPullParser.START_TAG) {
					if("o".equals(parser.getName())){
						parser.next();
						for (int i = 0; i <4;i++, parser.next()) {				
							if (i==0) {
								d0=parser.nextText();
							}else if(i==2){
								d1=parser.nextText();
							}else if(i==3){
								 d2=parser.nextText();

							}else if(i==1){
								d3=parser.nextText();
							}
						}
						int a= (int) mImpProblem.createImpProblem(d1,d0);	
						Log.i("mImpUserRegistration", d0+"="+d1+"="+a+"");
					}
					}
				 eventType = parser.next();	
				}
					s=1;
			mSharedPreferences.edit().putInt("s", s).commit();		
	     } catch (Exception e) {
	    	 Log.i("mImpUserRegistration", e.toString());
			e.printStackTrace();
		}	
	}
private void lookdb(){
mList=mImpProblem.QueryProfession();
mProblemListAdapter=new ProblemListAdapter(this, mList);
mListView.setAdapter(mProblemListAdapter);
}
}

