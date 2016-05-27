package com.yunda.activity.cn;

import android.R.string;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class ReturnPartDatauploadService extends Service {
private String comPanyCdode;
private String userid;
private String password;
private String deviceID;
private String batch;
private String Bill;
private String cPCdode;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
@Override
public int onStartCommand(Intent intent, int flags, int startId) {
			
	return super.onStartCommand(intent, flags, startId);
}
	
	public static void main(String[] args) {
		

	}
public class MyReturnPartDatauploadService extends Binder{
	public ReturnPartDatauploadService getReturnPartDatauploadService(){
		
		return ReturnPartDatauploadService.this;
	}
}
}
