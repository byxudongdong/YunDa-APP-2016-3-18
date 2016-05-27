package com.yunda.xlistview.cn;
import java.util.List;
import com.example.yunda_app_2016_3_18.R;
import com.yunda.untils.Abnorma;
import com.yunda.untils.ArrearsCompany;
import com.yunda.untils.CompanyList;
import com.yunda.untils.Problem;
import com.yunda.untils.UserList;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
public class ArrearsCompanyAdapter extends BaseAdapter{
	private List<ArrearsCompany> mList;
	private Context mContex;
	private LayoutInflater inflater;
	public ArrearsCompanyAdapter(Context mContext,List<ArrearsCompany> mList){
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder mHolder;
		if (convertView==null) {
			convertView=inflater.inflate(R.layout.arrearadapterxml, null, false);
			mHolder=new Holder(convertView);
			convertView.setTag(mHolder);
		}else {
			mHolder=(Holder) convertView.getTag();
		}
		mHolder.mNumberCode.setText(mList.get(position).getArrCompany());
		mHolder.mProblemName.setText(mList.get(position).getCode());
		return convertView;
	}
	class Holder{
		TextView mNumberCode,mProblemName;
		public Holder(View view){
			mNumberCode=(TextView) view.findViewById(R.id.arrearcompany);
			mProblemName=(TextView) view.findViewById(R.id.codearrear);
		}
	}
}
