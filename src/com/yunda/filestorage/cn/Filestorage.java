package com.yunda.filestorage.cn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class Filestorage {
	private Context context;
	private boolean hasSD = false;// SD卡是否存在 
	public static String SDPATH;// SD卡的路径 
	private String FILESPATH;// 当前程序包的路径
	private File file1;
	private File file2;
	private File file3;
	public Filestorage(Context context) {
		this.context = context;
		hasSD = Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
		SDPATH = Environment.getExternalStorageDirectory().getPath();
		FILESPATH = this.context.getFilesDir().getPath();
	}
	//创建文件夹
	//在sd卡上创建文件夹件
		public File createSDtwoFiles(String fileName) throws IOException {
			
			 file1 = new File(SDPATH + "//" + fileName);
			if (!file1.exists()) {
				file1.mkdirs();
			}
			//file.lastModified();获取文件创建时间
			return file1;
		}	
		//在sd卡上创建文件
		public File createSDthreeoFiles(File mFile,String fileName) throws IOException {
					file2=new File(mFile, fileName);
					if (!file2.exists()) {
						boolean m=file2.mkdirs();
						Log.i("createSDthreeoFiles", m+"");
					}
					
					//file.lastModified();获取文件创建时间
					return file2;
				}	
	//在sd卡上创建文件
	public File createSDFile(File mFile,String fileName) throws IOException {
		file3 = new File(mFile,fileName);
		if (!file3.exists()) {
			file3.createNewFile();
		}
		//file.lastModified();获取文件创建时间
		return file3;
	}	
	public void writeSDFile(String str, String fileName) {
		
		try {
			Log.i("writeSDFile", str+",,"+fileName);
			BufferedWriter bf = new BufferedWriter(new FileWriter(fileName, false));
			bf.write(str);
			bf.flush();
			bf.close();
		} catch (Exception e) {
			Log.i("pxt","e = "+e.toString());
		}
	}	
	//读取文件内容
	public  String readSDFile(File path) throws Exception{
		Log.i("readSDFile", path.getAbsolutePath());
		StringBuffer mStringBuffer=new StringBuffer();
		BufferedReader mBufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(path)));
		String xm;
		while ( (xm=mBufferedReader.readLine())!= null) {
			mStringBuffer.append(xm);	
		}
		return mStringBuffer.toString();
	}
	
	//删除文件
		public void delFile(File mFile,String fileName){
			File file = new File(mFile, fileName);
			if(file.isFile()){
				file.delete();
			}
		}	
		//判断文件是否存在
		public boolean lookSDFile(String str,String fileName){
			File jFile=new File(fileName, str);
			if (!jFile.exists()) {
				return true;
			}
			return false;
		}
}

