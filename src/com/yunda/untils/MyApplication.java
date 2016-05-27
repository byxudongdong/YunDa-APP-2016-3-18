package com.yunda.untils;


import java.util.List;

import com.yunda.activity.cn.ReturnPartDatauploadService;

import android.app.Application;

public class MyApplication extends Application {
private ReturnPartDatauploadService services;	
private List<?> mList;
private String userID;//用户id
private String profession;//工种名称
private String professionID;//工种id
private String passWord;//用户密码
private String conpanyCode;//当前站点
private String name;//登录人
 public String getUserID() {
	return userID;
}
public void setUserID(String userID) {
	this.userID = userID;
}
public String getProfession() {
	return profession;
}
public void setProfession(String profession) {
	this.profession = profession;
}
public String getProfessionID() {
	return professionID;
}
public void setProfessionID(String professionID) {
	this.professionID = professionID;
}
public String getPassWord() {
	return passWord;
}
public void setPassWord(String passWord) {
	this.passWord = passWord;
}
public String getConpanyCode() {
	return conpanyCode;
}
public void setConpanyCode(String conpanyCode) {
	this.conpanyCode = conpanyCode;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public List<?> getmList() {
	return mList;
}
public void setmList(List<?> mList) {
	this.mList = mList;
}
@Override
public void onCreate() {
	super.onCreate();
}
 public void setServices(ReturnPartDatauploadService services)
 {
     this.services = services;
 }
 
 public ReturnPartDatauploadService getServices()
 {
     return services;
 }
}
