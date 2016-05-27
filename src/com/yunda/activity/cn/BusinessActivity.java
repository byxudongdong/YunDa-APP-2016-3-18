package com.yunda.activity.cn;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.xmlpull.v1.XmlPullParser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yunda_app_2016_3_18.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yunda.imp.cn.ImpProfession;
import com.yunda.untils.HttpData;
import com.yunda.untils.HttpData.Datatotal;
import com.yunda.untils.ListData;
import com.yunda.untils.MD5transformation;
import com.yunda.untils.MyApplication;
import com.yunda.untils.PacketDataXml;
import com.yunda.untils.ReturnPart;
import com.yunda.untils.ReturnPartXml;
public class BusinessActivity extends Activity implements OnClickListener {
	private Button mSpecial_part_scan;// 特殊件扫描
	private Button mUnloading_scan; // 卸车扫描
	private Button mScan_to_scan;// 到件扫描
	private Button mScanning_of_hair;// 发件扫描
	private Button mPacket_scanning;// 集包扫描
	private Button mLoading_scan;// 装车扫描
	private Button mReflow_scan;// 回流件扫描
	private Button mVehicle_scheduling;// 回流件扫描
	private Button mBack_button;// 返回
	private String userName;
	private String passWord;
	private String conpanyCode;
	private String androidId;
	private ImpProfession mProfession;
	private SharedPreferences ps;
	private int h;
	public static final String KEY_CODENUMBER = "codenumber";
	public static final String KEY_PROFESSION = "profession";
	private static final String URL_INTENT = "http://opws.yundasys.com:9900/ws/ws.jsp";
	private EditText mEditText;
	private String name;
	private String time;
	private TextView totaldatas;
	//private List<?> mList;
	//private MyApplication application;
	private ImageView imageView1;
	private AnimationDrawable frameAnim;
	int REQUEST_CODE = 1;
	//private String profession="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//取消状态栏   
		  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
		    WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.specialscanactivity);
		// 初始化
		findViewById();
		Intent intent = getIntent();
		userName = intent.getStringExtra("username");
		passWord = intent.getStringExtra("password");
		conpanyCode = intent.getStringExtra("comPanyCode");
		androidId = intent.getStringExtra("androidId");
		name = intent.getStringExtra("name");
		time = intent.getStringExtra("time");
		ps = getSharedPreferences("sp", MODE_PRIVATE);
		if (ps.getInt("a", 0) == 0) {
			Download(userName, passWord, conpanyCode, androidId);
		};
		Query();
	}
	@Override
	protected void onStart() {
		super.onStart();
		totaldatas.setText(0+""+"/0");
//		if (application.getmList()!=null) {	
//			mList=application.getmList();
//			totaldatas.setText(mList.size()+""+"/0");	
//		}
	}
	private void findViewById() {
		mSpecial_part_scan = (Button) findViewById(R.id.Special_part_scan);
		totaldatas=(TextView) findViewById(R.id.toataldatass);
		mUnloading_scan = (Button) findViewById(R.id.Unloading_scan);
		mScan_to_scan = (Button) findViewById(R.id.Scan_to_scan);
		mScanning_of_hair = (Button) findViewById(R.id.Scanning_of_hair);
		mPacket_scanning = (Button) findViewById(R.id.Packet_scanning);
		mLoading_scan = (Button) findViewById(R.id.Loading_scan);
		mReflow_scan = (Button) findViewById(R.id.Reflow_scan);
		mVehicle_scheduling = (Button) findViewById(R.id.Vehicle_scheduling);
		mBack_button = (Button) findViewById(R.id.Back_button);
		mSpecial_part_scan.setOnClickListener(this);
		mUnloading_scan.setOnClickListener(this);
		mScan_to_scan.setOnClickListener(this);
		mScanning_of_hair.setOnClickListener(this);
		mPacket_scanning.setOnClickListener(this);
		mLoading_scan.setOnClickListener(this);
		mReflow_scan.setOnClickListener(this);
		mVehicle_scheduling.setOnClickListener(this);
		mBack_button.setOnClickListener(this);
		mProfession = new ImpProfession(getApplicationContext());
	//	application=(MyApplication) getApplication();
	 	imageView1=(ImageView) findViewById(R.id.im1);
		  frameAnim=(AnimationDrawable) getResources().getDrawable(R.drawable.bullet_anim);
		  imageView1.setBackgroundDrawable(frameAnim);
		  imageView1.setVisibility(View.GONE);
	}
	
	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.Special_part_scan:
			mEditText = new EditText(this);
			AlertDialog("101", mSpecial_part_scan.getText().toString());
			break;
		case R.id.Unloading_scan:
			mEditText = new EditText(this);
			AlertDialog("102", mUnloading_scan.getText().toString());
			break;
		case R.id.Scan_to_scan:
			mEditText = new EditText(this);
			AlertDialog("103", mScan_to_scan.getText().toString());
			break;
		case R.id.Scanning_of_hair:
			mEditText = new EditText(this);
			AlertDialog("104", mScanning_of_hair.getText().toString());
			break;
		case R.id.Packet_scanning:
			mEditText = new EditText(this);
			AlertDialog("106", mPacket_scanning.getText().toString());
			break;
		case R.id.Loading_scan:
			mEditText = new EditText(this);
			AlertDialog("107", mLoading_scan.getText().toString());
            // startActivity(new Intent(BusinessActivity.this,Truckloading.class));  
			break;
		case R.id.Reflow_scan:
			mEditText = new EditText(this);
			AlertDialog("108", mReflow_scan.getText().toString());
			break;
		case R.id.Vehicle_scheduling:
			mEditText = new EditText(this);
			AlertDialog("109", mVehicle_scheduling.getText().toString());
//			Intent nIntent = new Intent(BusinessActivity.this,
//					VehicleSchedulingActivity.class);
//			startActivity(nIntent);
			break;
		case R.id.Back_button:
			finish();
			break;

		}
	}

	public void Download(String userName, String passWord, String conpanyCode,
			String androidId) {

		byte[] b = new byte[1024];
		InputStream mInput = null;
		Log.i("xmlInfoParaseModel", "xml");
		try {
			mInput = getResources().getAssets().open("gongzhonglist.xml");
			mInput.read(b);
			mInput.close();
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("xmlInfoParaseModel", e.toString());
		}
		String xml = new String(b)
				.trim()
				.replace("mcompany", conpanyCode)
				.replace("muser", userName)
				.replace("mpass",
						new MD5transformation().MD5(passWord))
				.replace("mdev1", androidId).replace("mdev2", "1009");
		Log.i("xmlInfoParaseModel", xml);
		HttpUtils mHttpUtils = new HttpUtils(5000);
		RequestParams mRequestParams = new RequestParams();
		try {
			mRequestParams.setBodyEntity(new StringEntity(xml, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		mHttpUtils.send(HttpMethod.POST, URL_INTENT, mRequestParams,
				new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(BusinessActivity.this, "网络异常请检查网络！",
								Toast.LENGTH_SHORT).show();

					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						Log.i("xmlInfoParaseModel", arg0.result);
						xmlInfoParaseModel(arg0.result);
					}

				});
	}

	protected void xmlInfoParaseModel(String result) {
		XmlPullParser parser = Xml.newPullParser();
		try {
			parser.setInput(new ByteArrayInputStream(result.getBytes("UTF-8")),
					"UTF-8");
			int eventType = parser.getEventType();
			String d1 = null;
			String d0 = null;
			while (eventType != XmlPullParser.END_DOCUMENT) {
				if (eventType == XmlPullParser.START_TAG) {
					if ("o".equals(parser.getName())) {
						parser.next();
						for (int i = 0; i < 2;) {
							if (i == 0) {
								d0 = parser.nextText();
							} else if (i == 1) {
								d1 = parser.nextText();
							}
						}

						int a = (int) mProfession.create(d1, d0);
						Log.i("mImpUser", d0 + "=" + d1 + "=" + a + "");
					}

				}
				eventType = parser.next();
			}
			h = 1;
			ps.edit().putInt("a", h).commit();
		} catch (Exception e) {
			e.printStackTrace();
			Log.i("mImpUser", e.toString());
		}
	}

	// 利用mHashMap将从数据库查出的工种一一适配到界面
	public void Query() {
		Map<String, String> mHashMap = new HashMap<>();
		Cursor mCursor = mProfession.QueryProfession();
		String a;
		String b;
		while (mCursor.moveToNext()) {
			a = mCursor.getString(mCursor.getColumnIndex(KEY_CODENUMBER));
			b = mCursor.getString(mCursor.getColumnIndex(KEY_PROFESSION));
			mHashMap.put(a, b);
			Log.i("mHashMap", mHashMap.size() + "");
		}
		Iterator it = mHashMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String m = (String) entry.getKey();
			String g = (String) entry.getValue();
			if (m.equals("101") && g.equals("特殊件扫描")) {
				mSpecial_part_scan.setText(m + "| " + g);
			}
			if (m.equals("102") && g.equals("卸车扫描")) {
				mUnloading_scan.setText(m + "|    " + g);
			}
			if (m.equals("103") && g.equals("到件扫描")) {
				mScan_to_scan.setText(m + "|    " + g);
			}
			if (m.equals("104") && g.equals("发件扫描")) {
				mSpecial_part_scan.setText(m + "|    " + g);
			}
			if (m.equals("106") && g.equals("集包扫描")) {
				mSpecial_part_scan.setText(m + "|    " + g);
			}
			if (m.equals("107") && g.equals("装车扫描")) {
				mSpecial_part_scan.setText(m + "|   " + g);
			}
			if (m.equals("108") && g.equals("回流件扫描")) {
				mSpecial_part_scan.setText(m + "|" + g);
			}
			if (m.equals("109") && g.equals("车辆调度")) {
				mSpecial_part_scan.setText(m + "|   " + g);
			}
		}

	}

	public void AlertDialog(final String number, final String profession) {
					Intent mIntent = new Intent(BusinessActivity.this,
							MainmenueActivity.class);
					mIntent.putExtra("name", name);
					mIntent.putExtra("time", time);
					mIntent.putExtra("userName", userName);
					mIntent.putExtra("number", number);
					mIntent.putExtra("conpanyCode", conpanyCode);
					mIntent.putExtra("profession", profession);
					mIntent.putExtra("mEditText", mEditText.getText()
							.toString());
					mIntent.putExtra("passWord", passWord);
					startActivity(mIntent);
			}
	
	public static boolean isNetAvailable(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
	    NetworkInfo info = manager.getActiveNetworkInfo();
	    return (info != null && info.isAvailable());
	}
	//获取当前时间
		public String getDate() {
			Date date = new Date();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(date);
			return time;
		}
	@SuppressWarnings("unchecked")
	@SuppressLint("ShowToast")
	@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
			case 131://F1再次上传
//				Log.i("isNetAvailable", "isNetAvailable = "+isNetAvailable(BusinessActivity.this));
//				if(!isNetAvailable(BusinessActivity.this)){
//					Log.i("isNetAvailable", "网络不通，请稍候上传");
//					Toast.makeText(BusinessActivity.this, "网络不通，请稍候上传", 1000).show();
//					return true;
//				}
//				switch (profession.trim()) {
//				case "特殊件扫描":
//					
//					break;
//
//				case "卸车扫描":
//					break;
//				case "到件扫描":
//					break;
//				case "发件扫描":
//					break;
//				case "集包扫描":
//					if (application.getmList()!=null) {
//						if (mList.size()>0) {
//							imageView1.setVisibility(View.VISIBLE);
//							start();
//							HttpData mData=new HttpData( application, (List<ListData>) mList,profession);
//							mData.ReturnPartDataupload(new PacketDataXml(application.getmList(), application.getConpanyCode(), application.getUserID(), application.getProfessionID(), getDate(), application.getPassWord()).toString());		
//							mData.setDatatotal(new Datatotal() {
//								
//								@Override
//								public void setData(int a) {
//									totaldatas.setText(a+""+"/0");
//									imageView1.setVisibility(View.GONE);
//									stop();
//								}
//							});	
//						}else {
//							Toast.makeText(this, "当前无数据上传！", Toast.LENGTH_SHORT).show();
//						}
//					}else {
//						Toast.makeText(this, "当前无数据上传！", Toast.LENGTH_SHORT).show();
//					}
//					break;
//				case "装车扫描":
//					break;
//				case "回流件扫描":
//					if (application.getmList()!=null) {
//						if (mList.size()>0) {
//							imageView1.setVisibility(View.VISIBLE);
//							start() ;
//							HttpData mData=new HttpData( application, (List<ReturnPart>) mList,profession);
//							mData.ReturnPartDataupload(new ReturnPartXml((List<ReturnPart>) application.getmList(), application.getConpanyCode(), application.getUserID(), application.getProfessionID(), getDate(), application.getPassWord()).toString());		
//							mData.setDatatotal(new Datatotal() {
//								
//								@Override
//								public void setData(int a) {
//									totaldatas.setText(a+""+"/0");	
//									stop();
//									imageView1.setVisibility(View.GONE);
//								}
//							});	
//						}else {
//							Toast.makeText(this, "当前无数据上传！", Toast.LENGTH_SHORT).show();
//						}
//					}else {
//						Toast.makeText(this, "当前无数据上传！", Toast.LENGTH_SHORT).show();
//					}
//					break;
//				case "车辆调度":
//					break;
//				}
//				


		break;
			case 132://F2光值配对
				Intent w16Intent=new Intent();
				ComponentName mName=new ComponentName("com.example.hellojni", "com.example.hellojni.HelloJni");
				w16Intent.setComponent(mName);
				w16Intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(w16Intent);
			break;
			case 133://F3wifi连接
				Intent wifiIntent=new Intent();
				wifiIntent.setAction("android.settings.WIFI_SETTINGS");
				startActivity(wifiIntent);	
				break;
		}
		Log.i("onKeyDown", keyCode+"");
		
			return super.onKeyDown(keyCode, event);
		}
	/**
	 * 开始播放
	 */
		protected void start() {
		if (frameAnim != null && !frameAnim.isRunning()) {
			frameAnim.start();
//			Toast.makeText(ReturnPartActivity.this, "开始播放", 0).show();
//			Log.i("main", "index 为5的帧持续时间为："+frameAnim.getDuration(5)+"毫秒");
//			Log.i("main", "当前AnimationDrawable一共有"+frameAnim.getNumberOfFrames()+"帧");
		}
	}
	/**
	 * 停止播放
	 */
	protected void stop() {
		if (frameAnim != null && frameAnim.isRunning()) {
			frameAnim.stop();
			//Toast.makeText(ReturnPartActivity.this, "停止播放", 0).show();
		}
	}
}
