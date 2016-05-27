package com.yunda.activity.cn;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.Inflater;

import org.apache.http.entity.StringEntity;
import org.xmlpull.v1.XmlPullParser;

import com.example.yunda_app_2016_3_18.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yunda.imp.cn.ImpDataUpload;
import com.yunda.untils.ListData;
import com.yunda.untils.MD5transformation;
import com.yunda.untils.MyApplication;
import com.yunda.untils.PacketDataXml;
import com.yunda.untils.ReadFileSN;
import com.yunda.untils.ReturnPart;
import com.yunda.untils.ReturnPartXml;
import com.yunda.untils.SqlDataupload;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.Xml;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SearchrecrodActivity extends Activity implements OnItemClickListener{
	Button search;
	Spinner spinner;
	ListView listView;
	String str;
	public String[] date = { "今天", "昨天", "前天", "任意一天" };
	//ImpDataUpload db;
	List<SqlDataupload> list;// 实体类的各个实体
	BaseAdapter adapter;
	public DatePickerDialog d;
	String times="";
	SqlDataupload dataupload;
	private static  String URL_INTENT = "";
	private ImpDataUpload mImpDataUpload;
	private MyApplication application;
	private SharedPreferences ps;
	private List<SqlDataupload> kList;
	private void XmlPersor(String result) {
		XmlPullParser parser = Xml.newPullParser();
		String st = null,res = null;
		try {
			parser.setInput(new ByteArrayInputStream(result.getBytes("UTF-8")), "UTF-8");
			int eventType =parser.getEventType();
			while (eventType!=XmlPullParser.END_DOCUMENT) {
				if (eventType==XmlPullParser.START_TAG) {
					if ("dta".equals(parser.getName())) {
						st=parser.getAttributeValue(0);
						res=parser.getAttributeValue(1);
					}
				}

			 eventType = parser.next();	
			}	
	 if (st.equals("ok")) {
		
		 Toast.makeText(this, "上传成功！"+res+"条", 10).show();
		 Iterator<SqlDataupload> itt=kList.iterator();
		 int resultttt =Integer.parseInt(res);
		 while (itt.hasNext()&&resultttt>0) {
			 SqlDataupload sq=itt.next();
			 mImpDataUpload.updateInformation(sq.getNumber(),1);
			 Log.i("updateInformation", sq.getNumber());
			 	itt.remove();	
			 	resultttt--;	 
		 }
		 //Log.i("mImpDataUpload", dataupload.getNumber());
		 list=mImpDataUpload.QueryProfession(getDates());
		 	adapter.notifyDataSetChanged();
	 }
     } catch (Exception e) {
		e.printStackTrace();
	}	

			
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_searchrecrod);
		init();
		spinner.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				convertView = LayoutInflater.from(SearchrecrodActivity.this).inflate(R.layout.date, null);
				((TextView) convertView.findViewById(R.id.text)).setText(date[position]);
				return convertView;
			}

			@Override
			public long getItemId(int position) {
				return position;
			}

			@Override
			public Object getItem(int position) {
				return date[position];
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return date.length;
			}
		});
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				search.setTag(position);// 传给search所点击的那个数据
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		search.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int position = (int) v.getTag();
				switch (position) {
				case 0:
					list=mImpDataUpload.QueryProfession(getDates());
					Log.i("setOnClickListener", list.size()+"");
					adapter.notifyDataSetChanged();
					break;
				case 1:
					list=mImpDataUpload.QueryProfession(yesTerDay());
					adapter.notifyDataSetChanged();
					break;
				case 2:
					list=mImpDataUpload.QueryProfession(beforeYesterDay());
					adapter.notifyDataSetChanged();
					break;
				case 3:
					Calendar c = Calendar.getInstance();
                    c.setTime(new Date());
					if (d == null) {
						d = new DatePickerDialog(SearchrecrodActivity.this, new OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
								Calendar calendar = Calendar.getInstance();
								calendar.set(year, monthOfYear, dayOfMonth);
								long time = calendar.getTimeInMillis() ;
								Date date=new Date(time);
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
									times = format.format(date);
									Log.e("tag", times+"");
									list=mImpDataUpload.QueryProfession(times);
									adapter.notifyDataSetChanged();
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
					}
					d.setButton(DatePickerDialog.BUTTON_POSITIVE, "确认", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							
						}
					});
					d.show();
					break;

				default:
					break;
				}
				System.out.println(list.size());
			}
		});
		listView.setAdapter(adapter = new BaseAdapter() {

			@SuppressWarnings("unchecked")
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder holder = null;
				if (convertView == null) {
					holder = new ViewHolder();
					convertView = LayoutInflater.from(SearchrecrodActivity.this).inflate(R.layout.dateitem, null);
					holder.yiguo = (TextView) convertView.findViewById(R.id.yiguo);
					holder.yundanhao = (TextView) convertView.findViewById(R.id.yundanhao);
					convertView.setTag(holder);
				} else {
					holder = (ViewHolder) convertView.getTag();
				}
				SqlDataupload dataupload = list.get(position);
				holder.yiguo.setText(dataupload.getFlag() == 0 ? "未" : "已");// 判断flag是上传还是没有上传
				if (dataupload.getFlag() == 0) {
					kList.add(dataupload);
				}
				
				holder.yundanhao.setText(dataupload.getNumber());
				return convertView;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return list.size();
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return list.size();
			}

		});
	}

	public class ViewHolder {
		TextView yiguo;
		TextView yundanhao;
	}
	public static boolean isNetAvailable(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);  
	    NetworkInfo info = manager.getActiveNetworkInfo();
	    return (info != null && info.isAvailable());
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	switch (keyCode) {
		case 131://F1再次上传	
			Log.i("isNetAvailable", "isNetAvailable = "+isNetAvailable(SearchrecrodActivity.this));
			if(!isNetAvailable(SearchrecrodActivity.this)){
				Log.i("isNetAvailable", "网络不通，请稍候上传");
				Toast.makeText(SearchrecrodActivity.this, "网络不通，请稍候上传", 1000).show();
				return true;
			}
requestIntent(new SqlDataupload(kList, application.getConpanyCode(), application.getUserID(), application.getProfessionID(), getDates(), application.getPassWord()).toString());	
	break;
		case 132://F2锁屏
			Intent w16Intent=new Intent();
			ComponentName mName=new ComponentName("com.example.hellojni", "com.example.hellojni.HelloJni");
			w16Intent.setComponent(mName);
			w16Intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(w16Intent);
		break;
		case 133://F3解锁
			Intent wifiIntent=new Intent();
			wifiIntent.setAction("android.settings.WIFI_SETTINGS");
			startActivity(wifiIntent);		
			break;
		
	}
	Log.i("onKeyDown", keyCode+"");
	
		return super.onKeyDown(keyCode, event);
	}
	public void init() {
		mImpDataUpload=new ImpDataUpload(this);
		spinner = (Spinner) findViewById(R.id.spinner);
		search = (Button) findViewById(R.id.search);
	//	back = (Button) findViewById(R.id.back_button);
		listView = (ListView) findViewById(R.id.dlistview);
		list=mImpDataUpload.QueryProfession(getDates());
		listView.setOnItemClickListener(this);
		application=(MyApplication) getApplication();
		ps=getSharedPreferences("host", MODE_PRIVATE);
		URL_INTENT=ps.getString("datadend","http://qz.yundasys.com:9900/ws/ws.jsp");
		kList=new ArrayList<SqlDataupload>();
	}
	
	// 获取当前日期
	public String getDates() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(date);
		return time;
	}
	// 获取昨天日期
	public String yesTerDay() {
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		System.out.println("昨天:" + f.format(c.getTime()));
		c.add(Calendar.DAY_OF_MONTH, -1);
		String time = f.format(c.getTime());
		return time;
	}

	// 获取前天日期
	public String beforeYesterDay() {
		Format f = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		System.out.println("前天:" + f.format(c.getTime()));
		c.add(Calendar.DAY_OF_MONTH, -2);
		String time = f.format(c.getTime());
		return time;
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
				AlertDialog( position);
		
	}	
	public void AlertDialog(final int position) {
		LayoutInflater inflater=LayoutInflater.from(SearchrecrodActivity.this);
		View view =	inflater.inflate(R.layout.view, null);
		TextView textView1=(TextView) view.findViewById(R.id.textView2);
		TextView textView2=(TextView) view.findViewById(R.id.textView4);
		TextView textView3=(TextView) view.findViewById(R.id.textView6);
		TextView textView4=(TextView) view.findViewById(R.id.textView10);
		TextView textView5=(TextView) view.findViewById(R.id.textView11);
		TextView textView6=(TextView) view.findViewById(R.id.textView19);
		textView1.setText(list.get(position).getName());
		textView2.setText(list.get(position).getBignumber());
		textView3.setText(list.get(position).getNumber());
		textView4.setText(list.get(position).getTime());
		textView6.setText(list.get(position).getNextstation());
		textView5.setText(list.get(position).getFlag()==0 ?"未上传":"已上传");
		Builder builder = new AlertDialog.Builder(SearchrecrodActivity.this);
		builder.setView(view);
		builder.setTitle("运单详情");	
		builder.setIcon(R.drawable.yundaicon);
		builder.setNegativeButton("关闭", null);
		builder.show();
	}
	// 获取当前时间
	public String getDate() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(date);
		return time;
	}
	public void requestIntent(String xml){
		HttpUtils mHttpUtils = new HttpUtils(5000);
		RequestParams mRequestParams = new RequestParams();
		try {
			mRequestParams.setBodyEntity(new StringEntity(xml,"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mHttpUtils.send(HttpMethod.POST, URL_INTENT,mRequestParams,
				new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						
						Log.i("onSuccessshangchuan", arg0.result);
							//xml解析
						XmlPersor( arg0.result);
					}

					
				});
	};	
	
	
}
