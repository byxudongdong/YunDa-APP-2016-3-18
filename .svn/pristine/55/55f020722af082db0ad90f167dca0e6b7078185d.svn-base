package com.yunda.interfaces.cn;

import java.util.List;
import com.yunda.untils.CompanyList;
import com.yunda.untils.User;
import com.yunda.untils.UserList;

import android.database.Cursor;
import android.database.SQLException;
//定义接口插入和查询数据库的接口
public interface UserRegistration {
	public long createUser(String companyname, String companycode);
	public Cursor getAllNotes();
	public Cursor getDiary(String companycode) throws SQLException;
	public List<CompanyList> companyLists(int firstResult,int maxResult);
	public long insertUser(String username, String usercode);
	public List<UserList> userLists(int firstResult,int maxResult);
	
	
	
	
	
	
	
	
}
