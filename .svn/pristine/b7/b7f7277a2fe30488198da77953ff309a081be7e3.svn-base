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
import com.yunda.interfaces.cn.ReturnPartData;
import com.yunda.untils.PartData;
import com.yunda.untils.ReturnPart;
import com.yunda.untils.SqlDataupload;
public class ImpReturnPartData implements ReturnPartData{
	public static final String KEY_USERID ="userid";
	public static final String KEY_TYPEWORKID ="typeworkid";
	public static final String KEY_TYPEWORK ="typework";
	public static final String KEY_NUMBER ="number";
	public static final String KEY_TIME ="time";
	public static final String KEY_FLAG ="flag";
	public static final String KEY_NAME ="name";
	public static final String KEY_CURRENTSITE ="currentsite";
	public static final String KEY_DATE ="date";
	public static final String KEY_BATCHNUMBER ="batchnumber";
	private static final String RETURNPARTDATA="returnpartdata";
	public static final String KEY_COMPANYNAME = "companyname";
	public static final String KEY_COMPANYCODE = "companycode";
	private static final String DATABASE_TABLE = "companylist";
	private DBHelper mDbHelper;
	private SQLiteDatabase mDatabase;
	private ContentValues initialValues;
	private List<PartData> mList;
	private  List<PartData> xList;
	private PartData mPartData;
	public ImpReturnPartData(Context context){
		mDbHelper=new DBHelper(context);
		 mList=new ArrayList<PartData>();
		 mDatabase=mDbHelper.getWritableDatabase();
		  xList=new ArrayList<PartData>();
	}
	@Override
	public long createReturnPartData(String userID,String typeworkid,String typework,
			String number, String time, int flag, String name,
			String currentsite, String date,String batchnumber) {
		Log.i("createReturnPartData", 
				"userID="+userID+"typeworkid="+typeworkid+"typework="+typework+"\r\n"
		+"number="+number+"time="+time+"\r\n"
						+"flag="+flag+"name="+name+"currentsite="+currentsite+"date="
		+date+"\r\n"+"batchnumber="+batchnumber);
		 initialValues = new ContentValues();
		 initialValues.put(KEY_USERID, userID);
		 initialValues.put(KEY_TYPEWORKID, typeworkid);
		initialValues.put(KEY_TYPEWORK, typework);
		initialValues.put(KEY_NUMBER, number);
		initialValues.put(KEY_TIME, time);
		initialValues.put(KEY_FLAG, flag);
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_CURRENTSITE, currentsite);
		initialValues.put(KEY_DATE, date);
		initialValues.put(KEY_BATCHNUMBER, batchnumber);
	long a=	mDatabase.insert(RETURNPARTDATA, null, initialValues);	
	Log.i("createReturnPartData", a+"");
		//mDatabase.close();	
		return a;
	}
	@Override
	public List<PartData> QueryProfession(String time) {
		mList.clear();
		Log.i("QueryProfession", time);
		Cursor cursor = mDatabase.rawQuery("SELECT * FROM"+" "+RETURNPARTDATA+" "+"WHERE "+KEY_DATE+"=?",new String[]{time});
		while (cursor.moveToNext()) {
			mPartData=new PartData();
		mPartData.setCurrentsite(cursor.getString(cursor.getColumnIndex(KEY_CURRENTSITE)));
		mPartData.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
		mPartData.setFlag(cursor.getString(cursor.getColumnIndex(KEY_FLAG)));
		mPartData.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
		mPartData.setNumber(cursor.getString(cursor.getColumnIndex(KEY_NUMBER)));
		mPartData.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
		mPartData.setTypework(cursor.getString(cursor.getColumnIndex(KEY_TYPEWORK)));
		mPartData.setTypeworkid(cursor.getString(cursor.getColumnIndex(KEY_TYPEWORKID)));
		mPartData.setUserid(cursor.getString(cursor.getColumnIndex(KEY_USERID)));
		mPartData.setBatchNumber(cursor.getString(cursor.getColumnIndex(KEY_BATCHNUMBER)));
		mList.add(mPartData);
			
		}
		Log.i("setOnClickListener", mList.size()+"");	
		//mDatabase.close();	
		return mList;
		
	}
	public List<PartData> QueryProfessi(String flag) {
		Cursor cursor = mDatabase.rawQuery("SELECT * FROM"+" "+RETURNPARTDATA+" "+"WHERE "+KEY_FLAG+"=?",new String[]{flag});
		while (cursor.moveToNext()) {
			mPartData=new PartData();
		mPartData.setCurrentsite(cursor.getString(cursor.getColumnIndex(KEY_CURRENTSITE)));
		mPartData.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
		mPartData.setFlag(cursor.getString(cursor.getColumnIndex(KEY_FLAG)));
		mPartData.setName(cursor.getString(cursor.getColumnIndex(KEY_NAME)));
		mPartData.setNumber(cursor.getString(cursor.getColumnIndex(KEY_NUMBER)));
		mPartData.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
		mPartData.setTypework(cursor.getString(cursor.getColumnIndex(KEY_TYPEWORK)));
		mPartData.setTypeworkid(cursor.getString(cursor.getColumnIndex(KEY_TYPEWORKID)));
		mPartData.setUserid(cursor.getString(cursor.getColumnIndex(KEY_USERID)));
		mPartData.setBatchNumber(cursor.getString(cursor.getColumnIndex(KEY_BATCHNUMBER)));
			xList.add(mPartData);
		}
		Log.i("setOnClickListener", mList.size()+"");	
		//mDatabase.close();	
		return mList;
		
	}
	public Cursor getDiary(String companycode) throws SQLException {
		String[] columns={KEY_COMPANYNAME};
		String string=KEY_COMPANYCODE+"="+"?";
		String [] strings={companycode};                                                            
		Cursor mCursor=	mDatabase.query(DATABASE_TABLE, columns, string, strings, null, null, null);
		return mCursor;
	}
	public void deleteInformation(String number){
		mDatabase.execSQL("delete from"+" "+ RETURNPARTDATA+" " +"where"+" " +KEY_NUMBER+"=?",new Object[]{number});
		//mDatabase.close();
	}
	/*
	 * 修改語句
	 */
	public void updateInformation(String number,int a){
		mDatabase.execSQL("update"+" "+RETURNPARTDATA+" "+"set"+" "+KEY_FLAG+"=?"+" "+"where"+" "+KEY_NUMBER+"=?",new Object[]{a,number});
		//mDatabase.close();
		}
}
