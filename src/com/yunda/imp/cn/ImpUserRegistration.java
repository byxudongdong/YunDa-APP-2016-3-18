package com.yunda.imp.cn;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yunda.db.cn.DBHelper;
import com.yunda.interfaces.cn.UserRegistration;
import com.yunda.untils.CompanyList;
import com.yunda.untils.User;
import com.yunda.untils.UserList;
public class ImpUserRegistration implements UserRegistration {
	public static final String KEY_COMPANYNAME = "companyname";
	public static final String KEY_COMPANYCODE = "companycode";
	
	public static final String KEY_ROWID = "_id";
	private static final String DATABASE_TABLE = "companylist";
	private static final String USERDATABASE="stafflist";
	public static final String KEY_USERNAME = "username";
	public static final String KEY_USERCODE = "usercode";
	private DBHelper mDbHelper;
	private SQLiteDatabase mDatabase;
	private ContentValues initialValues;
	private  List<CompanyList> list ;
	private List<UserList> userList;
	public ImpUserRegistration(Context context){
		mDbHelper=new DBHelper(context);
		mDatabase=mDbHelper.getWritableDatabase();
		 initialValues = new ContentValues();
		 list = new ArrayList<CompanyList>();
		 userList=new ArrayList<UserList>();
	}
	@Override
	public long createUser(String companyname, String companycode) {
			initialValues.put(KEY_COMPANYNAME, companyname);
			initialValues.put(KEY_COMPANYCODE, companycode);
			Log.i("createUser", companycode+"="+companyname);
		return mDatabase.insert(DATABASE_TABLE, null, initialValues);	
	}
	@Override
	public Cursor getAllNotes() {
		return mDatabase.query(DATABASE_TABLE, new String[] { KEY_ROWID, KEY_COMPANYNAME,
				KEY_COMPANYCODE }, null, null, null, null, null);
	}
	public void closeclose() {
		mDbHelper.close();
	}
	@Override
	public Cursor getDiary(String companycode) throws SQLException {
		String[] columns={KEY_COMPANYNAME};
		String string=KEY_COMPANYCODE+"="+"?";
		String [] strings={companycode};
		Cursor mCursor=	mDatabase.query(DATABASE_TABLE, columns, string, strings, null, null, null);
//		Cursor mCursor =mDatabase.query(true, DATABASE_TABLE, new String[]{ KEY_COMPANYNAME
// }, KEY_COMPANYNAME + "='" + companycode+"'", null, null,
//						null, null, null);
//				if (mCursor != null) {
//					mCursor.moveToFirst();
//				}
		return mCursor;
	}
	@Override
	public List<CompanyList> companyLists(int maxResult,int firstResult) {
		Cursor cursor = mDatabase.rawQuery( " select * from "+DATABASE_TABLE+" limit ? offset ? " 
				, new String[]{String.valueOf(maxResult), String.valueOf(firstResult)});
	//	Cursor cursor=mDatabase.query(DATABASE_TABLE, null, null, null, null, null, null);
		while (cursor.moveToNext())
			{
				CompanyList mCompany = new CompanyList();
				mCompany.setComPanyCode(cursor.getString(cursor.getColumnIndex(KEY_COMPANYCODE)));
				mCompany.setComPanyName(cursor.getString(cursor.getColumnIndex(KEY_COMPANYNAME)));
				list.add(mCompany);
				
		}
//		cursor.close();
//		mDatabase.close();
		return list;
	}
	@Override
	public long insertUser(String username, String usercode) {
		initialValues.put(KEY_USERNAME, username);
		initialValues.put(KEY_USERCODE, usercode);
		return mDatabase.insert(USERDATABASE, null, initialValues);
	}
	@Override
	public List<UserList> userLists(int firstResult, int maxResult) {
		Log.i("userLists", "userLists");
		Cursor cursor = mDatabase.rawQuery( " select * from "+USERDATABASE+" limit ? offset ? " 
				, new String[]{String.valueOf(maxResult), String.valueOf(firstResult)});
		//Cursor cursor=mDatabase.query(USERDATABASE, null, null, null, null, null, null);
		while (cursor.moveToNext())
			{
			UserList mUser = new UserList();
				mUser.setUserCode(cursor.getString(cursor.getColumnIndex(KEY_USERCODE)));
				mUser.setUserName(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
				userList.add(mUser);
				
		}
		Log.i("userLists", userList.size()+"");
		return userList;
	}
	
}
