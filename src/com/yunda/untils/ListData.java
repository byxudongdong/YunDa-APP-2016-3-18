package com.yunda.untils;

public class ListData {
private String bigNumber;
private String number;
private String BatchNumber;
private String nextStation;
public String getNextStation() {
	return nextStation;
}
public void setNextStation(String nextStation) {
	this.nextStation = nextStation;
}
public String getBatchNumber() {
	return BatchNumber;
}
public void setBatchNumber(String batchNumber) {
	BatchNumber = batchNumber;
}
public String getBigNumber() {
	return bigNumber;
}
public void setBigNumber(String bigNumber) {
	this.bigNumber = bigNumber;
}
public String getNumber() {
	return number;
}
public void setNumber(String number) {
	this.number = number;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((bigNumber == null) ? 0 : bigNumber.hashCode());
	result = prime * result + ((number == null) ? 0 : number.hashCode());
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
	ListData other = (ListData) obj;
	if (bigNumber == null) {
		if (other.bigNumber != null)
			return false;
	} else if (!bigNumber.equals(other.bigNumber))
		return false;
	if (number == null) {
		if (other.number != null)
			return false;
	} else if (!number.equals(other.number))
		return false;
	return true;
}

}
