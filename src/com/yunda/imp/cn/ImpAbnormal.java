package com.yunda.imp.cn;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yunda.db.cn.DBHelper;
import com.yunda.interfaces.cn.AbnormalInterface;
import com.yunda.untils.Abnorma;
import com.yunda.untils.Problem;

public class ImpAbnormal implements AbnormalInterface{
	public static final String KEY_BIGNUMBER = "dCode";
	public static final String KEY_NUMBER = "reason";
	private static final String PROFESSDATABASE="abnormal";
	private DBHelper mDbHelper;
	private SQLiteDatabase mDatabase;
	private ContentValues initialValues;
	private  List<Abnorma> list ;
public  ImpAbnormal(Context context){
	mDbHelper=new DBHelper(context);
	mDatabase=mDbHelper.getWritableDatabase();
	initialValues = new ContentValues();
	list=new ArrayList<Abnorma>();
}
	@Override
	public long create(String dCode, String reason) {
		Log.i("createDataUpload", "codenumber="+dCode+"problem="+reason);
		if (dCode!=null&&reason!=null) {
			initialValues.put(KEY_BIGNUMBER, dCode);
			initialValues.put(KEY_NUMBER, reason);	
		}
		
	return mDatabase.insert(PROFESSDATABASE, null, initialValues);	
	}

	@Override
	public List<Abnorma> QueryProfession() {
		Cursor cursor=mDatabase.query(PROFESSDATABASE, null, null, null, null, null, null);
		while (cursor.moveToNext())
		{
			Abnorma mProblem=new Abnorma();
			mProblem.setdCode(cursor.getString(cursor.getColumnIndex(KEY_BIGNUMBER)));
			mProblem.setReason(cursor.getString(cursor.getColumnIndex(KEY_NUMBER)));
			list.add(mProblem);
			
	}
		return list;
	}

}
