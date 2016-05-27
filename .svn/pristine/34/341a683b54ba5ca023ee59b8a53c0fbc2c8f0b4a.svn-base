package com.yunda.imp.cn;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yunda.activity.cn.ProblemListActivity;
import com.yunda.db.cn.DBHelper;
import com.yunda.interfaces.cn.ProblemInterface;
import com.yunda.untils.CompanyList;
import com.yunda.untils.Problem;
import com.yunda.untils.SqlDataupload;

public class ImpProblem implements ProblemInterface{
	public static final String KEY_BIGNUMBER = "codenumber";
	public static final String KEY_NUMBER = "problem";
	private static final String PROFESSDATABASE="problemfreight";
	private DBHelper mDbHelper;
	private SQLiteDatabase mDatabase;
	private ContentValues initialValues;
	private  List<Problem> list ;
	public ImpProblem(Context context){
			mDbHelper=new DBHelper(context);
			mDatabase=mDbHelper.getWritableDatabase();
			initialValues = new ContentValues();
			list=new ArrayList<Problem>();
	}
	
	@Override
	
	public long createImpProblem(String codenumber, String problem) {
		Log.i("createDataUpload", "codenumber="+codenumber+"problem="+problem);
		if (codenumber!=null&&problem!=null) {
		initialValues.put(KEY_BIGNUMBER, codenumber);
		initialValues.put(KEY_NUMBER, problem);
		}
	return mDatabase.insert(PROFESSDATABASE, null, initialValues);	
	}
	@Override
	public List<Problem> QueryProfession() {
		Cursor cursor=mDatabase.query(PROFESSDATABASE, null, null, null, null, null, null);
		while (cursor.moveToNext())
		{
			Problem mProblem=new Problem();
			mProblem.setCode(cursor.getString(cursor.getColumnIndex(KEY_BIGNUMBER)));
			mProblem.setProblem(cursor.getString(cursor.getColumnIndex(KEY_NUMBER)));
			list.add(mProblem);
			
	}
		return list;
	}

}
