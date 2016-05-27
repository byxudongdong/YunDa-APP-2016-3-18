package com.yunda.untils;

import java.util.Iterator;
import java.util.List;

import android.util.Log;

public class SqlDataupload {
private String bignumber;
private String number;
private String time;
private int  flag;
private String name;
private String nextstation;
private String BatchNumber;
private String nextstationCode;
private List<SqlDataupload> mList;
private String ConpanyCode;
private String UserID;
private String ProfessionID;
private String timedataString;
private String PassWord;
public SqlDataupload(){
	
}
public SqlDataupload(List<SqlDataupload> mList, String conpanyCode,
		String userID, String professionID, String timedataString,
		String passWord) {
	super();
	this.mList = mList;
	ConpanyCode = conpanyCode;
	UserID = userID;
	ProfessionID = professionID;
	this.timedataString = timedataString;
	PassWord = passWord;
}
public String getNextstationCode() {
	return nextstationCode;
}
public void setNextstationCode(String nextstationCode) {
	this.nextstationCode = nextstationCode;
}
public String getBatchNumber() {
	return BatchNumber;
}
public void setBatchNumber(String batchNumber) {
	BatchNumber = batchNumber;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getBignumber() {
	return bignumber;
}
public void setBignumber(String bignumber) {
	this.bignumber = bignumber;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getNextstation() {
	return nextstation;
}
public void setNextstation(String nextstation) {
	this.nextstation = nextstation;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public int getFlag() {
	return flag;
}
public void setFlag(int flag) {
	this.flag = flag;
}
public String toString(){
StringBuffer mStringBuffer=new StringBuffer();	
	Iterator<SqlDataupload> it= mList.iterator();
	while (it.hasNext()) {
		SqlDataupload mListData=(SqlDataupload) it.next();
	mStringBuffer.append("<o>"+"\r\n"+
			" <d>"+mListData.getBatchNumber()+"</d>"+"\r\n"+
			"<d>"+mListData.getNumber()+"</d>"+"\r\n"+
			"<d>"+mListData.getBignumber()+"</d>"+"\r\n"+
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
			"<d>"+ConpanyCode+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+mListData.getNextstationCode()+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+UserID+"</d>"+"\r\n"+
			"<d>"+ProfessionID+"</d>"+"\r\n"+
			"<d>"+"03"+"</d>"+"\r\n"+
			"<d>"+timedataString+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+ "</o>");					
	}	
String xml="<req"+" op"+"="+"\"op12amb\""+">"+"\r\n"
			+"<h>"+"\r\n"+"<ver>"+"1.0"+"</ver>"+"\r\n"+"<company>"+ConpanyCode+"</company>"+"\r\n"+"<user>"+UserID+"</user>"+"\r\n"+ 
			" <pass>"+new MD5transformation().MD5(PassWord)+"</pass>"+"\r\n"+"<dev1>"+ReadFileSN.ReadFile()+"</dev1>"+"\r\n"+ 
			"<dev2>"+""+"</dev2>"+"\r\n"+"</h>"+"\r\n"+"<data>"+"\r\n"+mStringBuffer.toString()+"\r\n"+"</data>"+"\r\n"+"</req>";
		Log.i("toString", xml);
	return	xml;
}

}
