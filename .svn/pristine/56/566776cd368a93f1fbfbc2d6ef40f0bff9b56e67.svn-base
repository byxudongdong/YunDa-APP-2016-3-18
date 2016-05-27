package com.yunda.imp.cn;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.yunda.db.cn.DBHelper;
import com.yunda.interfaces.cn.ArrearsInterface;
import com.yunda.untils.ArrearsCompany;
import com.yunda.untils.Problem;
import com.yunda.untils.SqlDataupload;

public class ImpArrears implements ArrearsInterface{
	public static final String KEY_BIGNUMBER ="arrearcompany";
	public static final String KEY_NUMBER ="arrCode";
	private static final String PROFES="arrear";
	private DBHelper mDbHelper;
	private SQLiteDatabase mDatabase;
	private ContentValues initialValues;
	private List<ArrearsCompany> mList;
	public ImpArrears(Context context){
		mDbHelper=new DBHelper(context);
		mDatabase=mDbHelper.getWritableDatabase();
		 initialValues = new ContentValues();	
		 mList=new ArrayList<ArrearsCompany>();	
	}
	@Override
	public long createImpArrears(String arrearcompany,String dCode) {
		
		if (dCode!=null &&arrearcompany!=null) {
			initialValues.put(KEY_BIGNUMBER, arrearcompany);
			initialValues.put(KEY_NUMBER,Integer.parseInt(dCode));
			Log.i("createDataUpload", "bignumber="+dCode+"number="+arrearcompany);
		}
	
	return mDatabase.insert(PROFES, null, initialValues);
	}
	@Override
	public List<ArrearsCompany> QueryProfession(int maxResult,int firstResult) {
		Cursor cursor = mDatabase.rawQuery( " select * from "+PROFES+" limit ? offset ? " 
				, new String[]{String.valueOf(maxResult), String.valueOf(firstResult)});
	//	Cursor cursor=mDatabase.query(PROFES, null, null, null, null, null, null);
		while (cursor.moveToNext())
		{
		ArrearsCompany mArrearsCompany=new ArrearsCompany();
		mArrearsCompany.setArrCompany(cursor.getString(cursor.getColumnIndex(KEY_BIGNUMBER)));
		if (cursor.getString(cursor.getColumnIndex(KEY_NUMBER)).equals("0")) {
			mArrearsCompany.setCode("暂停 ");
		}else if (cursor.getString(cursor.getColumnIndex(KEY_NUMBER)).equals("1")) {
			mArrearsCompany.setCode("正常");
		}else if (cursor.getString(cursor.getColumnIndex(KEY_NUMBER)).equals("2")) {
			mArrearsCompany.setCode("预备关闭");
		}else if (cursor.getString(cursor.getColumnIndex(KEY_NUMBER)).equals("3")) {
			mArrearsCompany.setCode("欠费关闭");
		}else if (cursor.getString(cursor.getColumnIndex(KEY_NUMBER)).equals("4")) {
			mArrearsCompany.setCode("把枪关闭");
		}
		mList.add(mArrearsCompany);	
}
		return mList;
}

}
