package com.yunda.activity.cn;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.example.yunda_app_2016_3_18.R;
import com.yunda.imp.cn.ImpUserRegistration;
import com.yunda.untils.PacketInforMation;
import com.yunda.untils.ReturnPartXml;
import com.yundaW16setting.cn.MyNative;
import com.yundadialog.BounceTopEnter;
import com.yundadialog.NormalDialog;
import com.yundadialog.OnBtnClickL;
import com.yundadialog.SlideBottomExit;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoadingScanActivity extends Activity implements OnClickListener,OnCheckedChangeListener,OnItemClickListener,OnItemSelectedListener{
private CheckBox mCheckBox;//连接电子秤
private TextView mTextView;//显示连接成功还是失败；
private Spinner mSpinner;//显示货样非货样
private EditText mEditText;//装车位
private EditText fEditText;//发车凭证；
private EditText nextStation;//下一站；
private TextView sTextView;//站点
private EditText nEditText;//运单号
private EditText scanEditText;//已扫描数
private Button addButton;//添加
private Button deleteButton;//删除
private Button backButton;//返回
private ListView mListView;//显示数据
private static int MSG_INIT=1;
public final static String ACTION_DATA_AVAILABLE = "com.example.bluetooth.le.ACTION_DATA_AVAILABLE";
public final static String EXTRA_DATA = "com.example.bluetooth.le.EXTRA_DATA";
private String number;//接收到的条码
private MyNative mnative;
private ImpUserRegistration mImpUserRegistration;
private String DepartureCertificate="";//发车凭证
private String ParkingSpaces="";//装车位
private SoundPool mSoundPool = null;
HashMap<Integer, Integer> soundMap = new HashMap<Integer, Integer>();
private Handler mHandler = new Handler() {
	public void handleMessage(android.os.Message msg) {
		if (msg.what == MSG_INIT) {
			String s = (String) msg.obj;
			sTextView.setText(s);
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
			//条码校验
			BarcodeVeriFication(number);
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
		setContentView(R.layout.loadingscanactivity);
		//初始化
		findViewById();
	}

	private void findViewById() {
		mCheckBox=(CheckBox) findViewById(R.id.loadcheckbox);
		mTextView=(TextView) findViewById(R.id.loadTextView1);
		mSpinner=(Spinner) findViewById(R.id.loadspinner1);
		mEditText=(EditText) findViewById(R.id.loadeditText1);
		fEditText=(EditText) findViewById(R.id.loadEdit);
		nextStation=(EditText) findViewById(R.id.loadEditText);
		sTextView=(TextView) findViewById(R.id.loadTextView2);
		nEditText=(EditText) findViewById(R.id.loadaddbutton);
		addButton=(Button) findViewById(R.id.loadbutton1);
		deleteButton=(Button) findViewById(R.id.loaddeletebutton);
		backButton=(Button) findViewById(R.id.loadfinish);
		scanEditText=(EditText) findViewById(R.id.loadEditText4);
		mListView=(ListView) findViewById(R.id.loadlistview);
		mnative = new MyNative();
		addButton.setOnClickListener(this);
		deleteButton.setOnClickListener(this);
		backButton.setOnClickListener(this);
		mCheckBox.setOnCheckedChangeListener(this);
		mSpinner.setOnItemSelectedListener(this);
		mImpUserRegistration=new ImpUserRegistration(this);
		mSoundPool = new SoundPool(2, AudioManager.STREAM_SYSTEM, 5);
		soundMap.put(1 , mSoundPool.load(this, R.raw.test_2k_8820_200ms , 1));
		soundMap.put(2 , mSoundPool.load(this, R.raw.error , 1));
	}

	@Override
	public void onClick(View v) {
	switch (v.getId()) {
	case R.id.loadbutton1:
		
		break;
	case R.id.loaddeletebutton:
		
		break;
	case R.id.loadfinish:
		finish();
		break;
	}
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		Toast.makeText(this, "你点击了"+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		
		
	}
@Override
	protected void onStart() {
		super.onStart();
		IntentFilter filter = new IntentFilter(ACTION_DATA_AVAILABLE);
		registerReceiver(receiver, filter);
	}
@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}
private void BarcodeVeriFication(final String number) {
	String number_res="";
	String BatchNumber="";//批次号
	int ret = mnative.cutCode(number);
	switch (number.length()) {
		case 11:
		//发车凭证
		if (number.startsWith("68")&&number.length()==11&&!TextUtils.isEmpty(mEditText.getText()+"")) {
			mSoundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
			DepartureCertificate=number;
			nextStation.requestFocus();
			fEditText.setText(DepartureCertificate);
			Selection.setSelection(fEditText.getText(), fEditText.getText().length());
		}else {
			mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
			Toast.makeText(LoadingScanActivity.this, "请先扫描发车位", Toast.LENGTH_SHORT).show();
		}
		break;

		case 5:
		//装车位
		if (number.startsWith("ZC")&&!number.equals("ZC000")&&number.length()==5){
			mSoundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
			ParkingSpaces=number;	
			fEditText.requestFocus();
			mEditText.setText(ParkingSpaces);
			Selection.setSelection(mEditText.getText(), mEditText.getText().length());
		}
		break;
		case 6:
			//站点编号
		if (mnative.CheckBarcode(number, 2) == 1&&!TextUtils.isEmpty(mEditText.getText()+"")&&!TextUtils.isEmpty(fEditText.getText()+"")) {
			if(TextUtils.isEmpty(nextStation.getText()+"")){
				mSoundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
				nEditText.requestFocus();
				nextStation.setText(number);
				new MyThread(number).start();
				nextStation.setFocusable(false);
				Selection.setSelection(nextStation.getText(), nextStation.getText().length());
			Toast.makeText(LoadingScanActivity.this, "下一站已锁定", Toast.LENGTH_SHORT).show();
			}else {
				mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
				unregisterReceiver(receiver);
				 final NormalDialog dialog = new NormalDialog(LoadingScanActivity.this);
				    dialog.content("下一站已锁定是否切换")//
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
				                	nEditText.requestFocus();
				                	nextStation.setFocusable(true);
				    				nextStation.setText(number);
				    				new MyThread(number).start();
				    				nextStation.setFocusable(false);
				    				Selection.setSelection(nextStation.getText(), nextStation.getText().length());
				        			IntentFilter filter = new IntentFilter(ACTION_DATA_AVAILABLE);
				        			registerReceiver(receiver, filter);
				                	dialog.dismiss();
				                }
				            });
			}
		}else if(TextUtils.isEmpty(mEditText.getText()+"")&&TextUtils.isEmpty(fEditText.getText()+"")){
			mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
			Toast.makeText(LoadingScanActivity.this, "请先扫描装车位", Toast.LENGTH_SHORT).show();	
		}else if(!TextUtils.isEmpty(mEditText.getText()+"")&&TextUtils.isEmpty(fEditText.getText()+"")){
			mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
			Toast.makeText(LoadingScanActivity.this, "发车凭证错误", Toast.LENGTH_SHORT).show();	
		}
		break;
		case 13:
			//13位条码
		if (ret==0&&!TextUtils.isEmpty(nextStation.getText()+"")&&!TextUtils.isEmpty(mEditText.getText()+"")&&!TextUtils.isEmpty(fEditText.getText()+"")) {
			mSoundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
			nEditText.setText(number);	
			Selection.setSelection(nEditText.getText(), nEditText.getText().length());
		}else if (TextUtils.isEmpty(nextStation.getText()+"")&&!TextUtils.isEmpty(mEditText.getText()+"")&&!TextUtils.isEmpty(fEditText.getText()+"")) {
			mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
			Toast.makeText(LoadingScanActivity.this, "站点编号有误！", Toast.LENGTH_SHORT).show();	
		}else if(TextUtils.isEmpty(mEditText.getText()+"")&&!TextUtils.isEmpty(nextStation.getText()+"")&&!TextUtils.isEmpty(fEditText.getText()+"")){
			mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
			Toast.makeText(LoadingScanActivity.this, "装车位条码有误!", Toast.LENGTH_SHORT).show();	
		}else if(!TextUtils.isEmpty(nextStation.getText()+"")&&TextUtils.isEmpty(fEditText.getText()+"")&&!TextUtils.isEmpty(mEditText.getText()+"")){
			mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
			Toast.makeText(LoadingScanActivity.this, "发车凭证有误!", Toast.LENGTH_SHORT).show();	
		}else if(TextUtils.isEmpty(nextStation.getText()+"")&&TextUtils.isEmpty(fEditText.getText()+"")&&TextUtils.isEmpty(mEditText.getText()+"")){
			mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
			Toast.makeText(LoadingScanActivity.this, "请先扫描装车位!", Toast.LENGTH_SHORT).show();	
		}else if (TextUtils.isEmpty(nextStation.getText()+"")&&!TextUtils.isEmpty(mEditText.getText()+"")&&TextUtils.isEmpty(fEditText.getText()+"")){
			mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
			Toast.makeText(LoadingScanActivity.this, "发车凭证有误！", Toast.LENGTH_SHORT).show();	
		}
			break;
		case 18:
			//18位条码
			if (ret==0&&!TextUtils.isEmpty(nextStation.getText()+"")&&!TextUtils.isEmpty(mEditText.getText()+"")&&!TextUtils.isEmpty(fEditText.getText()+"")) {
				mSoundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
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
				nEditText.setText(number_res);
				Selection.setSelection(nEditText.getText(), nEditText.getText().length());
				ObjectAssignment(number_res, getDate(),BatchNumber);
			}else if (TextUtils.isEmpty(nextStation.getText()+"")&&!TextUtils.isEmpty(mEditText.getText()+"")&&!TextUtils.isEmpty(fEditText.getText()+"")) {
				mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
				Toast.makeText(LoadingScanActivity.this, "站点编号有误！", Toast.LENGTH_SHORT).show();	
			}else if(TextUtils.isEmpty(mEditText.getText()+"")&&!TextUtils.isEmpty(nextStation.getText()+"")&&!TextUtils.isEmpty(fEditText.getText()+"")){
				mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
				Toast.makeText(LoadingScanActivity.this, "装车位条码有误!", Toast.LENGTH_SHORT).show();	
			}else if(!TextUtils.isEmpty(nextStation.getText()+"")&&TextUtils.isEmpty(fEditText.getText()+"")&&!TextUtils.isEmpty(mEditText.getText()+"")){
				mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
				Toast.makeText(LoadingScanActivity.this, "发车凭证有误!", Toast.LENGTH_SHORT).show();	
			}else if(TextUtils.isEmpty(nextStation.getText()+"")&&TextUtils.isEmpty(fEditText.getText()+"")&&TextUtils.isEmpty(mEditText.getText()+"")){
				mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
				Toast.makeText(LoadingScanActivity.this, "请先扫描装车位!", Toast.LENGTH_SHORT).show();	
			}else if (TextUtils.isEmpty(nextStation.getText()+"")&&!TextUtils.isEmpty(mEditText.getText()+"")&&TextUtils.isEmpty(fEditText.getText()+"")){
				mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
				Toast.makeText(LoadingScanActivity.this, "发车凭证有误！", Toast.LENGTH_SHORT).show();	
			}
			break;
		case 24:
			//24位条码
			if (ret==0&&!TextUtils.isEmpty(nextStation.getText()+"")&&!TextUtils.isEmpty(mEditText.getText()+"")&&!TextUtils.isEmpty(fEditText.getText()+"")) {
				mSoundPool.play(soundMap.get(1), 1, 1, 0, 0, 1);
				number_res = number.substring(0, 13);
				BatchNumber = number.substring(13, 17);
				ObjectAssignment(number_res, getDate(),BatchNumber);
				nEditText.setText(number_res);
				Selection.setSelection(nEditText.getText(), nEditText.getText().length());
				Log.d("cutCode批次=24", BatchNumber + "////");
			}else if (TextUtils.isEmpty(nextStation.getText()+"")&&!TextUtils.isEmpty(mEditText.getText()+"")&&!TextUtils.isEmpty(fEditText.getText()+"")) {
				mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
				Toast.makeText(LoadingScanActivity.this, "站点编号有误！", Toast.LENGTH_SHORT).show();	
			}else if(TextUtils.isEmpty(mEditText.getText()+"")&&!TextUtils.isEmpty(nextStation.getText()+"")&&!TextUtils.isEmpty(fEditText.getText()+"")){
				mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
				Toast.makeText(LoadingScanActivity.this, "装车位条码有误!", Toast.LENGTH_SHORT).show();	
			}else if(!TextUtils.isEmpty(nextStation.getText()+"")&&TextUtils.isEmpty(fEditText.getText()+"")&&!TextUtils.isEmpty(mEditText.getText()+"")){
				mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
				Toast.makeText(LoadingScanActivity.this, "发车凭证有误!", Toast.LENGTH_SHORT).show();	
			}else if(TextUtils.isEmpty(nextStation.getText()+"")&&TextUtils.isEmpty(fEditText.getText()+"")&&TextUtils.isEmpty(mEditText.getText()+"")){
				mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
				Toast.makeText(LoadingScanActivity.this, "请先扫描装车位!", Toast.LENGTH_SHORT).show();	
			}else if (TextUtils.isEmpty(nextStation.getText()+"")&&!TextUtils.isEmpty(mEditText.getText()+"")&&TextUtils.isEmpty(fEditText.getText()+"")){
				mSoundPool.play(soundMap.get(2), 1, 1, 0, 0, 1);
				Toast.makeText(LoadingScanActivity.this, "发车凭证有误！", Toast.LENGTH_SHORT).show();	
			}
			break;
			
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
		Log.i("isNetAvailable", "isNetAvailable = "+isNetAvailable(LoadingScanActivity.this));
		if(!isNetAvailable(LoadingScanActivity.this)){
			Log.i("isNetAvailable", "网络不通，请稍候上传");
			Toast.makeText(LoadingScanActivity.this, "网络不通，请稍候上传", 1000).show();
			return true;
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
private void ObjectAssignment(String number_res, String date,
		String batchNumber2) {
	// TODO Auto-generated method stub
	
}

	//获取当前时间
	public String getDate() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
}
