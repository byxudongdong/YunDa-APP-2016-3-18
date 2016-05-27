package com.yunda.bluetoothpairing.cn;
import java.util.List;

import com.example.yunda_app_2016_3_18.R;
import com.yunda.untils.BluetoothDevices;

import android.R.color;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyPrintAdapter extends BaseAdapter{
	private List<BluetoothDevice> mList;
	private Context mContex;
	private LayoutInflater inflater;
	private int index=-1;
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public MyPrintAdapter(Context mContext,List<BluetoothDevice> mList){
		this.mContex=mContext;
		this.mList=mList;
		inflater=LayoutInflater.from(mContex);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder mHolder;
		if (convertView==null) {
			convertView=inflater.inflate(R.layout.mydeviceadapter, null, false);
			mHolder=new Holder(convertView);
			convertView.setTag(mHolder);
		}else {
			mHolder=(Holder) convertView.getTag();
		}
		mHolder.mNumberNmae.setText(mList.get(position).getName());
		mHolder.mNumberAdress.setText(mList.get(position).getAddress());
		convertView.setBackgroundColor(Color.WHITE);
		if (position==index) {
			convertView.setBackgroundColor(Color.parseColor("#87CEFA"));
		}else {
			convertView.setBackgroundColor(Color.WHITE);
		}
//		if(mList.get(position).isFlag()){
//			mHolder.mNumberNmae.setTextColor(Color.RED);
//			mHolder.mNumberAdress.setTextColor(Color.RED);
//		}else{
//			mHolder.mNumberNmae.setTextColor(Color.BLACK);
//			mHolder.mNumberAdress.setTextColor(Color.BLACK);
//		}
		return convertView;
	}
	class Holder{
		TextView mNumberNmae,mNumberAdress;
		public Holder(View view){
			mNumberNmae=(TextView) view.findViewById(R.id.deviceName);
			mNumberAdress=(TextView) view.findViewById(R.id.deviceAdress);
		}
	}
}
