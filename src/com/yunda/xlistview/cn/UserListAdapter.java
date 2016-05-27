package com.yunda.xlistview.cn;
import java.util.List;
import com.example.yunda_app_2016_3_18.R;
import com.yunda.untils.CompanyList;
import com.yunda.untils.UserList;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
public class UserListAdapter extends BaseAdapter{
	private List<UserList> mList;
	private Context mContex;
	private LayoutInflater inflater;
	public UserListAdapter(Context mContext,List<UserList> mList){
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
			convertView=inflater.inflate(R.layout.userlist, null, false);
			mHolder=new Holder(convertView);
			convertView.setTag(mHolder);
		}else {
			mHolder=(Holder) convertView.getTag();
		}
		mHolder.mUserCode.setText(mList.get(position).getUserCode());
		mHolder.mUserName.setText(mList.get(position).getUserName());
		return convertView;
	}
	class Holder{
		TextView mUserCode,mUserName;
		public Holder(View view){
			mUserCode=(TextView) view.findViewById(R.id.usercode);
			mUserName=(TextView) view.findViewById(R.id.username);
		}
	}
}
