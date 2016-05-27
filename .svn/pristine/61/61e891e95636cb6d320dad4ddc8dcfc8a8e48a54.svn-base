package com.yunda.activity.cn;

import com.example.yunda_app_2016_3_18.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
public class DonwloaddataActivity extends Activity implements OnClickListener{
	private Button mComePanyButton;//公司列表
	private Button mStafflistButton;//员工列表
	private Button mProblemPiecesButton;//问题件
	private Button mAbnormalyButton;//异常签收类型
	private Button mCompanyarrearsButton;//欠费公司
	private String userID;
	private String passWord;
	private String conpanyCode;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.ziliaodownload);
	//初始化
	findViewById();
}
private void findViewById() {
	mComePanyButton=(Button) findViewById(R.id.companylist);
	mStafflistButton=(Button) findViewById(R.id.stafflist);
	mProblemPiecesButton=(Button) findViewById(R.id.mProblemPiecesButton);
	mAbnormalyButton=(Button) findViewById(R.id.mAbnormalyButton);
	mCompanyarrearsButton=(Button) findViewById(R.id.mCompanyarrearsButton);
	mComePanyButton.setOnClickListener(this);
	mStafflistButton.setOnClickListener(this);
	mProblemPiecesButton.setOnClickListener(this);
	mAbnormalyButton.setOnClickListener(this);
	mCompanyarrearsButton.setOnClickListener(this);
	userID=getIntent().getStringExtra("userID");
	passWord=getIntent().getStringExtra("passWord");
	conpanyCode=getIntent().getStringExtra("conpanyCode");
}
@Override
public void onClick(View v) {
switch (v.getId()) {
case R.id.companylist:
	Intent pIntent=new Intent(DonwloaddataActivity.this,CompangListActivity.class);
	pIntent.putExtra("userID", userID);
	pIntent.putExtra("passWord", passWord);
	pIntent.putExtra("conpanyCode", conpanyCode);
	startActivity(pIntent);
	break;

case R.id.stafflist:
	Intent xIntent=new Intent(DonwloaddataActivity.this,StaffListActivity.class);
	xIntent.putExtra("userID", userID);
	xIntent.putExtra("passWord", passWord);
	xIntent.putExtra("conpanyCode", conpanyCode);
	startActivity(xIntent);
	break;
case R.id.mProblemPiecesButton:
	Intent ProIntent=new Intent(DonwloaddataActivity.this,ProblemListActivity.class);
	ProIntent.putExtra("userID", userID);
	ProIntent.putExtra("passWord", passWord);
	ProIntent.putExtra("conpanyCode", conpanyCode);
	startActivity(ProIntent);
	break;
case R.id.mAbnormalyButton:
	Intent AbnIntent=new Intent(DonwloaddataActivity.this,AbnormalActivity.class);
	AbnIntent.putExtra("userID", userID);
	AbnIntent.putExtra("passWord", passWord);
	AbnIntent.putExtra("conpanyCode", conpanyCode);
	startActivity(AbnIntent);
	break;

case R.id.mCompanyarrearsButton:
	Intent arrIntent=new Intent(DonwloaddataActivity.this,ArrearsActivity.class);
	arrIntent.putExtra("userID", userID);
	arrIntent.putExtra("passWord", passWord);
	arrIntent.putExtra("conpanyCode", conpanyCode);
	startActivity(arrIntent);
	break;
}
}
}
