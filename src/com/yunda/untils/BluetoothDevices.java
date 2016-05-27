package com.yunda.untils;

public class BluetoothDevices  {
private String name;
private String address;
private boolean flag;
private int n;//判断是否配对过
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public boolean isFlag() {
	return flag;
}
public void setFlag(boolean flag) {
	this.flag = flag;
}
public int getN() {
	return n;
}
public void setN(int n) {
	this.n = n;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((address == null) ? 0 : address.hashCode());
	result = prime * result + (flag ? 1231 : 1237);
	result = prime * result + n;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	BluetoothDevices other = (BluetoothDevices) obj;
	if (address == null) {
		if (other.address != null)
			return false;
	} else if (!address.equals(other.address))
		return false;
	if (flag != other.flag)
		return false;
	if (n != other.n)
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}

}
