package com.yunda.interfaces.cn;

import java.util.List;

import com.yunda.activity.cn.ProblemListActivity;
import com.yunda.untils.Problem;
public interface ProblemInterface {
	public long createImpProblem(String dCode, String reason);
	public List<Problem> QueryProfession();	

}
