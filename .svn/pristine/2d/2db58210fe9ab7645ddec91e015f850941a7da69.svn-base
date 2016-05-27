package com.yunda.untils;

import java.util.Iterator;
import java.util.List;

import android.util.Log;

public class PartData {
private String userid;
private String typeworkid;
private String typework;
private String number;
private String flag;
private String time;
private String name;
private String currentsite;
private String date;
private String BatchNumber;
private List<PartData> mList;
private String conpanyCode;
private String userIDs;
private String professionID;
private String password;
private String uploadtime;
public PartData(){
	
}
public PartData(List<PartData> mList, String conpanyCode,
		String userID, String professionID,String uploadtime, String password) {
	super();
	this.mList = mList;
	this.conpanyCode = conpanyCode;
	userIDs = userID;
	this.professionID = professionID;
	this.password = password;
	this.uploadtime=uploadtime;
}
public String getBatchNumber() {
	return BatchNumber;
}
public void setBatchNumber(String batchNumber) {
	BatchNumber = batchNumber;
}
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getTypeworkid() {
	return typeworkid;
}
public void setTypeworkid(String typeworkid) {
	this.typeworkid = typeworkid;
}
public String getTypework() {
	return typework;
}
public void setTypework(String typework) {
	this.typework = typework;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
public String getFlag() {
	return flag;
}
public void setFlag(String flag) {
	this.flag = flag;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getCurrentsite() {
	return currentsite;
}
public void setCurrentsite(String currentsite) {
	this.currentsite = currentsite;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String toString(){
StringBuffer mStringBuffer=new StringBuffer();	
	Iterator<PartData> it=mList.iterator();
	while (it.hasNext()) {
		PartData	mReturnPart=(PartData) it.next();
	mStringBuffer.append("<o>"+"\r\n"+
	" <d>"+mReturnPart.getBatchNumber()+"</d>"+"\r\n"+
			"<d>"+mReturnPart.getNumber()+"</d>"+"\r\n"+
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
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+conpanyCode+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+userIDs+"</d>"+"\r\n"+
			"<d>"+professionID+"</d>"+"\r\n"+
			"<d>"+"90"+"</d>"+"\r\n"+
			"<d>"+uploadtime+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+
			"<d>"+""+"</d>"+"\r\n"+ "</o>");					
	}	
String xml="<req"+" op"+"="+"\"op12amb\""+">"+"\r\n"
			+"<h>"+"\r\n"+"<ver>"+"1.0"+"</ver>"+"\r\n"+"<company>"+conpanyCode+"</company>"+"\r\n"+"<user>"+userIDs+"</user>"+"\r\n"+ 
			" <pass>"+new MD5transformation().MD5(password)+"</pass>"+"\r\n"+"<dev1>"+ReadFileSN.ReadFile()+"</dev1>"+"\r\n"+ 
			"<dev2>"+""+"</dev2>"+"\r\n"+"</h>"+"\r\n"+"<data>"+"\r\n"+mStringBuffer.toString()+"\r\n"+"</data>"+"\r\n"+"</req>";
		Log.i("toString", xml);
	return	xml;
}
}
