package com.yunda.activity.cn;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.http.entity.StringEntity;
import org.xmlpull.v1.XmlPullParser;
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
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yunda_app_2016_3_18.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yunda.imp.cn.ImpReturnPartData;
import com.yunda.untils.MyApplication;
import com.yunda.untils.ReturnPart;
import com.yunda.untils.ReturnPartXml;
import com.yunda.xlistview.cn.ReturnPartAdapter;
import com.yundaW16setting.cn.MyNative;
import com.yundadialog.BounceTopEnter;
import com.yundadialog.NormalDialog;
import com.yundadialog.OnBtnClickL;
import com.yundadialog.SlideBottomExit;
public class ReturnPartActivity extends Activity implements OnClickListener ,OnItemClickListener{
private Button mAddButton;//添加
private Button deleteButton;//删除
private EditText mWbillEditText;//运单号
private EditText mTotalEditText;//当前扫描数
private Button mFinishButton;//返回键
public final static String ACTION_DATA_AVAILABLE = "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
public final static String EXTRA_DATA = "com.example.bluetooth.le.EXTRA_DATA";
private String number;
private MyNative mnative ;
private String BatchNumber="";// 批次号
private ReturnPart mReturnPart;
private List<ReturnPart>  mList;
private ReturnPartAdapter mAdapter;
private ListView mListView;
private int index;
private ImpReturnPartData mImpReturnPartData;
private static String URL_INTENT="";//= "http://opws.yundasys.com:9900/ws/ws.jsp"
private String userID;//用户id
private String profession;//工种名称
private String professionID;//工种id
private String passWord;//用户密码
private String conpanyCode;//当前站点
private String name;//登录人
private long a;
int tatals = 0;/////////////////
private String s;
private TextView mDataReturn,updataTotal;
private String d;
private SoundPool mSoundPool = null;
private boolean flag=true;
List<ReturnPart> dataList1=new ArrayList<ReturnPart>();
private MyApplication mApplication;
private ImageView imageView1;
private AnimationDrawable frameAnim;
private SharedPreferences ps;
HashMap<Integer, Integer> soundMap = new HashMap<Integer, Integer>();
@SuppressLint("UseSparseArrays")
private Handler mHandler=new Handler(){
	public void handleMessage(Message msg) {
		switch (msg.what) {
			case 1:
			 d=(String) msg.obj;
			break;
			case 2:
				mReturnPart= (ReturnPart) msg.obj;
				//s=mReturnPart.getNumber();
				Log.i("handleMessage", s);	
			break;
		}
	};
};
private BroadcastReceiver receiver=new BroadcastReceiver() {
	
	
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action == ACTION_DATA_AVAILABLE) {
			
			number = intent.getStringExtra(EXTRA_DATA);
			Log.i("number", number.length() + "");
		}
		BarcodeVeriFication(number);
		
	}
};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//取消状态栏   
		  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
		    WindowManager.LayoutParams.FLAG_FULLSCREEN); 
		  setContentView(R.layout.returnpart);
		  getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		  findViewById();
	}
	private void findViewById(){
		  mAddButton=(Button) findViewById(R.id.addbutton);
		  deleteButton=(Button) findViewById(R.id.delete);
		  mWbillEditText=(EditText) findViewById(R.id.wbilltext);
		  mTotalEditText=(EditText) findViewById(R.id.totaltext);
		  mListView=(ListView) findViewById(R.id.retulist);
		  mDataReturn=(TextView) findViewById(R.id.returndataload);
		  mFinishButton=(Button) findViewById(R.id.back);
		  updataTotal=(TextView) findViewById(R.id.total);
		  mAddButton.setOnClickListener(this);
		  deleteButton.setOnClickListener(this);
		  mFinishButton.setOnClickListener(this);
		  mnative = new MyNative();
		  mList=new ArrayList<ReturnPart>();
		  mAdapter=new ReturnPartAdapter(this, mList);
		  mListView.setAdapter(mAdapter);
		  mListView.setOnItemClickListener(this);
		  mImpReturnPartData=new ImpReturnPartData(this);
		  userID=getIntent().getStringExtra("userID");
		  profession=getIntent().getStringExtra("profession");
		  professionID=getIntent().getStringExtra("professionID");
		  passWord=getIntent().getStringExtra("passWord");
		  conpanyCode=getIntent().getStringExtra("conpanyCode");
		  name=getIntent().getStringExtra("name");
		  mSoundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
		  soundMap.put(1 , mSoundPool.load(this, R.raw.test_2k_8820_200ms , 1));
		  soundMap.put(2 , mSoundPool.load(this, R.raw.error , 1));
		  mDataReturn.setText(dataList1.size()+"");
		  updataTotal.setText(tatals+"");
		  mAdapter.setIndex(0);
		  mApplication=(MyApplication) getApplication();
		  imageView1=(ImageView) findViewById(R.id.imageV1);
		  frameAnim=(AnimationDrawable) getResources().getDrawable(R.drawable.bullet_anim);
		  imageView1.setBackgroundDrawable(frameAnim);
		  imageView1.setVisibility(View.GONE);
		  ps=getSharedPreferences("host", MODE_PRIVATE);
		  URL_INTENT=ps.getString("datadend","http://qz.yundasys.com:9900/ws/ws.jsp");
		  Log.i("URL_INTENT", URL_INTENT);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.addbutton:
			if (!TextUtils.isEmpty(mWbillEditText.getText()+"")) {
				BarcodeVeriFication(mWbillEditText.getText()+"");		
			}
			break;
		case R.id.delete:
			if(mList.size()>0){
				final NormalDialog dialog = new NormalDialog(this);
		        dialog.content("真的要删除此条数据吗?(●—●)")//
		                .style(NormalDialog.STYLE_TWO)//
		                .titleTextSize(23)//
		                .btnText("取消删除", "残忍删除")//#383838" #D4D4D4
		                .btnTextColor(Color.parseColor("#383838"), Color.parseColor("#383838"))//
		                .btnTextSize(16f, 16f)//
		                .showAnim(new BounceTopEnter())//
		                .dismissAnim( new SlideBottomExit())//
		                .show();

		        dialog.setOnBtnClickL(
		                new OnBtnClickL() {
		                    @Override
		                    public void onBtnClick() {
		                        dialog.dismiss();
		                    }
		                },
		                new OnBtnClickL() {
		                    @Override
		                    public void onBtnClick() {
		                        dialog.superDismiss();
		                        if (index==-1) {
		            				return;
		            			}
		            		if (dataList1.size()>0) {
		            					if(dataList1.contains(mList.get(index))){
		            						dataList1.remove(mList.get(index));
		            					}
		            					mDataReturn.setText(dataList1.size()+"");
		            					}
		            			if (mList.size()>0) {
		            				mImpReturnPartData.deleteInformation(mList.get(index).getNumber());
		            				mList.remove(index);
		            				index=mList.size()-1;	
		            				
		            				mTotalEditText.setText(mList.size()+"");
		            				mAdapter.setIndex(0);
		            				mAdapter.notifyDataSetChanged();
		            			}
		            			dialog.dismiss();
		                    }
		                });	
			}			
			break;
		case R.id.back:
			if (dataList1.size()==0) {
				finish();
			}else {
				NormalDialogStyleOne();
			}
			break;	
		}
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
			mApplication.setmList(dataList1);
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
	@SuppressLint("SimpleDateFormat")
	public String getDates() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		return time;
	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		index=position;
		mAdapter.setIndex(index);
		mAdapter.notifyDataSetChanged();
	}
//	public void Dialog(){
//		Builder builder = new AlertDialog.Builder(ReturnPartActivity.this);
//		builder.setTitle("确定是否删除");
//		TextView textView=new TextView(this);
//			textView.setText("重复扫描！");
//		builder.setView(textView);
//		builder.setCancelable(false);
//		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//					mImpReturnPartData.deleteInformation(mWbillEditText.getText()+"");
//					if (mList.size()>0) {
//					Iterator<ReturnPart>it=mList.iterator();
//						while (it.hasNext()) {
//							ReturnPart mReturnPart=	it.next();
//							if (mReturnPart.getNumber().equals(mWbillEditText.getText()+"")) {
//									it.remove();				
//							}	
//						}			
//				}
////					现在退出后不会再次上传原本的数据，
////					应未上传时候允许在询问后做退出到界面，退出后可以让未上传数据能手动上传。
////					不能强制留在扫描界面的原因是，万一遇到无线不行可以切换网络后继续上传
//					Iterator<ReturnPart> rs=dataList1.iterator();
//					if (dataList1.size()>0) {
//						while(rs.hasNext()){
//							ReturnPart Part=rs.next();
//							if (Part.getNumber().equals(mWbillEditText.getText()+"")) {
//								rs.remove();
//							}
//						}
//						mDataReturn.setText(+dataList1.size()+"");
//					}
//				mWbillEditText.setText("");
//				mTotalEditText.setText(mList.size()+"");
//				mAdapter.setIndex(0);
//				mAdapter.notifyDataSetChanged();
//				IntentFilter filter = new IntentFilter(ACTION_DATA_AVAILABLE);
//        		registerReceiver(receiver, filter);	
//			}
//		});
//		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//			
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				IntentFilter filter = new IntentFilter(ACTION_DATA_AVAILABLE);
//				registerReceiver(receiver, filter);
//				
//			}
//		});
//		android.app.AlertDialog dialog = builder.create();
//		dialog.show();
//	}
	private void NormalDialogStyleOne() {
	    final NormalDialog dialog = new NormalDialog(ReturnPartActivity.this);
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
	                	if(!isNetAvailable(ReturnPartActivity.this)){
	        				Toast.makeText(ReturnPartActivity.this, "网络不通，请稍候上传", 1000).show();
	        				return;
	        			}
	                	if (dataList1.size()>0&&flag==true) {
	        	  ReturnPartDataupload(new ReturnPartXml(dataList1, conpanyCode, userID, professionID, getDate(), passWord).toString());
						}
	            
	        	dialog.dismiss();
	                 
	                }
	            });
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
			Cursor cursor = mImpReturnPartData.getDiary(number);
			while (cursor.moveToNext()) {
				companyName = cursor.getString(cursor
						.getColumnIndex(KEY_COMPANYNAME));
			}

			mHandler.obtainMessage(1, companyName).sendToTarget();
			
		}
	}
@SuppressLint("ShowToast")
public synchronized void ObjectAssignment(String number,String time,String BatchNumber){
	mWbillEditText.setText(number);
	new MyThread(conpanyCode).start();	
a=mImpReturnPartData.createReturnPartData(userID,professionID,profession, number, getDate() , 0, name, d, getDates(),BatchNumber);
	mSoundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
	mReturnPart=new ReturnPart();
	mReturnPart.setNumber(number);
	mReturnPart.setTime(time);
	mReturnPart.setFlag(0);
	mReturnPart.setBatchNumber(BatchNumber);
	mList.add(0,mReturnPart);
	dataList1.add(mReturnPart);
	mAdapter.notifyDataSetChanged();
	mWbillEditText.setText("");
	mTotalEditText.setText(mList.size()+"");
	mDataReturn.setText(dataList1.size()+"");
	mDataReturn.setTextColor(Color.RED);
if (dataList1.size()% 10==0&&flag==true) {
	//dataList1.clear();
	if(!isNetAvailable(ReturnPartActivity.this)){
		Toast.makeText(ReturnPartActivity.this, "网络不通，请稍候上传", 1000).show();
		return;
	}
	ReturnPartDataupload(new ReturnPartXml(dataList1, conpanyCode, userID, professionID, getDate(), passWord).toString());		         
}
//		if (a!=-1) {
//			
//		}else {
//		mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
//			Dialog();
//			unregisterReceiver(receiver);
//		}
	}
/*
 * 条码校验
 */
public  void BarcodeVeriFication(String number){
	int ret = mnative.cutCode(number);
	String number_res = "";
	Log.i("number", ret+ "");
	switch (ret) {
	case 0:
		switch (number.length()) {
		case 13:
			number_res = number;
			BatchNumber="";
			ObjectAssignment(number_res, getDate(),BatchNumber);
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
			ObjectAssignment(number_res, getDate(),BatchNumber);
			break;
		case 24:
			number_res = number.substring(0, 13);
			BatchNumber = number.substring(13, 17);
			ObjectAssignment(number_res, getDate(),BatchNumber);
			Log.d("cutCode批次=24", BatchNumber + "////");
			break;
			default:
				Toast.makeText(ReturnPartActivity.this, "条码不符合规则", Toast.LENGTH_SHORT).show();	
			break;
		}
		break;
	case 1:
		Toast.makeText(ReturnPartActivity.this, "条码不符合规则", Toast.LENGTH_SHORT).show();
		break;
	}
}
private  void XmlPersor(String result) {
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
		int q=Integer.parseInt(res);
		tatals+=q;
	// updataTotal.setText(tatals+"");
	 Iterator<ReturnPart> itt=dataList1.iterator();
	 int resultttt =Integer.parseInt(res);
	 while (itt.hasNext()&&resultttt>0) {
		 	ReturnPart mReturnPart2=itt.next();
		 	mImpReturnPartData.updateInformation(mReturnPart2.getNumber(), 1);	
		 	itt.remove();	
		 	resultttt--;	 
		 
	 }
		flag=true;
		stop();
		 imageView1.setVisibility(View.GONE);
	 mDataReturn.setText(dataList1.size()+"");
	 mDataReturn.setTextColor(Color.BLACK);
 }
 } catch (Exception e) {
	e.printStackTrace();
}	
}
public static boolean isNetAvailable(Context context) {
	ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
    NetworkInfo info = manager.getActiveNetworkInfo();
    return (info != null && info.isAvailable());
}
@SuppressLint("ShowToast")
@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	switch (keyCode) {
		case 131://F1再次上传	
			Log.i("isNetAvailable", "isNetAvailable = "+isNetAvailable(ReturnPartActivity.this));
			if(!isNetAvailable(ReturnPartActivity.this)){
				Log.i("isNetAvailable", "网络不通，请稍候上传");
				Toast.makeText(ReturnPartActivity.this, "网络不通，请稍候上传", 1000).show();
				return true;
			}
			if (dataList1.size()>0&&flag==true) {
		ReturnPartDataupload(new ReturnPartXml(dataList1, conpanyCode, userID, professionID, getDate(), passWord).toString());		
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
public void ReturnPartDataupload(final String xml){
	imageView1.setVisibility(View.VISIBLE);
	 start();
	flag=false;
	Log.i("pxt","xml=" +xml);
	HttpUtils mHttpUtils = new HttpUtils(5000);
	RequestParams mRequestParams = new RequestParams();
	try {
		mRequestParams.setBodyEntity(new StringEntity(xml,"utf-8"));
	} catch (UnsupportedEncodingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	mHttpUtils.send(HttpMethod.POST, URL_INTENT,mRequestParams,
			new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					try {
						Thread.sleep(1000);
						ReturnPartDataupload(xml);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}				
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					
					Log.i("onSuccessshangchuan", arg0.result);
					
						//xml解析
					XmlPersor(arg0.result);
				}
			});	
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

