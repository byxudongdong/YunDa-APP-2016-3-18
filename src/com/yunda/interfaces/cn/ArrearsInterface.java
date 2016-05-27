package com.yunda.interfaces.cn;

import java.util.List;

import com.yunda.activity.cn.ProblemListActivity;
import com.yunda.untils.ArrearsCompany;
import com.yunda.untils.Problem;
public interface ArrearsInterface {
	public long createImpArrears(String arrearcompany, String arrCode);
	public List<ArrearsCompany> QueryProfession(int maxResult,int firstResult);	

}
