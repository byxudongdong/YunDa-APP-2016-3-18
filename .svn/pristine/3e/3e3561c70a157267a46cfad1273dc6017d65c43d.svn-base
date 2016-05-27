package com.yunda.activity.cn;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.http.entity.StringEntity;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import com.example.yunda_app_2016_3_18.R;
import com.example.yunda_app_2016_3_18.R.id;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yunda.filestorage.cn.Filestorage;
import com.yunda.imp.cn.ImpDataUpload;
import com.yunda.imp.cn.ImpUserRegistration;
import com.yunda.untils.BagPackageNumber;
import com.yunda.untils.ListData;
import com.yunda.untils.MD5transformation;
import com.yunda.untils.MyApplication;
import com.yunda.untils.PacketDataXml;
import com.yunda.untils.PacketInforMation;
import com.yunda.untils.ReadFileSN;
import com.yunda.untils.ReturnPart;
import com.yunda.untils.ReturnPartXml;
import com.yunda.untils.XMLBagPackageNumber;
import com.yunda.xlistview.cn.ListDataAdapter;
import com.yundaW16setting.cn.MyNative;
import com.yundadialog.BounceTopEnter;
import com.yundadialog.NormalDialog;
import com.yundadialog.OnBtnClickL;
import com.yundadialog.SlideBottomExit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.drm.DrmStore.Playback;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class ScanningActivity extends Activity implements OnItemClickListener,OnClickListener{
	private EditText largeSizePackage;// 大包号
	private EditText nextStation;// 下一站
	private EditText waybillBumber;// 运单号
	private EditText ScannedNumber;// 已扫描数
	private EditText mContent;
	private String userName;
	private String userPassword ;
	private String comPanyCdode ;
	public final static String ACTION_DATA_AVAILABLE = "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
	public final static String EXTRA_DATA = "com.example.bluetooth.le.EXTRA_DATA";
	private String number;
	private ImpUserRegistration mImpUserRegistration;
	public static final int MSG_INIT = 1;
	private int a;
	private String time;
	private BagPackageNumber mBagPackageNumber;
	private String string;
	private SoundPool mSoundPool = null;
	private String deviceID;//
	private static  String URL_INTENT = "";
	private BagPackageNumber mXmlBagPackageNumber;
	private String values;
	private ImpDataUpload mImpDataUpload;
	private static int MSG_WHAT=2;
	private ListData mListData;
	private List<ListData> mDatas;
	private List<ListData> mList;
	private ListDataAdapter mAdapter;
	private String s;
	private String bigNumber;//大包号
	private String awbNumber;//运单号
	private Button addData;
	private Button deleteData;
	private Button backButton;
	private ListView shadowListView;
	private boolean flag=true;
	private TextView packettotal;
	private int postiont;
	private ImageView mImageView;
	private AnimationDrawable frameAnim;
	private MyApplication myApplication;
	private String alreadlyNextstation;
	private String alreadlyNextcode;
	private boolean alreadlydata=true;
	
	private SharedPreferences ps;
	@SuppressLint("UseSparseArrays")
	HashMap<Integer, Integer> soundMap = new HashMap<Integer, Integer>();
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			if (msg.what == MSG_INIT) {
				String s = (String) msg.obj;
				waybillBumber.setText(s);
				mBagPackageNumber.setNextStation(s);
			}		
		};
	};
	MyNative mnative = new MyNative();
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action == ACTION_DATA_AVAILABLE) {
				number = intent.getStringExtra(EXTRA_DATA);
				Log.i("number", number.length() + "");
				BarcodeVeriFication(number);
			}
		}	
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//取消状态栏   
		  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
		    WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		  	requestWindowFeature(Window.FEATURE_NO_TITLE);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			setContentView(R.layout.packagescan);

		// 初始化
		findViewById();
	}
	@SuppressWarnings("deprecation")
	private void findViewById() {
		deviceID=ReadFileSN.ReadFile();//获取设备序列号
		largeSizePackage = (EditText) findViewById(R.id.largesizepackage);
		nextStation = (EditText) findViewById(R.id.NextStation);
		mImageView=(ImageView) findViewById(R.id.packetimageView1);
		waybillBumber = (EditText) findViewById(R.id.NextStationcontent);
		packettotal=(TextView) findViewById(R.id.packettotal);
		ScannedNumber = (EditText) findViewById(R.id.Scannednumber);
		mContent = (EditText) findViewById(R.id.Waybillnumbercontent);
		backButton=(Button) findViewById(R.id.backbutton);
		mImpUserRegistration = new ImpUserRegistration(this);
		mBagPackageNumber = new BagPackageNumber();
		backButton.setOnClickListener(this);
		mImpDataUpload=new ImpDataUpload(this);
		mDatas=new ArrayList<ListData>();
		mList=new ArrayList<ListData>();
		addData=(Button) findViewById(R.id.adddata);
		deleteData=(Button) findViewById(R.id.deletedata);
		shadowListView=(ListView) findViewById(R.id.shadowListView);
		mAdapter=new ListDataAdapter(this, mDatas);
		shadowListView.setAdapter(mAdapter);
		shadowListView.setOnItemClickListener(this);
		deleteData.setOnClickListener(this);
		addData.setOnClickListener(this);
		mAdapter.setIndex(0);
		frameAnim=(AnimationDrawable) getResources().getDrawable(R.drawable.bullet_anim);
		mImageView.setBackgroundDrawable(frameAnim);
		mImageView.setVisibility(View.GONE);
		myApplication=(MyApplication) getApplication();
		  mSoundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
		  soundMap.put(1 , mSoundPool.load(this, R.raw.test_2k_8820_200ms , 1));
		  soundMap.put(2 , mSoundPool.load(this, R.raw.error , 1));
		  ps=getSharedPreferences("host", MODE_PRIVATE);
		  URL_INTENT=ps.getString("datadend","http://qz.yundasys.com:9900/ws/ws.jsp");
		  Log.i("URL_INTENT", URL_INTENT);
	}
	@Override
	protected void onStart() {
		super.onStart();
		IntentFilter filter = new IntentFilter(ACTION_DATA_AVAILABLE);
		registerReceiver(receiver, filter);
	}
	@Override
	protected void onPause() {
		super.onPause();
		myApplication.setmList(mList);
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}	
	// 获取当前时间
	public String getDate() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}
	// 获取当前日期
	public String getDates() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		return time;
	}
	public class MyThread extends Thread {
		private String companyName;;
		private String number;
		public static final String KEY_COMPANYNAME = "companyname";

		public MyThread(String number) {
			this.number = number;
		}
		@Override
		public void run() {
			super.run();
			Cursor cursor = mImpUserRegistration.getDiary(number);
			while (cursor.moveToNext()) {
				companyName = cursor.getString(cursor
						.getColumnIndex(KEY_COMPANYNAME));
			}
			mHandler.obtainMessage(MSG_INIT, companyName).sendToTarget();
		}
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		postiont=position;
		mAdapter.setIndex(postiont);
		mAdapter.notifyDataSetChanged();			
	}
public void AlertDialog(final int position) {
		Builder builder = new AlertDialog.Builder(ScanningActivity.this);
		builder.setTitle("确定是否删除");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			    if (postiont==-1) {
    				return;
    			}
    		if (mList.size()>0) {
    					if(mList.contains(mList.get(postiont))){
    						mList.remove(mList.get(postiont));
    					}
    					packettotal.setText(mList.size()+"");
    					}
    			if (mDatas.size()>0) {
    				mImpDataUpload.deleteInformation(mDatas.get(position).getNumber());
    				mDatas.remove(postiont);
    				postiont=mDatas.size()-1;	
    				ScannedNumber.setText(mDatas.size()+"");
    				mAdapter.setIndex(0);
    				mAdapter.notifyDataSetChanged();
    			}
			}
		});
		builder.setNegativeButton("取消", null);
		android.app.AlertDialog dialog = builder.create();
		dialog.show();
	}

//public void Dialog(){
//	Builder builder = new AlertDialog.Builder(ScanningActivity.this);
//	builder.setTitle("确定是否删除");
//	TextView textView=new TextView(this);
//		textView.setText("不能重复扫描！");
//	builder.setView(textView);
//	builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//
//		@Override
//		public void onClick(DialogInterface dialog, int which) {
//			mImpDataUpload.deleteInformation(mContent.getText().toString());
//			if (mDatas.size()>0) {
//				Iterator<ListData>it=mDatas.iterator();
//					while (it.hasNext()) {
//						ListData mListData=	it.next();
//						if (mListData.getNumber().equals(mContent.getText()+"")) {
//								it.remove();				
//						}	
//					}			
//			}
//			Iterator<ListData> rs=mList.iterator();
//			if (mList.size()>0) {
//				while(rs.hasNext()){
//					ListData mListData=	rs.next();
//					if (mListData.getNumber().equals(mContent.getText()+"")) {
//						rs.remove();
//					}
//				}
//				packettotal.setText(mList.size()+""+"/0");
//				ScannedNumber.setText(mDatas.size()+"");
//			}
//			mContent.setText("");
//			IntentFilter filter = new IntentFilter(ACTION_DATA_AVAILABLE);
//			registerReceiver(receiver, filter);
//		}
//	});
//	builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//		
//		@Override
//		public void onClick(DialogInterface dialog, int which) {
//			IntentFilter filter = new IntentFilter(ACTION_DATA_AVAILABLE);
//			registerReceiver(receiver, filter);
//			
//		}
//	});
//	android.app.AlertDialog dialog = builder.create();
//	dialog.show();
//}

@Override
public void onClick(View v) {
if (v.getId()==R.id.backbutton) {
	if (mList.size()==0) {
		finish();
	}else {
		NormalDialogStyleOne();
	}
	
}else if(v.getId()==R.id.adddata){
	BarcodeVeriFication(number);
}else if(v.getId()==R.id.deletedata){
	AlertDialog(postiont);
}
	
}
public void PacketDataupload(String xml){
		start();
	 mImageView.setVisibility(View.VISIBLE);
	flag=false;
	HttpUtils mHttpUtils = new HttpUtils(5000);
	RequestParams mRequestParams = new RequestParams();
	try {
		mRequestParams.setBodyEntity(new StringEntity(xml,"utf-8"));
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	mHttpUtils.send(HttpMethod.POST, URL_INTENT,mRequestParams,
			new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					Log.i("onSuccessshangchuan",arg1);
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					
					Log.i("onSuccessshangchuan", arg0.result);
						//xml解析
					XmlPersor( arg0.result);
				}

				
			});	
}
public  void BarcodeVeriFication(final String number){
	mSoundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
	String number_res = "";
	String BatchNumber="";// 批次号
	if (mnative.CheckBarcode(number, 6) == 1) {
		if (TextUtils.isEmpty(largeSizePackage.getText()+"")) {
			lookinformation(number);
			if (TextUtils.isEmpty(alreadlyNextstation)&&TextUtils.isEmpty(alreadlyNextcode)) {
				bigNumber=number;
				largeSizePackage.setText(bigNumber);
				nextStation.setText("");
				mContent.setText("");
				waybillBumber.setText("");	
			}else {
				bigNumber=number;
				largeSizePackage.setText(bigNumber);
				nextStation.setText(alreadlyNextcode);
				mContent.setText("");
				waybillBumber.setText(alreadlyNextstation);
				alreadlyNextstation="";
				alreadlyNextcode="";
			}			
		}else {
			unregisterReceiver(receiver);
			 final NormalDialog dialog = new NormalDialog(ScanningActivity.this);
			    dialog.content("大包号已锁定是否切换")//
			            .showAnim(new BounceTopEnter())//
			            .dismissAnim(new SlideBottomExit())//
			            .show();
			    dialog.setOnBtnClickL(
			            new OnBtnClickL() {
			                @Override
			                public void onBtnClick() {
			                	dialog.dismiss();
			                	IntentFilter filter = new IntentFilter(ACTION_DATA_AVAILABLE);
			            		registerReceiver(receiver, filter);
			                }
			            },
			            new OnBtnClickL() {
			                @SuppressLint("ShowToast")
							@Override
			                public void onBtnClick() {
			                	lookinformation(number);
			        			if (TextUtils.isEmpty(alreadlyNextstation)&&TextUtils.isEmpty(alreadlyNextcode)) {
			        				bigNumber=number;
			        				largeSizePackage.setText(bigNumber);
			        				nextStation.setText("");
			        				mContent.setText("");
			        				waybillBumber.setText("");	
			        			}else {
			        				bigNumber=number;
			        				largeSizePackage.setText(bigNumber);
			        				nextStation.setText(alreadlyNextcode);
			        				mContent.setText("");
			        				waybillBumber.setText(alreadlyNextstation);
			        				alreadlyNextstation="";
			        				alreadlyNextcode="";
			        			}	
			        			IntentFilter filter = new IntentFilter(ACTION_DATA_AVAILABLE);
			        			registerReceiver(receiver, filter);
			                	dialog.dismiss();

			                }
			            });
		}
		
	}else if (mnative.CheckBarcode(number, 2) == 1) {
		if (!TextUtils.isEmpty(largeSizePackage.getText().toString())) {
			nextStation.setText(number);// 给下一站输入框赋这个6位数的条码值
			new MyThread(number).start();// 开启线程查询数据库，根据6位数站点编号的条码值查询出对应的下一站地址
		} else {
			Toast.makeText(getApplicationContext(), "请先扫描大包号",
					Toast.LENGTH_SHORT).show();
		}
	}else if (!number.startsWith("900")&&number.length()>6) {
if (!TextUtils.isEmpty(largeSizePackage.getText().toString())&& largeSizePackage.getText().toString().length() == 13&& 
nextStation.getText().toString().length() == 6&& !TextUtils.isEmpty(waybillBumber.getText().toString())) {
			int ret = mnative.cutCode(number);
			if (ret == 0) {
				switch (number.length()) {
				case 13:
					number_res = number;
					break;
				case 18:
					number_res = number.substring(0, 13);
					if (number_res.startsWith("36")
							|| number_res.startsWith("37")
							|| number_res.startsWith("38")
							|| number_res.startsWith("39")) {
						BatchNumber = number.substring(13, 17);
						Log.d("cutCode批次=18", BatchNumber + "////");
					} else {
						BatchNumber = number.substring(13, 17);
						Log.d("cutCode批次=18", BatchNumber + "////");
					}
					break;
				case 24:
					number_res = number.substring(0, 13);
					BatchNumber = number.substring(13, 17);
					Log.d("cutCode批次=24", BatchNumber + "////");
					break;
				}
				awbNumber=number_res;
				mContent.setText(awbNumber);
				mBagPackageNumber.setTime(getDate());
				mBagPackageNumber.setUser(getIntent().getStringExtra(
						"userID"));
				time = getDate();
		
				int a =(int) mImpDataUpload.createDataUpload(bigNumber
				,awbNumber, getDates(), 0,userName,waybillBumber.getText().toString(),BatchNumber,nextStation.getText().toString());
	Log.i("getApplicationContext", BatchNumber+"  "+bigNumber+" = "+awbNumber+"  "+getDates()+"  "+waybillBumber.getText().toString());
				mListData=new ListData();
				mListData.setBigNumber(bigNumber);
				mListData.setNumber(awbNumber);
				mListData.setBatchNumber(BatchNumber);
				mListData.setNextStation(nextStation.getText().toString());
				mDatas.add(0, mListData);
				mList.add(mListData);
				Log.i("getApplicationContext", mListData.getNumber()+"条码批次号="+mListData.getBatchNumber());
				packettotal.setText(mList.size()+"");
				packettotal.setTextColor(Color.RED);
				ScannedNumber.setText(mDatas.size()+"");
				mAdapter.notifyDataSetChanged();
				if (mList.size()%10==0&&flag==true) {
				PacketDataupload(new PacketDataXml(mList, getIntent().getStringExtra("conpanyCode"), getIntent().getStringExtra("userID"), getIntent().getStringExtra("professionID"), getDate(), getIntent().getStringExtra("passWord")).toString());
				}				
			}
			}else{
				Toast.makeText(getApplicationContext(), "请先扫描打包号接着扫描下一站",
						Toast.LENGTH_SHORT).show();	
} 
		}else {
			Toast.makeText(getApplicationContext(), "扫描错误！",
					Toast.LENGTH_SHORT).show();	
		} 	
}
public static boolean isNetAvailable(Context context) {
	ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
    NetworkInfo info = manager.getActiveNetworkInfo();
    return (info != null && info.isAvailable());
}
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
switch (keyCode) {
	case 131://F1再次上传	
		Log.i("isNetAvailable", "isNetAvailable = "+isNetAvailable(ScanningActivity.this));
		if(!isNetAvailable(ScanningActivity.this)){
			Log.i("isNetAvailable", "网络不通，请稍候上传");
			Toast.makeText(ScanningActivity.this, "网络不通，请稍候上传", 1000).show();
			return true;
		}
		if (mList.size()>0&&flag==true) {
			PacketDataupload(new PacketDataXml(mList, getIntent().getStringExtra("conpanyCode"), getIntent().getStringExtra("userID"), getIntent().getStringExtra("professionID"), getDate(), getIntent().getStringExtra("passWord")).toString());		
		}
break;
	case 132://F2锁屏
		Intent w16Intent=new Intent();
		ComponentName mName=new ComponentName("com.example.hellojni", "com.example.hellojni.HelloJni");
		w16Intent.setComponent(mName);
		w16Intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(w16Intent);
	break;
	case 133://F3解锁
		Intent wifiIntent=new Intent();
		wifiIntent.setAction("android.settings.WIFI_SETTINGS");
		startActivity(wifiIntent);		
		break;
	case KeyEvent.KEYCODE_BACK:
		return true;
}
Log.i("onKeyDown", keyCode+"");
	return super.onKeyDown(keyCode, event);
}
private void XmlPersor(String result) {
	XmlPullParser parser = Xml.newPullParser();
	String st = null,res = null;
	try {
		parser.setInput(new ByteArrayInputStream(result.getBytes("UTF-8")), "UTF-8");
		int eventType =parser.getEventType();
		while (eventType!=XmlPullParser.END_DOCUMENT) {
			if (eventType==XmlPullParser.START_TAG) {
				if ("dta".equals(parser.getName())) {
					st=parser.getAttributeValue(0);
					res=parser.getAttributeValue(1);
				}
			}
		 eventType = parser.next();	
		}	
 if (st.equals("ok")) {
	 //Toast.makeText(this, "成功上传"+res+"条", Toast.LENGTH_SHORT).show();
	 int resultttt =Integer.parseInt(res);
	 Iterator<ListData> it=mList.iterator();
	 while (it.hasNext()&&resultttt>0) {
		ListData mData=it.next();
		mImpDataUpload. updateInformation(mData.getNumber(),1);	
		it.remove();	
	 	resultttt--;	
	}
	packettotal.setText(mList.size()+"");
	packettotal.setTextColor(Color.BLACK);
		stop();
	 mImageView.setVisibility(View.GONE);
	 flag=true;
 }
 } catch (Exception e) {
	e.printStackTrace();
}	
}
private void NormalDialogStyleOne() {
    final NormalDialog dialog = new NormalDialog(ScanningActivity.this);
    dialog.content("当前有未上传完的数据，禁止退出，点确定继续上传")//
            .showAnim(new BounceTopEnter())//
            .dismissAnim(new SlideBottomExit())//
            .show();
    dialog.setOnBtnClickL(
            new OnBtnClickL() {
                @Override
                public void onBtnClick() {
                    finish();
                }
            },
            new OnBtnClickL() {
                @SuppressLint("ShowToast")
				@Override
                public void onBtnClick() {
                	if(!isNetAvailable(ScanningActivity.this)){
        				Toast.makeText(ScanningActivity.this, "网络不通，请稍候上传", 1000).show();
        				return;
        			}
                	if (mList.size()>0&&flag==true) {
            			PacketDataupload(new PacketDataXml(mList, getIntent().getStringExtra("conpanyCode"), getIntent().getStringExtra("userID"), getIntent().getStringExtra("professionID"), getDate(), getIntent().getStringExtra("passWord")).toString());		
					}
            
        	dialog.dismiss();
                 
                }
            });
}
public void lookinformation(final String bigNumber){
	new Thread(){
		@Override
		public void run() {
			super.run();
			List<PacketInforMation> pt=mImpDataUpload.getInformation(bigNumber);
			Iterator<PacketInforMation>	it=pt.iterator();
			while (it.hasNext()) {
				PacketInforMation mp=it.next();
				alreadlyNextstation=mp.getNextstationName();
				alreadlyNextcode=mp.getNextstation();
			}
		}
	}.start();
}
/**
 * 开始播放
 */
	protected void start() {
	if (frameAnim != null && !frameAnim.isRunning()) {
		frameAnim.start();
	}
}
/**
 * 停止播放
 */
protected void stop() {
	if (frameAnim != null && frameAnim.isRunning()) {
		frameAnim.stop();
	}
}
}
