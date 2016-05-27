package com.yunda.imp.cn;
import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.Long3;
import android.util.Log;
import com.yunda.db.cn.DBHelper;
import com.yunda.interfaces.cn.DataUpload;
import com.yunda.untils.PacketInforMation;
import com.yunda.untils.SqlDataupload;
public class ImpDataUpload implements DataUpload{
	public static final String KEY_BIGNUMBER = "bignumber";
	public static final String KEY_NUMBER = "number";
	public static final String KEY_ROWID = "flag";
	private static final String KEY_TIME="time";
	private static final String PROFESSDATABASE="dataUpload";
	private static final String KEY_USERID ="userid";
	private static final String KEY_NEXTSTATION="nextstation";
	private static final String KEY_BATCHNUMBER="batchnumber";
	private static final String KEY_NEXTCODE="nextcode";
	private DBHelper mDbHelper;
	private SQLiteDatabase mDatabase;
	private ContentValues initialValues;
	private SqlDataupload sqlDataupload;
	private List<SqlDataupload> mList;
	private long a;
	public ImpDataUpload(Context context){
		mDbHelper=new DBHelper(context);
		mList=new ArrayList<SqlDataupload>();
	}
	@Override
	public long createDataUpload(String bignumber, String number, String time,
			int flag ,String name,String nextstation ,String BatchNumber,String nextcode) {
		Log.i("createDataUpload", "bignumber="+bignumber+"number="+number+"time="+time+"flag="+flag+" "+nextstation);
			mDatabase=mDbHelper.getWritableDatabase();
			 initialValues = new ContentValues();	
			initialValues.put(KEY_BIGNUMBER, bignumber);
			initialValues.put(KEY_NUMBER, number);
			initialValues.put(KEY_TIME, time);
			initialValues.put(KEY_ROWID, flag);
			initialValues.put(KEY_USERID, name);
			initialValues.put(KEY_NEXTSTATION, nextstation);
			initialValues.put(KEY_BATCHNUMBER, BatchNumber);
			initialValues.put(KEY_NEXTCODE, nextcode);
			a =mDatabase.insert(PROFESSDATABASE, null, initialValues);	
			mDatabase.close();	
			return a;
	}
	public List<PacketInforMation>  getInformation(String bignumber) {
		List<PacketInforMation> nList=new ArrayList<PacketInforMation>();
		mDatabase=mDbHelper.getWritableDatabase();
		Log.i("QueryProfession", bignumber);
		Cursor cursor = mDatabase.rawQuery("SELECT * FROM"+" "+PROFESSDATABASE+" "+"WHERE "+KEY_BIGNUMBER+"=?",new String[]{bignumber});
		while (cursor.moveToNext()) {
		PacketInforMation 	fo=new PacketInforMation();
			fo.setNextstationName(cursor.getString(cursor.getColumnIndex(KEY_NEXTSTATION)));
			fo.setNextstation(cursor.getString(cursor.getColumnIndex(KEY_NEXTCODE)));
			nList.add(fo);
		}
		Log.i("setOnClickListener", nList.size()+"");	
		mDatabase.close();	
		return nList;
	}
	@Override
	public List<SqlDataupload>  QueryProfession(String time) {
		mList.clear();
		mDatabase=mDbHelper.getWritableDatabase();
		Log.i("QueryProfession", time);
		Cursor cursor = mDatabase.rawQuery("SELECT * FROM"+" "+PROFESSDATABASE+" "+"WHERE "+KEY_TIME+"=?",new String[]{time});
		while (cursor.moveToNext()) {
			sqlDataupload=new SqlDataupload();
			sqlDataupload.setBignumber(cursor.getString(cursor.getColumnIndex(KEY_BIGNUMBER)));
			sqlDataupload.setNumber(cursor.getString(cursor.getColumnIndex(KEY_NUMBER)));
			sqlDataupload.setFlag(cursor.getInt(cursor.getColumnIndex(KEY_ROWID)));
			sqlDataupload.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
			sqlDataupload.setName(cursor.getString(cursor.getColumnIndex(KEY_USERID)));
			sqlDataupload.setNextstation(cursor.getString(cursor.getColumnIndex(KEY_NEXTSTATION)));
			sqlDataupload.setBatchNumber(cursor.getString(cursor.getColumnIndex(KEY_BATCHNUMBER)));
			sqlDataupload.setNextstationCode(cursor.getString(cursor.getColumnIndex(KEY_NEXTCODE)));
			mList.add(sqlDataupload);
			
		}
		Log.i("setOnClickListener", mList.size()+"");	
		mDatabase.close();	
		return mList;
	}	
	/*
	 * 刪除語句
	 */
	public void deleteInformation(String number){
		mDatabase=mDbHelper.getWritableDatabase();
		mDatabase.execSQL("delete from"+" "+ PROFESSDATABASE+" " +"where"+" " +KEY_NUMBER+"=?",new Object[]{number});
		//mDatabase.close();
	}
	/*
	 * 修改語句
	 */
	public void updateInformation(String number,int a){
		mDatabase=mDbHelper.getWritableDatabase();
		mDatabase.execSQL("update"+" "+PROFESSDATABASE+" "+"set"+" "+KEY_ROWID+"=?"+" "+"where"+" "+KEY_NUMBER+"=?",new Object[]{a,number});
		mDatabase.close();
		}
}
