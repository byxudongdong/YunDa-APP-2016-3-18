package com.yunda.activity.cn;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yunda_app_2016_3_18.R;
import com.yunda.bluetoothpairing.cn.MyPrintAdapter;
import com.yunda.imp.cn.ImpReturnPartData;
import com.yunda.untils.BluetoothDevices;
import com.yunda.untils.ClsUtils;
import com.yunda.xlistview.cn.CustomImageButton;
import com.yundadialog.BaseAnimatorSet;
import com.yundadialog.BounceTopEnter;
import com.yundadialog.DialogMenuItem;
import com.yundadialog.NormalDialog;
import com.yundadialog.OnBtnClickL;
import com.yundadialog.OnOperItemClickL;
import com.yundadialog.SlideBottomExit;
import com.yundadialog.T;
public class BluetoothSettingActivity extends Activity implements OnClickListener,OnItemClickListener{
private CustomImageButton mCustomImageButton;
private TextView macTextview;//mac地址
private Button mBluetoothSettingButton;//蓝牙设置
private Button searchButton;//蓝牙搜索
private ListView bluetoothListview;//存放蓝牙设备列表
private Button finishButton;//返回
private Button pairButton;//配对
private ArrayList<com.yundadialog.DialogMenuItem> testItems = new ArrayList<com.yundadialog.DialogMenuItem>();
private String[] stringItems = {"耀华K3190A1_TYPE", "耀华K3190AX_TYPE","耀华K3190A1+_TYPE", "耀华K3190A7_TYPE", "耀华K3190A12_TYPE","耀华xK3190-12+E","天合TDl300_TYPE","天合TDl200A1_TYPE","上海权衡公司","动态称"};
private BaseAnimatorSet bas_in;
private BaseAnimatorSet bas_out;
private BluetoothAdapter xBluetoothAdapter;
private BluetoothDevices mBluetoothDevices;
private List<BluetoothDevice> xBluetoothDevicesList;
private static final int REQUEST_ENABLE_BT = 0x0001;
private MyPrintAdapter mPrintAdapter;
private ProgressDialog progressDialog;
private int pos;
private ImpReturnPartData mImpBluetooth;
private SharedPreferences spble;
private Set<BluetoothDevice> set;
private BroadcastReceiver receiver=new BroadcastReceiver() {
	@SuppressWarnings("null")
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		
		BluetoothDevice device = null;
		//Log.i("onReceive", action);
		if (BluetoothDevice.ACTION_FOUND.equals(action)) {
			 device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE); 
			xBluetoothDevicesList.add(device);   			 			
				mPrintAdapter.notifyDataSetChanged();
		}else if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)){
			progressDialog = ProgressDialog.show(context, "提示",    
                    "正在查找手边蓝牙，请稍后...", true);  
		}else if(BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
				progressDialog.dismiss();   
		}else if(BluetoothDevice.ACTION_BOND_STATE_CHANGED.equals(action)){
		 if (xBluetoothDevicesList.get(pos).getBondState() == BluetoothDevice.BOND_BONDING) {
				 progressDialog.dismiss();
				 progressDialog = ProgressDialog.show(context, "提示",    
		                    "正在匹配，请稍后...", true);  	
			}else if (xBluetoothDevicesList.get(pos).getBondState() == BluetoothDevice.BOND_BONDED){
				progressDialog.dismiss();
				Toast.makeText(context, "配对成功！", Toast.LENGTH_SHORT).show();
				 pairButton.setText("确定");	
			}else {
				Toast.makeText(getApplicationContext(), "配对失败,请尝试重新配对！", Toast.LENGTH_SHORT).show();
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
	setContentView(R.layout.bluetoothsetting);
		mCustomImageButton=(CustomImageButton) findViewById(R.id.customImageButton1);
		macTextview=(TextView) findViewById(R.id.mactextview);
		mBluetoothSettingButton=(Button) findViewById(R.id.bluetoothsetting);
		searchButton=(Button) findViewById(R.id.searchbooth);
		bluetoothListview=(ListView) findViewById(R.id.bluetoothlistview);
		finishButton=(Button) findViewById(R.id.finishbutton);
		pairButton=(Button) findViewById(R.id.pairbutton);
		mCustomImageButton.setColor(Color.BLACK);
		mCustomImageButton.setTextSize(15f);
		mCustomImageButton.setOnClickListener(this);
		mBluetoothSettingButton.setOnClickListener(this);
		searchButton.setOnClickListener(this);
		finishButton.setOnClickListener(this);
		pairButton.setOnClickListener(this);
		   bas_in = new BounceTopEnter();
	        bas_out = new SlideBottomExit();
	        testItems.add(new DialogMenuItem("耀华K3190A1_TYPE"));
	        testItems.add(new DialogMenuItem("耀华K3190AX_TYPE"));
	        testItems.add(new DialogMenuItem("耀华K3190A1+_TYPE"));
	        testItems.add(new DialogMenuItem("耀华K3190A7_TYPE"));
	        testItems.add(new DialogMenuItem("耀华K3190A12_TYPE"));
	        testItems.add(new DialogMenuItem("耀华xK3190-12+E"));
	        testItems.add(new DialogMenuItem("天合TDl300_TYPE"));
	        testItems.add(new DialogMenuItem("天合TDl200A1_TYPE"));
	        testItems.add(new DialogMenuItem("上海权衡公司"));
	        testItems.add(new DialogMenuItem("动态称"));
	    	mImpBluetooth=new ImpReturnPartData(this);
	    	 xBluetoothDevicesList=new ArrayList<BluetoothDevice>();
	    	 xBluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
	    	 set= xBluetoothAdapter.getBondedDevices();
	    	Iterator<BluetoothDevice> it=set.iterator();
	    	while (it.hasNext()) {
				BluetoothDevice mBluetoothDevice=	it.next();
				xBluetoothDevicesList.add(0, mBluetoothDevice);
			}
		    mPrintAdapter=new MyPrintAdapter(this, xBluetoothDevicesList);
		    	bluetoothListview.setOnItemClickListener(this);
		    	spble=getSharedPreferences("device", MODE_PRIVATE);
		    	macTextview.setText(spble.getString("address", "                            "));
		    	bluetoothListview.setAdapter(mPrintAdapter);
				mCustomImageButton.setText(spble.getString("blancetype", "耀华K3190A1_TYPE"));

}
@Override
public void onClick(View v) {
switch (v.getId()) {
case R.id.customImageButton1:
	NormalListDialog();
	break;
case R.id.bluetoothsetting:
	Intent mBluetooth_pairingIntent=new Intent();
	mBluetooth_pairingIntent.setAction("android.settings.BLUETOOTH_SETTINGS");
	startActivity(mBluetooth_pairingIntent);
	break;
case R.id.searchbooth:
	if (xBluetoothAdapter.enable()) {
		xBluetoothAdapter.startDiscovery();
	}
	Toast.makeText(this, mCustomImageButton.getText()+"", Toast.LENGTH_SHORT).show();
	break;
case R.id.finishbutton:
	finish();
	break;
case R.id.pairbutton:
	switch (pairButton.getText().toString()) {
	case "确定":
		NormalDialogStyleOne();
		break;

		
	case "配对":
		 try {
		 if (xBluetoothDevicesList.get(pos).getBondState() == BluetoothDevice.BOND_NONE) {
			 ClsUtils.createBond(xBluetoothDevicesList.get(pos).getClass(),xBluetoothDevicesList.get(pos));
			 progressDialog = ProgressDialog.show(this, "提示",    
	                    "正在配对，请稍后...", true);  	  
			 }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	break;
}	
}
@Override
	protected void onStart() {
		super.onStart();
		initIntentFilter();
	}
@Override
	protected void onResume() {
		super.onResume();
		if (!xBluetoothAdapter.enable()) {
			Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(mIntent, REQUEST_ENABLE_BT);
		}
	}
private void NormalListDialog() {
    final com.yundadialog.NormalListDialog dialog = new com.yundadialog.NormalListDialog(BluetoothSettingActivity.this, stringItems);
    dialog .title("请选择电子秤类型")
    	.showAnim(bas_in)//
            .dismissAnim(bas_out)//
            .show();
    dialog.setOnOperItemClickL(new OnOperItemClickL() {
		@Override
		public void onOperItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			mCustomImageButton.setText(testItems.get(position).operName);
			mCustomImageButton.setColor(Color.BLACK);
			mCustomImageButton.setTextSize(15f);
			 T.showShort(getApplicationContext(), testItems.get(position).operName);
		        dialog.dismiss();
			
		}
    });
    
}
@SuppressLint("InlinedApi")
private void initIntentFilter() {
	IntentFilter intentFilter = new IntentFilter();
	intentFilter.addAction(BluetoothDevice.ACTION_FOUND);
	intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
	intentFilter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
	intentFilter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
	intentFilter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
	intentFilter.addAction(BluetoothDevice.ACTION_PAIRING_REQUEST);
	registerReceiver(receiver, intentFilter);
}
@SuppressLint("NewApi")
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			pos=position;
			//view.setBackgroundColor(Color.YELLOW);
			if (xBluetoothDevicesList.get(pos).getBondState()==BluetoothDevice.BOND_BONDED) {
				pairButton.setText("确定");
			}else {
				pairButton.setText("配对");
			}	
			macTextview.setText(xBluetoothDevicesList.get(pos).getAddress());
			spble.edit().putString("address", xBluetoothDevicesList.get(pos).getAddress()).commit();
				mPrintAdapter.setIndex(pos);
				mPrintAdapter.notifyDataSetChanged();
}
private void NormalDialogStyleOne() {
    final NormalDialog dialog = new NormalDialog(BluetoothSettingActivity.this);
    dialog.content("确定保存当前选中的信息吗？")//
            .showAnim(bas_in)//
            .dismissAnim(bas_out)//
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
                	spble.edit().putString("address", xBluetoothDevicesList.get(pos).getAddress()).commit();
                	spble.edit().putString("blancetype",mCustomImageButton.getText()+"").commit();
                    T.showShort(BluetoothSettingActivity.this, "信息已保存");
                    //dialog.dismiss();
                    finish();
                }
            });
}

@Override
protected void onDestroy() {
	super.onDestroy();
	xBluetoothAdapter.cancelDiscovery();
	unregisterReceiver(receiver);
}
}
