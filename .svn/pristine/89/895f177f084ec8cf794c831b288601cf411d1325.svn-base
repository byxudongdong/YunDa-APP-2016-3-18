package com.yunda.activity.cn;
import java.security.Principal;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.yunda_app_2016_3_18.R;
import com.yunda.filestorage.cn.Filestorage;
import com.yunda.imp.cn.ImpUserRegistration;
import com.yunda.untils.CompanyList;
import com.yunda.untils.RoundProgressBar;
import com.yunda.xlistview.cn.CompanyListAdapter;
import com.yunda.xlistview.cn.XListView;
import com.yunda.xlistview.cn.XListView.IXListViewListener;
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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
public class CompangListActivity extends Activity implements IXListViewListener{
private XListView mListView;
private CompanyListAdapter mAdapter;
private List<CompanyList> mList;
private ImpUserRegistration mImpUserRegistration;
private int m;
private int n=10;
private int b=m+m*n;
private Handler mHandler;
private RoundProgressBar mRoundProgressBar; 
private int a ;
private int p;
private Intent ServiceIntent;
private SharedPreferences mSharedPreferences;
private String userID;
private String passWord;
private String conpanyCode;
private static String INITVIEW="com.yunda.activity.cn.CompangListActivity";
private Handler mHandler2=new Handler();
private boolean flag=true;
private Runnable mRunnable=new Runnable() {
	@Override
	public void run() {
		mRoundProgressBar.setProgress(mService.getIndex());
		mHandler2.postDelayed(mRunnable, 1000);
		Log.i("mRunnable", mService.getIndex()+"");
		if (flag==true&&mService.getIndex()>500) {
			b=m+m*n;
			mImpUserRegistration.companyLists(n, b);
			mAdapter.notifyDataSetChanged();
				m++;		
			onLoad();
			flag=false;
		}
	}
};
private DownloadService mService;
private ServiceConnection mConnection=new ServiceConnection() {
	
	@Override
	public void onServiceDisconnected(ComponentName name) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onServiceConnected(ComponentName name, IBinder service) {
		mService=((DownloadService.Binders)service).getDownloadService();
		mHandler2.post(mRunnable);
		
	}
};

private BroadcastReceiver receiver=new BroadcastReceiver() {
	@Override
	public void onReceive(Context context, Intent intent) {
		String action=intent.getAction();
		if (action.equals(INITVIEW)) {
			a=intent.getIntExtra("num", 0);
			String string=intent.getStringExtra("st");
			if (string.equals("ok")) {
				mSharedPreferences.edit().putInt("a", a).commit();
				unbindService(mConnection);
				stopService(ServiceIntent);
				mHandler2.removeCallbacks(mRunnable);
				mRoundProgressBar.setVisibility(View.GONE);
				Log.i("mSharedPreferences", a+"         "+string+"");
			}else {
				unbindService(mConnection);
				stopService(ServiceIntent);
				mHandler2.removeCallbacks(mRunnable);
				mRoundProgressBar.setVisibility(View.GONE);
				Toast.makeText(getApplicationContext(), "下载失败！", Toast.LENGTH_SHORT).show();
			}
			
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
	setContentView(R.layout.companglistactivity);
	//初始化
	findViewById();
	
}
@Override
	protected void onStart() {
		super.onStart();
		IntentFilter filter=new IntentFilter(INITVIEW);
		registerReceiver(receiver, filter);
	}

private void findViewById() {
	mRoundProgressBar=(RoundProgressBar) findViewById(R.id.roundProgressBar5);
	mListView=(XListView) findViewById(R.id.xListView1);
	mImpUserRegistration=new ImpUserRegistration(this);
	mList= mImpUserRegistration.companyLists(n, b);
	m++;
	mAdapter=new CompanyListAdapter(this, mList);
	mListView.setAdapter(mAdapter);
	mListView.setXListViewListener(this);
	mSharedPreferences=getSharedPreferences("num", MODE_PRIVATE);
	mListView.setPullLoadEnable(true);// 设置让它上拉，FALSE为不让上拉，便不加载更多数据
	mHandler=new Handler();
	p=mSharedPreferences.getInt("a", 0);
	Log.i("mListView", p+"");
	userID=getIntent().getStringExtra("userID");
	passWord=getIntent().getStringExtra("passWord");
	conpanyCode=getIntent().getStringExtra("conpanyCode");
	Log.i("findViewById", "userID="+userID+"passWord="+passWord+"conpanyCode="+conpanyCode);
	if (p==0) {
			mRoundProgressBar.setVisibility(View.VISIBLE);
			mRoundProgressBar.setMax(250843);
		 ServiceIntent=new Intent(this,DownloadService.class);
		 ServiceIntent.putExtra("userID", userID);
		 ServiceIntent.putExtra("passWord", passWord);
		 ServiceIntent.putExtra("conpanyCode", conpanyCode);
		 bindService(ServiceIntent, mConnection, Service.BIND_AUTO_CREATE);
		startService(ServiceIntent);
		
		
	}
	new Filestorage(this);	
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
			mImpUserRegistration.companyLists(n, b);
			mAdapter.notifyDataSetChanged();
			m++;		
			onLoad();
			Log.i("onLoadMore", "n="+n+"<><><><"+"b="+b);
		}
	}, 0);
}
@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}


}
