package com.yunda.activity.cn;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yunda_app_2016_3_18.R;
import com.yunda.untils.HttpData;
import com.yunda.untils.HttpData.Datatotal;
import com.yunda.untils.ListData;
import com.yunda.untils.MyApplication;
import com.yunda.untils.NormalListDialog;
import com.yunda.untils.PacketDataXml;
import com.yunda.untils.ReturnPart;
import com.yunda.untils.ReturnPartXml;
import com.yundadialog.DialogMenuItem;
import com.yundadialog.OnOperItemClickL;
import com.yundadialog.T;
public class MainmenueActivity extends Activity implements OnClickListener{
	private Button mBusinessoperationButton;//业务操作
	private Button mRecordqueryButton;//记录查询
	private Button mInformationqueryButton;//信息查询
	private Button mDatadownloadButton;//资料下载
	private Button msettingButton;//系统设置
	private Button msignoutButton;//注销
	private TextView t_nameTextView;
	private TextView t_timeTextView;
	private TextView t_proessName;
	private TextView t_proessid;
	private TextView t_userid;
	private String userID;//用户id
	private String profession;//工种名称
	private String professionID;//工种id
	private String passWord;//用户密码
	private String conpanyCode;//当前站点
	private String name;//登录人
	private String[] stringItems = {"回流件扫描[1]"};
	private String[] packets = {"集包扫描[1]","集包扫描(效验)[2]","集包扫描(自动)[3]"};
	private String[] packetslook = {"业绩统计[1]","集包扫描[2]"};
	private String[] lookreturnpart = {"业绩统计[1]","回流件扫描[2]"};
	private String[] loadscanitem = {"装车扫描[1]","装车结束[2]"};
	private TextView totaldata;
	private ReturnPartDatauploadService service;
	private MyApplication application;
	private List<?> mList;
	private boolean flag=true;
	private ImageView imageView1;
	private AnimationDrawable frameAnim;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	//取消状态栏   
	  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
	    WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	setContentView(R.layout.mainmenue);
	findViewById();
	Log.i("onStart", "onCreate");
	
}
@Override
protected void onStart() {
	super.onStart();
	if (application.getmList()!=null) {	
		mList=application.getmList();
		totaldata.setText(mList.size()+""+"/0");	
	}
}
private void findViewById() {
	t_nameTextView=(TextView) findViewById(R.id.t_name);
	t_timeTextView=(TextView) findViewById(R.id.t_time);
	t_userid=(TextView) findViewById(R.id.dengluid);
	imageView1=(ImageView) findViewById(R.id.imaew1);
	t_proessName=(TextView) findViewById(R.id.gongzhongming);
	t_proessid=(TextView) findViewById(R.id.gongzhongid);
	mBusinessoperationButton=(Button) findViewById(R.id.yewubutton);
	mRecordqueryButton=(Button) findViewById(R.id.jiluchaxunbutton);
	mInformationqueryButton=(Button) findViewById(R.id.xinxichaxunbutton);
	mDatadownloadButton=(Button) findViewById(R.id.ziliaoxiazaibutt);
	msettingButton=(Button) findViewById(R.id.xitongshezhi);
	msignoutButton=(Button) findViewById(R.id.destorybutton);
	totaldata=(TextView) findViewById(R.id.totaldata);
	mBusinessoperationButton.setOnClickListener(this);
	mRecordqueryButton.setOnClickListener(this);
	mInformationqueryButton.setOnClickListener(this);
	mDatadownloadButton.setOnClickListener(this);
	msettingButton.setOnClickListener(this);
	msignoutButton.setOnClickListener(this);
	name=getIntent().getStringExtra("name");
	t_nameTextView.setText("登录人 "+name);
	t_timeTextView.setText("登录时间"+getIntent().getStringExtra("time"));
	userID=getIntent().getStringExtra("userName");
	t_userid.setText("登录ID"+userID);
	profession=getIntent().getStringExtra("profession").substring(4);
	t_proessName.setText("工种名"+profession);
	professionID=getIntent().getStringExtra("number");
	t_proessid.setText("工种ID"+professionID);
	passWord=getIntent().getStringExtra("passWord");
	conpanyCode=getIntent().getStringExtra("conpanyCode");
    application=(MyApplication) getApplicationContext();
    application.setConpanyCode(conpanyCode);
    application.setName(name);
    application.setPassWord(passWord);
    application.setProfession(profession);
    application.setProfessionID(professionID);
    application.setUserID(userID);
    imageView1=(ImageView) findViewById(R.id.imaew1);
    frameAnim=(AnimationDrawable) getResources().getDrawable(R.drawable.bullet_anim);
    imageView1.setBackgroundDrawable(frameAnim);
    imageView1.setVisibility(View.GONE);
  
}
@Override
protected void onResume() {
	super.onResume();

	
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
@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	switch (keyCode) {
		case 131://F1再次上传	
			Log.i("isNetAvailable", "isNetAvailable = "+isNetAvailable(MainmenueActivity.this));
			if(!isNetAvailable(MainmenueActivity.this)){
				Log.i("isNetAvailable", "网络不通，请稍候上传");
				Toast.makeText(MainmenueActivity.this, "网络不通，请稍候上传", 1000).show();
				return true;
			}
			switch (profession.trim()) {
			case "特殊件扫描":
				
				break;

			case "卸车扫描":
				break;
			case "到件扫描":
				break;
			case "发件扫描":
			
				break;
			case "集包扫描":
	if (application.getmList()!=null) {
		if (mList.size()>0) {
			imageView1.setVisibility(View.VISIBLE);
			start();
			HttpData mData=new HttpData( application, (List<ListData>) mList,profession);
			mData.ReturnPartDataupload(new PacketDataXml(application.getmList(), application.getConpanyCode(), application.getUserID(), application.getProfessionID(), getDate(), application.getPassWord()).toString());		
			mData.setDatatotal(new Datatotal() {
				@Override
				public void setData(int a) {
					totaldata.setText(a+""+"/0");
					imageView1.setVisibility(View.GONE);
					stop();
				}
			});	
		}else {
			Toast.makeText(this, "当前无数据上传！", Toast.LENGTH_SHORT).show();
		}
	}else {
		Toast.makeText(this, "当前无数据上传！", Toast.LENGTH_SHORT).show();
	}
	
	break;
case "装车扫描":
	
	break;
case "回流件扫描":
	if (application.getmList()!=null) {
		if (mList.size()>0) {
			imageView1.setVisibility(View.VISIBLE);
			start();
			HttpData mData=new HttpData( application, (List<ReturnPart>) mList,profession);
			mData.ReturnPartDataupload(new ReturnPartXml((List<ReturnPart>) application.getmList(), application.getConpanyCode(), application.getUserID(), application.getProfessionID(), getDate(), application.getPassWord()).toString());		
			mData.setDatatotal(new Datatotal() {
				
				@Override
				public void setData(int a) {
					totaldata.setText(a+""+"/0");
					imageView1.setVisibility(View.GONE);
					stop();
				}
			});	
		}else {
			Toast.makeText(this, "当前无数据上传！", Toast.LENGTH_SHORT).show();
		}
	}else {
		Toast.makeText(this, "当前无数据上传！", Toast.LENGTH_SHORT).show();
	}
	
	break;
case "车辆调度":
	Intent cheliangIntent=new Intent();
	cheliangIntent.putExtra("profession", "车辆调度");
	setResult(0, cheliangIntent);
	break;
			}
	break;
		case 132://光值配对
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
		case KeyEvent.KEYCODE_BACK:
			return true;
	}
	Log.i("onKeyDown", keyCode+"");
	
		return super.onKeyDown(keyCode, event);
	}
@Override
public void onClick(View v) {
	switch (v.getId()) {
		case R.id.yewubutton:
			//Toast.makeText(this, profession, Toast.LENGTH_SHORT).show();
			Log.i("profession", profession);
			switch (profession.trim()) {
			case"特殊件扫描":
				Toast.makeText(this, "此功能暂未实现", Toast.LENGTH_SHORT).show();
				break;
			case"卸车扫描":
				Toast.makeText(this, "此功能暂未实现", Toast.LENGTH_SHORT).show();
				break;
			case"到件扫描":
				Toast.makeText(this, "此功能暂未实现", Toast.LENGTH_SHORT).show();
				break;
			case"发件扫描":
				Toast.makeText(this, "此功能暂未实现", Toast.LENGTH_SHORT).show();
				break;
			case"集包扫描":
	NormalListDialog mDialog=new NormalListDialog(userID, profession, professionID, passWord, conpanyCode, name);
			mDialog.ActionSheetDialog(packets, MainmenueActivity.this,"集包扫描");
				break;
			case"装车扫描":
				NormalListDialog loadDialog=new NormalListDialog(userID, profession, professionID, passWord, conpanyCode, name);
					loadDialog.ActionSheetDialog(loadscanitem, MainmenueActivity.this,"装车扫描");
				break;	
			case"回流件扫描":
	NormalListDialog nmDialog=new NormalListDialog(userID, profession, professionID, passWord, conpanyCode, name);
			nmDialog.ActionSheetDialog(stringItems, MainmenueActivity.this,"回流件扫描");
				break;
			case"车辆调度":
				Toast.makeText(this, "此功能暂未实现", Toast.LENGTH_SHORT).show();
				break;
			}
			break;
			case R.id.jiluchaxunbutton:
				switch (profession.trim()) {
				case"特殊件扫描":
					Toast.makeText(this, "此功能暂未实现", Toast.LENGTH_SHORT).show();
					break;
				case"卸车扫描":
					Toast.makeText(this, "此功能暂未实现", Toast.LENGTH_SHORT).show();
					break;
				case"到件扫描":
					Toast.makeText(this, "此功能暂未实现", Toast.LENGTH_SHORT).show();
					break;
				case"发件扫描":
					Toast.makeText(this, "此功能暂未实现", Toast.LENGTH_SHORT).show();
					break;
				case"集包扫描":
	NormalListDialog packetDialog=new NormalListDialog(userID, profession, professionID, passWord, conpanyCode, name);
					packetDialog.packetSheetDialog(packetslook, MainmenueActivity.this);
//					Intent RecordIntent=new Intent(MainmenueActivity.this,RecordActivity.class);
//					RecordIntent.putExtra("userID", userID);
//					RecordIntent.putExtra("profession", profession);
//					RecordIntent.putExtra("professionID", professionID);
//					RecordIntent.putExtra("passWord", passWord);
//					RecordIntent.putExtra("conpanyCode", conpanyCode);
//					startActivity(RecordIntent);
					
					break;
				case"装车扫描":
			NormalListDialog loadDialog=new NormalListDialog(userID, profession, professionID, passWord, conpanyCode, name);
				loadDialog.ActionSheetDialog(loadscanitem, MainmenueActivity.this,"装车扫描");
								break;
				case"回流件扫描":
		NormalListDialog mDialog=new NormalListDialog(userID, profession, professionID, passWord, conpanyCode, name);
					mDialog.ActionSheetDialog(lookreturnpart, MainmenueActivity.this,"回流件扫描");
					break;
				case"车辆调度":
					Toast.makeText(this, "此功能暂未实现", Toast.LENGTH_SHORT).show();
					break;
				}
			
			break;
			case R.id.xinxichaxunbutton:
				Toast.makeText(this, "此功能暂未实现！", Toast.LENGTH_SHORT).show();
			break;
			case R.id.ziliaoxiazaibutt:
				ActionSheetDialog();
				break;
			case R.id.xitongshezhi:
				Intent sIntent=new Intent(MainmenueActivity.this,SettingActivity.class);
				startActivity(sIntent);	
					break;
			case R.id.destorybutton:
				MainmenueActivity.this.startActivity(new Intent(MainmenueActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
				break;
	}
}
private void ActionSheetDialog() {
    final String[] stringItems = {"公司列表[1]", "员工列表[2]","问题件/留仓原件[3]", "异常签收类型[4]", "欠费公司列表[5]"};
    final com.yundadialog.ActionSheetDialog dialog = new com.yundadialog.ActionSheetDialog(MainmenueActivity.this, stringItems, null);
    dialog.title("选择要下载的列表名称\r\n(下载资料:时间久请耐心等待)")//
            .titleTextSize_SP(14.5f)//
            .show();

    dialog.setOnOperItemClickL(new OnOperItemClickL() {
        @Override
        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {	
    		switch (stringItems[position]) {
			case "公司列表[1]":
				Intent pIntent=new Intent(MainmenueActivity.this,CompangListActivity.class);
				pIntent.putExtra("userID", userID);
				pIntent.putExtra("passWord", passWord);
				pIntent.putExtra("conpanyCode", conpanyCode);
				startActivity(pIntent);
				break;

			case "员工列表[2]":
				Intent xIntent=new Intent(MainmenueActivity.this,StaffListActivity.class);
				xIntent.putExtra("userID", userID);
				xIntent.putExtra("passWord", passWord);
				xIntent.putExtra("conpanyCode",conpanyCode);
				startActivity(xIntent);
				break;
			case "问题件/留仓原件[3]":
				Intent ProIntent=new Intent(MainmenueActivity.this,ProblemListActivity.class);
				ProIntent.putExtra("userID",userID);
				ProIntent.putExtra("passWord", passWord);
				ProIntent.putExtra("conpanyCode",conpanyCode);
				startActivity(ProIntent);
				break;	
			case "异常签收类型[4]":
				Intent AbnIntent=new Intent(MainmenueActivity.this,AbnormalActivity.class);
				AbnIntent.putExtra("userID",userID);
				AbnIntent.putExtra("passWord", passWord);
				AbnIntent.putExtra("conpanyCode",conpanyCode);
				startActivity(AbnIntent);
				break;
			case"欠费公司列表[5]":
				Intent arrIntent=new Intent(MainmenueActivity.this,ArrearsActivity.class);
				arrIntent.putExtra("userID", userID);
				arrIntent.putExtra("passWord", passWord);
				arrIntent.putExtra("conpanyCode",conpanyCode);
				startActivity(arrIntent);
				break;
			}
            T.showShort(MainmenueActivity.this, stringItems[position]);
            dialog.dismiss();
        }
    });
   
}
///**
// * 开始播放
// */
	protected void start() {
	if (frameAnim != null && !frameAnim.isRunning()) {
		frameAnim.start();
//		Toast.makeText(ReturnPartActivity.this, "开始播放", 0).show();
//		Log.i("main", "index 为5的帧持续时间为："+frameAnim.getDuration(5)+"毫秒");
//		Log.i("main", "当前AnimationDrawable一共有"+frameAnim.getNumberOfFrames()+"帧");
	}
}
///**
// * 停止播放
// */
protected void stop() {
	if (frameAnim != null && frameAnim.isRunning()) {
		frameAnim.stop();
		//Toast.makeText(ReturnPartActivity.this, "停止播放", 0).show();
	}
}
}
