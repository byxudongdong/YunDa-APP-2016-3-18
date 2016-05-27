package com.yunda.activity.cn;

import com.yunda.untils.ClsUtils;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BluetoothConnectActivityReceiver extends BroadcastReceiver {
	String strPsw = "123456";  
	@Override
	public void onReceive(Context context, Intent intent) {
			Log.i("onReceive", intent.getAction());
		 if (intent.getAction().equals("android.bluetooth.device.action.PAIRING_REQUEST")){    
		BluetoothDevice btDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);  
			int a =	btDevice.getBondState();
			Log.i("onReceive", a+"");
//	      byte[] pinBytes = BluetoothDevice.convertPinToBytes("1234");    
//	      btDevice.setPin(pinBytes);    
		
		try{    
			 
            
            //  ClsUtils.cancelPairingUserInput(btDevice.getClass(), btDevice);	                     
		}  catch (Exception e) {      
			 // TODO Auto-generated catch block    
            e.printStackTrace();   	                   
				                  
            
        }	                  
		  }		               
	}

}
