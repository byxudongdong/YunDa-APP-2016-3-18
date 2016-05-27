package com.yunda.untils;
import java.util.Map;
public class XMLBagPackageNumber {
private Map<String ,String> Waybillnumber;//运单号
private String BagPackageNumber="";//大包号
private String ComPanyCode="";//公司编码
private String nextStation="";//下一站
private String time;//扫描时间
private String user;//扫描人

public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getUser() {
	return user;
}
public void setUser(String user) {
	this.user = user;
}
public Map<String ,String> getWaybillnumber() {
	return Waybillnumber;
}
public void setWaybillnumber(Map<String ,String> waybillnumber) {
	Waybillnumber = waybillnumber;
}
public String getBagPackageNumber() {
	return BagPackageNumber;
}
public void setBagPackageNumber(String bagPackageNumber) {
	BagPackageNumber = bagPackageNumber;
}
public String getComPanyCode() {
	return ComPanyCode;
}
public void setComPanyCode(String comPanyCode) {
	ComPanyCode = comPanyCode;
}
public String getNextStation() {
	return nextStation;
}
public void setNextStation(String nextStation) {
	this.nextStation = nextStation;
}

}
