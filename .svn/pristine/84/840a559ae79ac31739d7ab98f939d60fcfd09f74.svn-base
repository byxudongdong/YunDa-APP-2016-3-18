package com.yunda.untils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class BagPackageNumber {
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
//<dta"+" "+"BagPackageNumber="+BagPackageNumber+">"+"\r\n"+"<h>"+"<ver>"+ComPanyCode+"</ver>"+"<time>"+time+"</time>"
//+"<station>"+nextStation+"</station>"+"</h>"+"\r\n"+"<data>"+"\r\n"+mStringBuffer.toString()+"\r\n"+"</data>"+"\r\n"
//+"</dta>";

@Override
public String toString() {
	StringBuffer mStringBuffer=new StringBuffer();
	Iterator it = Waybillnumber.entrySet().iterator();
while (it.hasNext()) {
	Map.Entry entry = (Map.Entry) it.next();
	mStringBuffer.append("<o>"+"\r\n"+"<Waybillnumber>"+entry.getKey()+"</Waybillnumber>"+"\r\n"+"<time>"+entry.getValue()+"</time>"+"\r\n"+"</o>"+"\r\n");		
}
	if (Waybillnumber.size()!=0&&BagPackageNumber.length()>0&&ComPanyCode.length()>0&&nextStation.length()>0) {
		return "<req"+" op"+"="+"\"op12amb\""+">"+"\r\n"+"<h>"+"\r\n"+"<ver>"+"2.0"+"</ver>"+"\r\n"+"<company>"+ComPanyCode+"</company>"
	+"\r\n"+"<user>"+user+"</user>"+"\r\n"+ " <pass>"+"password"+"</pass>"+"\r\n"+"<dev1>"+"53201209315222"+"</dev1>"+"\r\n"
	+ "<dev2>"+"1001"+"</dev2>"+"\r\n"+"<data>"+"\r\n"+"<BagPackageNumber>"+BagPackageNumber+"</BagPackageNumber>"+"\r\n"+"<station>"+nextStation+"</station>"+"\r\n"
	+"<d>"+"\r\n"+mStringBuffer.toString()+"</d>"+"\r\n"+"</data>"+"\r\n"+"</h>"+"\r\n"+"</req>";
	
	}
	return "123456";
}

}
