package com.yunda.untils;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.apache.http.entity.StringEntity;
import org.xmlpull.v1.XmlPullParser;

import android.R.integer;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yunda.imp.cn.ImpDataUpload;
import com.yunda.imp.cn.ImpReturnPartData;

public  class HttpData {
 private ImpReturnPartData mImpReturnPartData;//回流件
 private ImpDataUpload mImpDataUpload;//集包
 private MyApplication mContext;
private List<?> mList;
private static  String URL_INTENT = "";
private int datatotal;
private Datatotal data;
private String  profession;
String st= null,res = null;
private SharedPreferences ps;
public HttpData(MyApplication mContext, List<?> mList,String  profession) {
	this.mContext = mContext;
	this.mList=mList;
	this.profession=profession;
	mImpReturnPartData=new ImpReturnPartData(mContext);
	mImpDataUpload=new ImpDataUpload(mContext);
	  ps=mContext.getSharedPreferences("host", mContext.MODE_PRIVATE);
	  URL_INTENT=ps.getString("datadend","http://qz.yundasys.com:9900/ws/ws.jsp");
	  Log.i("URL_INTENT", URL_INTENT);
}
	public  void ReturnPartDataupload(final String xml){
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
							// TODO Auto-generated catch block
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
private   void XmlPersor(String result ) {
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
	 if (getResult( result ).equals("ok")) {
			 Toast.makeText(mContext, "当前上传"+res+"条", Toast.LENGTH_SHORT).show();	
				 @SuppressWarnings("unchecked")
				Iterator<ListData> it=(Iterator<ListData>) mList.iterator();
				 while (it.hasNext()) {
					 ListData mListData=it.next();
					 mImpDataUpload. updateInformation(mListData.getNumber(),1);		
					 	it.remove();	
				 }
				 if (this.data!=null) {
					 this.data.setData(mList.size());
				}
			 }
		break;
	case "装车扫描":

		break;
	case "回流件扫描":
	
		 if (getResult( result ).equals("ok")) {
			 Toast.makeText(mContext, "当前上传"+res+"条", Toast.LENGTH_SHORT).show();	
				 Iterator<ReturnPart> itt=(Iterator<ReturnPart>) mList.iterator();
				 while (itt.hasNext()) {
					 ReturnPart mReturnPart2=itt.next();
					 mImpReturnPartData.updateInformation(mReturnPart2.getNumber(), 2);	
					 	itt.remove();	
				 }
				 if (this.data!=null) {
					 this.data.setData(mList.size());
				}
			 }
	 
		break;
	case "车辆调度":

		break;
	}
	}

public int getDatatotal() {
	return datatotal;
}

public void setDatatotal(Datatotal datatotal) {
	this.data = datatotal;
}
public interface Datatotal{
	public void setData(int a );
}
public String getResult(String result ){
	XmlPullParser mparser = Xml.newPullParser();
	try {
		mparser.setInput(new ByteArrayInputStream(result.getBytes("UTF-8")), "UTF-8");
		int eventType =mparser.getEventType();
		while (eventType!=XmlPullParser.END_DOCUMENT) {
			if (eventType==XmlPullParser.START_TAG) {
				if ("dta".equals(mparser.getName())) {
					st=mparser.getAttributeValue(0);
					res=mparser.getAttributeValue(1);
				}
			}
		 eventType = mparser.next();	
		}

 } catch (Exception e) {
	e.printStackTrace();
}
	return st;
	
	}
}

