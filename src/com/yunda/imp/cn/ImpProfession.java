package com.yunda.imp.cn;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yunda.db.cn.DBHelper;
import com.yunda.interfaces.cn.Profession;
import com.yunda.untils.CompanyList;
import com.yunda.untils.UserList;

public class ImpProfession implements Profession{
	public static final String KEY_CODENUMBER = "codenumber";
	public static final String KEY_PROFESSION = "profession";
	public static final String KEY_ROWID = "_id";
	private static final String PROFESSDATABASE="professionlist";
	private DBHelper mDbHelper;
	private SQLiteDatabase mDatabase;
	private ContentValues initialValues;
	public ImpProfession(Context context){
		mDbHelper=new DBHelper(context);
		mDatabase=mDbHelper.getWritableDatabase();
		 initialValues = new ContentValues();
	}
	@Override
	public long create(String codenumber, String profession) {
		initialValues.put(KEY_CODENUMBER, codenumber);
		initialValues.put(KEY_PROFESSION, profession);
		Log.i("createUser", codenumber+"="+profession);
	return mDatabase.insert(PROFESSDATABASE, null, initialValues);	
	}
	@Override
	public Cursor QueryProfession() {
		Cursor cursor=mDatabase.query(PROFESSDATABASE, null, null, null, null, null, null);
		
		return cursor;
	}

}
