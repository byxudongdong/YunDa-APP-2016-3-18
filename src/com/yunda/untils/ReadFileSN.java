package com.yunda.untils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.util.Log;

public class ReadFileSN {
	
@SuppressWarnings("resource")
public static String  ReadFile(){
 String devicesID = null;
	//定义存储空间
 byte[] bs=new byte[1024];
		//建立通道对象
 FileInputStream inputStream;
	ByteArrayOutputStream outputStream = null;
	try {
		inputStream = new FileInputStream(new File("/system/hw_info/serial_numbers"));		
		 outputStream=new ByteArrayOutputStream();
		//开始读文件
		int len =-1;
		if (inputStream!=null) {
			while ((len=inputStream.read(bs))!=-1) {
				outputStream.write(bs, 0, len);	
			}	
		}
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	//devicesID=new String(outputStream.toByteArray());
	devicesID = "999999999";//devicesID.substring(0,9);
	return devicesID;
	}
		
}
