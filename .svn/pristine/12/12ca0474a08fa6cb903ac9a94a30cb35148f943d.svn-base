package com.yunda.untils;

import java.util.Iterator;
import java.util.List;

import android.util.Log;

public class PacketDataXml {
private List<?> mList;
private String conpanyCode;
private String userID;
private String professionID;
private String time;
private String password;

public PacketDataXml(List<?> mList, String conpanyCode, String userID,
		String professionID, String time, String password) {
	super();
	this.mList = mList;
	this.conpanyCode = conpanyCode;
	this.userID = userID;
	this.professionID = professionID;
	this.time = time;
	this.password = password;
}

public String toString(){
StringBuffer mStringBuffer=new StringBuffer();	
	Iterator<ListData> it=(Iterator<ListData>) mList.iterator();
	while (it.hasNext()) {
		ListData mListData=(ListData) it.next();
	mStringBuffer.append("<o>"+"\r\n"+
			" <d>"+mListData.getBatchNumber()+"</d>"+"\r\n"+
			"<d>"+mListData.getNumber()+"</d>"+"\r\n"+
			"<d>"+mListData.getBigNumber()+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+conpanyCode+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+mListData.getNextStation()+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+userID+"</d>"+"\r\n"+
			"<d>"+professionID+"</d>"+"\r\n"+
			"<d>"+"03"+"</d>"+"\r\n"+
			"<d>"+time+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+ "</o>");					
	}	
String xml="<req"+" op"+"="+"\"op12amb\""+">"+"\r\n"
			+"<h>"+"\r\n"+"<ver>"+"1.0"+"</ver>"+"\r\n"+"<company>"+conpanyCode+"</company>"+"\r\n"+"<user>"+userID+"</user>"+"\r\n"+ 
			" <pass>"+new MD5transformation().MD5(password)+"</pass>"+"\r\n"+"<dev1>"+ReadFileSN.ReadFile()+"</dev1>"+"\r\n"+ 
			"<dev2>"+""+"</dev2>"+"\r\n"+"</h>"+"\r\n"+"<data>"+"\r\n"+mStringBuffer.toString()+"\r\n"+"</data>"+"\r\n"+"</req>";
		Log.i("toString", xml);
	return	xml;
}

}
