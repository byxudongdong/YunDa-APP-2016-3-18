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
import com.yunda.imp.cn.ImpArrears;
import com.yunda.untils.Abnorma;
import com.yunda.untils.ArrearsCompany;
import com.yunda.untils.MD5transformation;
import com.yunda.untils.RoundProgressBar;
import com.yunda.xlistview.cn.AbnormalAdapter;
import com.yunda.xlistview.cn.ArrearsCompanyAdapter;
import com.yunda.xlistview.cn.XListView;
import com.yunda.xlistview.cn.XListView.IXListViewListener;

import android.R.integer;
import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class ArrearsActivity extends Activity implements IXListViewListener{
	private XListView mListView;
	private int arr;
	private SharedPreferences mSharedPreferences; 
    private List<ArrearsCompany> mList;
    private ArrearsCompanyAdapter mAdapter;
    private ImpArrears mImpArrears;
    private int m;
    private int n=10;
    private int b=m+m*n;
    private Handler mHandler;
    private static String ARREARSINITVIEW="com.yunda.activity.cn.ArrearsCompangActivity";
    private Intent ServiceIntent;
    private String userID;
	private String passWord;
	private String conpanyCode;
    private RoundProgressBar mProgressBar;
    private ArrearsServices mArrearsServices;
    private boolean flag=true;
    private ServiceConnection mConnection=new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mArrearsServices=((ArrearsServices.Binders)service).getDownloadService();
			mHandler2.post(mRunnable);
		}
	};
    private BroadcastReceiver receiver=new BroadcastReceiver() {	
		@Override
		public void onReceive(Context context, Intent intent) {
			String action=intent.getAction();
			if (action==ARREARSINITVIEW) {
				arr=intent.getIntExtra("n", 0);
				mSharedPreferences.edit().putInt("a", arr).commit();
				unbindService(mConnection);
				stopService(ServiceIntent);
				mProgressBar.setVisibility(View.GONE);
				mHandler2.removeCallbacks(mRunnable);
				Log.i("mSharedPreferences", arr+"");
			}
			
		}
	};
	private Handler mHandler2=new Handler();
	private Runnable mRunnable=new Runnable() {
		@Override
		public void run() {
			mProgressBar.setProgress(mArrearsServices.getIndex());
			mHandler2.postDelayed(mRunnable, 1000);
			Log.i("mRunnable", mArrearsServices.getIndex()+"");
			if (flag==true&&mArrearsServices.getIndex()>500) {
				b=m+m*n;
				mImpArrears.QueryProfession(n, b);
				mAdapter.notifyDataSetChanged();
				m++;		
				onLoad();
				flag=false;
			}
		}
	};
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	//取消状态栏   
	  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
	    WindowManager.LayoutParams.FLAG_FULLSCREEN);
	setContentView(R.layout.arrearsactivity);
	//初始化
	findViewById();
}
@Override
protected void onStart() {
	super.onStart();
	IntentFilter filter=new IntentFilter(ARREARSINITVIEW);
	registerReceiver(receiver, filter);
}
private void findViewById() {
	userID=getIntent().getStringExtra("userID");
	passWord=getIntent().getStringExtra("passWord");
	conpanyCode=getIntent().getStringExtra("conpanyCode");
	mListView=(XListView) findViewById(R.id.arrearsListView);
	mProgressBar=(RoundProgressBar) findViewById(R.id.roundProgressBar5);
	mImpArrears=new ImpArrears(this);
	mSharedPreferences=getSharedPreferences("arr", MODE_PRIVATE);
	arr=mSharedPreferences.getInt("a", 0);
	mListView.setPullLoadEnable(true);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
	mListView.setXListViewListener(this);
	mHandler=new Handler();
	if (arr==0) {
		mProgressBar.setVisibility(View.VISIBLE);
		mProgressBar.setMax(21640);
		 ServiceIntent=new Intent(this,ArrearsServices.class);
		 ServiceIntent.putExtra("userID", userID);
		 ServiceIntent.putExtra("passWord", passWord);
		 ServiceIntent.putExtra("conpanyCode", conpanyCode);
		 Log.i("onBind", "userName="+userID+" "+"passWord="+passWord+" "+"conpanyCode="+conpanyCode);
		 bindService(ServiceIntent, mConnection, Service.BIND_AUTO_CREATE);
		 startService(ServiceIntent);
	}
	mList= mImpArrears.QueryProfession(n, b);
		m++;
	mAdapter=new ArrearsCompanyAdapter(this, mList);
	mListView.setAdapter(mAdapter);
}

@Override
protected void onDestroy() {
	super.onDestroy();
	unregisterReceiver(receiver);
}
private void onLoad() {
	mListView.stopRefresh();
	mListView.stopLoadMore();
	mListView.setRefreshTime("刚刚");
}

@Override
public void onRefresh() {
	mHandler.postDelayed(new Runnable() {
		@Override
		public void run() {
			mAdapter.notifyDataSetChanged();
			onLoad();
		}
	}, 1000);
	
}

@Override
public void onLoadMore() {
	mHandler.postDelayed(new Runnable() {
		@Override
		public void run() {
			b=m+m*n;
			mImpArrears.QueryProfession(n, b);
			mAdapter.notifyDataSetChanged();
			m++;		
			onLoad();
			Log.i("onLoadMore", "n="+n+"<><><><"+"b="+b);
		}
	}, 500);
	
}
}
