package com.yunda.untils;

public class ReturnPart {
private String number;
private String time;
private String BatchNumber;
private int flag;
public int getFlag() {
	return flag;
}

public void setFlag(int flag) {
	this.flag = flag;
}

public String getNumber() {
	return number;
}

public void setNumber(String number) {
	this.number = number;
}

public String getTime() {
	return time;
}

public void setTime(String time) {
	this.time = time;
}

public String getBatchNumber() {
	return BatchNumber;
}

public void setBatchNumber(String batchNumber) {
	BatchNumber = batchNumber;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
			+ ((BatchNumber == null) ? 0 : BatchNumber.hashCode());
	result = prime * result + flag;
	result = prime * result + ((number == null) ? 0 : number.hashCode());
	result = prime * result + ((time == null) ? 0 : time.hashCode());
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
	ReturnPart other = (ReturnPart) obj;
	if (BatchNumber == null) {
		if (other.BatchNumber != null)
			return false;
	} else if (!BatchNumber.equals(other.BatchNumber))
		return false;
	if (flag != other.flag)
		return false;
	if (number == null) {
		if (other.number != null)
			return false;
	} else if (!number.equals(other.number))
		return false;
	if (time == null) {
		if (other.time != null)
			return false;
	} else if (!time.equals(other.time))
		return false;
	return true;
}
}
