package com.yunda.xlistview.cn;
import java.util.List;
import com.example.yunda_app_2016_3_18.R;
import com.yunda.untils.Abnorma;
import com.yunda.untils.CompanyList;
import com.yunda.untils.ListData;
import com.yunda.untils.Problem;
import com.yunda.untils.ReturnPart;
import com.yunda.untils.UserList;

import android.R.integer;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
public class ReturnPartAdapter extends BaseAdapter{
	private List<ReturnPart> mList;
	private Context mContex;
	private LayoutInflater inflater;
	private int index=-1;
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ReturnPartAdapter(Context mContext,List<ReturnPart> mList){
		this.mContex=mContext;
		this.mList=mList;
		inflater=LayoutInflater.from(mContex);
		Log.i("mList", mList.size()+"");
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

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder mHolder;
		if (convertView==null) {
			convertView=inflater.inflate(R.layout.simpleadapter, null, false);
			mHolder=new Holder(convertView);
			convertView.setTag(mHolder);
		}else {
			mHolder=(Holder) convertView.getTag();
		}
		mHolder.BigNumber.setText(mList.get(position).getNumber());
		mHolder.mNumber.setText(mList.get(position).getTime());
		//convertView.setBackgroundColor(Color.WHITE);
		if (position==index) {
			convertView.setBackgroundColor(Color.parseColor("#87CEFA"));
		}else {
			convertView.setBackground(mContex.getResources().getDrawable(R.drawable.bg1));
		}
		return convertView;
	}
	class Holder{
		TextView BigNumber,mNumber;
		public Holder(View view){
			BigNumber=(TextView) view.findViewById(R.id.bg_number);
			mNumber=(TextView) view.findViewById(R.id.yundannumber);
		}
	}
}
