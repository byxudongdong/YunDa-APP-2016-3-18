package com.yunda.db.cn;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	//建表
	private static final String DATABASE_CREATE = "create table companylist (_id integer primary key autoincrement, "
			+ "companyname text not null UNIQUE, companycode text not null UNIQUE);";
	//建表
	private static final String USERTABASE_CREATE = "create table stafflist (_id integer primary key autoincrement, "
				+ "username text not null UNIQUE, usercode text not null UNIQUE);";
	//建表
	private static final String PROFESSRTABASE_CREATE = "create table professionlist (_id integer primary key autoincrement, "
						+ "codenumber text not null UNIQUE, profession text not null UNIQUE);";	
	//建表
	private static final String DATAUPLOAD = "create table dataUpload (_id integer primary key autoincrement, "
							+ "bignumber varchar , number varchar,time varchar ,flag integer , userid varchar,nextstation varchar,batchnumber varchar," +
							"nextcode varchar);";	
	//建表
	private static final String PROBLEMFREIGHT = "create table problemfreight (_id integer primary key autoincrement, "
									+ "codenumber text not null UNIQUE, problem text not null UNIQUE);";
	//建表
	private static final String ABNORMAL = "create table abnormal (_id integer primary key autoincrement, "
	
			+ "dCode text not null UNIQUE, reason text not null UNIQUE);";	
	//建表
	private static final String ARREARCONPANY = "create table arrear (_id integer primary key autoincrement, "
									+ "arrearcompany varchar UNIQUE, arrCode integer );";	
		//建表
private static final String RETURNPARTDATA = "create table returnpartdata (_id integer primary key autoincrement, "
								+ "userid varchar, typeworkid varchar , " +
								"typework varchar , number varchar  ,time varchar ," +
								" flag integer,name varchar,currentsite varchar,batchnumber varchar," +
								"date varchar);";
//建表
private static final String LOADINGSCAN = "create table loadingscan (_id integer primary key autoincrement, "
						+ "saomiaoleixing varchar, wupinleibie varchar , " +
						"fachepinzheng varchar , xiayizhandian varchar  ,gongzhong varchar ," +
						" zhuangchewei varchar,name varchar,currentsite varchar,nextsite varchar,Itemcategory varchar,fachepinzheng varchar,fachepinzheng varchar" +
						"date varchar);";
	//数据库名字
	private static final String DATABASE_NAME ="database";
	//版本号
	private static final int DATABASE_VERSION = 1;
	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
		db.execSQL(USERTABASE_CREATE);
		db.execSQL(PROFESSRTABASE_CREATE);
		db.execSQL(DATAUPLOAD);
		db.execSQL(PROBLEMFREIGHT);
		db.execSQL(ABNORMAL);
		db.execSQL(ARREARCONPANY);
		db.execSQL(RETURNPARTDATA);
		//db.execSQL(LOADINGSCAN);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);
	}
}
