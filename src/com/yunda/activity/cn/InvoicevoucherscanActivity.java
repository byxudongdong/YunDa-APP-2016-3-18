package com.yunda.activity.cn;

import com.example.yunda_app_2016_3_18.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class InvoicevoucherscanActivity extends Activity {
	public String[] date = { "货样", "非货样" };
	CheckBox box;
	Spinner spinner;
	EditText weighteditect;
	EditText locationedittext;
	EditText departvoucheredittext,truckScannednumber;//发车凭证
	EditText truckNextStation,truckNextStationcontent,trucknumbercontent;
	Button truckupdate,truckdelete,invoicebackbutton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.invoicevoucherscan);
		init();
		spinner.setAdapter(new BaseAdapter() {
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				convertView = LayoutInflater.from(InvoicevoucherscanActivity.this).inflate(R.layout.date, null);
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
		//事件还没有做
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(InvoicevoucherscanActivity.this, "你点击的是"+date[position],Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
	}
	public void init(){
		box=(CheckBox)findViewById(R.id.invicecheckbox);
		spinner=(Spinner)findViewById(R.id.invoicespiner);
		weighteditect=(EditText)findViewById(R.id.weighteditect);
		locationedittext=(EditText)findViewById(R.id.locationedittext);
		departvoucheredittext=(EditText)findViewById(R.id.departvoucheredittext);
		truckScannednumber=(EditText)findViewById(R.id.truckScannednumber);
		truckNextStation=(EditText)findViewById(R.id.truckNextStation);
		truckNextStationcontent=(EditText)findViewById(R.id.truckNextStationcontent);
		trucknumbercontent=(EditText)findViewById(R.id.trucknumbercontent);
		truckupdate=(Button)findViewById(R.id.truckupdate);
		truckdelete=(Button)findViewById(R.id.truckdelete);
		invoicebackbutton=(Button)findViewById(R.id.invoicebackbutton);
	}
	

}
