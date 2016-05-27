package com.yunda.xlistview.cn;
import java.util.List;
import com.example.yunda_app_2016_3_18.R;
import com.yunda.untils.CompanyList;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
public class CompanyListAdapter extends BaseAdapter{
	private List<CompanyList> mList;
	private Context mContex;
	private LayoutInflater inflater;
	public CompanyListAdapter(Context mContext,List<CompanyList> mList){
		this.mContex=mContext;
		this.mList=mList;
		inflater=LayoutInflater.from(mContex);
		//Log.i("mList", mList.size()+"");
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
			convertView=inflater.inflate(R.layout.companyadapter, null, false);
			mHolder=new Holder(convertView);
			convertView.setTag(mHolder);
		}else {
			mHolder=(Holder) convertView.getTag();
		}
		mHolder.mCompanNumber.setText(mList.get(position).getComPanyCode());
		mHolder.mCompanyName.setText(mList.get(position).getComPanyName());
		return convertView;
	}
	class Holder{
		TextView mCompanNumber,mCompanyName;
		public Holder(View view){
			mCompanNumber=(TextView) view.findViewById(R.id.numbertextvew);
			mCompanyName=(TextView) view.findViewById(R.id.nametextvew);
		}
	}
}
