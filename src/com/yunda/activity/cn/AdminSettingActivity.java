package com.yunda.activity.cn;
import java.util.ArrayList;
import com.example.yunda_app_2016_3_18.R;
import com.yundadialog.BaseAnimatorSet;
import com.yundadialog.BounceTopEnter;
import com.yundadialog.DialogMenuItem;
import com.yundadialog.NormalDialog;
import com.yundadialog.OnBtnClickL;
import com.yundadialog.OnOperItemClickL;
import com.yundadialog.SlideBottomExit;
import com.yundadialog.T;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
public class AdminSettingActivity extends Activity implements OnClickListener {
private Button serverButton;//服务器设置
private Button AutouploadButton;//自动上传设置
private Button Data_clearbutton;//手表数据清空
private Button Record_query;//记录查询
private Button System_settings;//系统设置
private Button Data_download;//资料下载
private Button backButton;
private BaseAnimatorSet bas_out;
private SharedPreferences sp;
private ArrayList<com.yundadialog.DialogMenuItem> testItems = new ArrayList<com.yundadialog.DialogMenuItem>();
private String[] stringItems = {"特殊件扫描查询[1]", "卸车扫描查询[2]", "到件扫描查询[3]", "发件扫描查询[4]", "集包扫描查询[5]", "装车扫描查询[6]","回流件扫描查询[7]","车辆调度查询[8]"};
private BaseAnimatorSet bas_in;
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
	//取消状态栏   
	  this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
	    WindowManager.LayoutParams.FLAG_FULLSCREEN); 
	  	setContentView(R.layout.adminsetting);
	  	serverButton=(Button) findViewById(R.id.serverbutton);
	  	AutouploadButton=(Button) findViewById(R.id.AutouploadButton);
	  	Data_clearbutton=(Button) findViewById(R.id.Data_clearbutton);
	  	Record_query=(Button) findViewById(R.id.Record_query);
	  	System_settings=(Button) findViewById(R.id.System_settings);
	  	Data_download=(Button) findViewById(R.id.Data_download);
	  	backButton=(Button) findViewById(R.id.backbutton);
	  	serverButton.setOnClickListener(this);
	  	AutouploadButton.setOnClickListener(this);
	  	Data_clearbutton.setOnClickListener(this);
	  	Record_query.setOnClickListener(this);
	  	System_settings.setOnClickListener(this);
	  	Data_download.setOnClickListener(this);
	  	backButton.setOnClickListener(this);
        bas_in = new BounceTopEnter();
        bas_out = new SlideBottomExit();
        testItems.add(new DialogMenuItem("特殊件扫描查询[1]"));
        testItems.add(new DialogMenuItem("卸车扫描查询[2]"));
        testItems.add(new DialogMenuItem("到件扫描查询[3]"));
        testItems.add(new DialogMenuItem("发件扫描查询[4]"));
        testItems.add(new DialogMenuItem("集包扫描查询[5]"));
        testItems.add(new DialogMenuItem("装车扫描查询[6]"));
        testItems.add(new DialogMenuItem("回流件扫描查询[7]"));
        testItems.add(new DialogMenuItem("车辆调度查询[8]"));
        sp = this.getSharedPreferences("passwordFile", MODE_PRIVATE);

}
@Override
public void onClick(View v) {
	switch (v.getId()) {
	case R.id.serverbutton:
		Intent mIntent=new Intent(AdminSettingActivity.this, ServerSettingActivity.class);
		startActivity(mIntent);
		break;
	case R.id.AutouploadButton:
		Intent nIntent=new Intent(AdminSettingActivity.this, AutouploadActivity.class);
		startActivity(nIntent);
		break;
	case R.id.Data_clearbutton:
		NormalDialogStyleOne();
		break;
	case R.id.Record_query:
		NormalListDialog();
		break;
	case R.id.System_settings:
		Intent sIntent=new Intent(AdminSettingActivity.this,SettingActivity.class);
		startActivity(sIntent);	
		break;
	case R.id.Data_download:
		ActionSheetDialog();
		break;
	case R.id.backbutton:
	finish();
	break;
	}
	
}
private void NormalDialogStyleOne() {
    final NormalDialog dialog = new NormalDialog(AdminSettingActivity.this);
    dialog.content(" 该操作将清空手表所有数"+"\r\n"+"  据(包括业务数据、基本设置"+"\r\n"+"等)"+" 是否继续？")//
            .showAnim(bas_in)//
            .dismissAnim(bas_out)//
            .show();

    dialog.setOnBtnClickL(
            new OnBtnClickL() {
                @Override
                public void onBtnClick() {
                    dialog.dismiss();
                }
            },
            new OnBtnClickL() {
                @Override
                public void onBtnClick() {
                    T.showShort(AdminSettingActivity.this, "数据已清空");
                    dialog.dismiss();
                }
            });
}
public void setBasIn(BaseAnimatorSet bas_in) {
    this.bas_in = bas_in;
}

public void setBasOut(BaseAnimatorSet bas_out) {
    this.bas_out = bas_out;
}
private void NormalListDialog() {
    final com.yundadialog.NormalListDialog dialog = new com.yundadialog.NormalListDialog(AdminSettingActivity.this, stringItems);
    dialog.title("请选择")//
            .showAnim(bas_in)//
            .dismissAnim(bas_out)//
            .show();
    dialog.setOnOperItemClickL(new OnOperItemClickL() {
		@Override
		public void onOperItemClick(AdapterView<?> parent, View view,
				int position, long id) {
			switch (testItems.get(position).operName) {
			case "特殊件扫描查询[1]":
				
				
				break;
			case "卸车扫描查询[2]":
				
				break;
			case "到件扫描查询[3]":
	
				break;
			case "发件扫描查询[4]":
	
				break;
			case "集包扫描查询[5]":
	
				break;
			case "装车扫描查询[6]":
	
				break;
			case "回流件扫描查询[7]":
	
				break;
			case "车辆调度查询[8]":
	
				break;
			}
			
			 T.showShort(getApplicationContext(), testItems.get(position).operName);
		        dialog.dismiss();
			
		}
    });
    
}
private void ActionSheetDialog() {
    final String[] stringItems = {"公司列表[1]", "员工列表[2]","问题件/留仓原件[3]", "异常签收类型[4]", "欠费公司列表[5]"};
    final com.yundadialog.ActionSheetDialog dialog = new com.yundadialog.ActionSheetDialog(AdminSettingActivity.this, stringItems, null);
    dialog.title("选择要下载的列表名称\r\n(下载资料:时间久请耐心等待)")//
            .titleTextSize_SP(14.5f)//
            .show();

    dialog.setOnOperItemClickL(new OnOperItemClickL() {
        @Override
        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {	
    		switch (stringItems[position]) {
			case "公司列表[1]":
				Intent pIntent=new Intent(AdminSettingActivity.this,CompangListActivity.class);
				pIntent.putExtra("userID", sp.getString("cardNumStr", ""));
				pIntent.putExtra("passWord", sp.getString("passwordStr", ""));
				pIntent.putExtra("conpanyCode",sp.getString("conpanyCode", ""));
				startActivity(pIntent);
				break;

			case "员工列表[2]":
				Intent xIntent=new Intent(AdminSettingActivity.this,StaffListActivity.class);
				xIntent.putExtra("userID", sp.getString("cardNumStr", ""));
				xIntent.putExtra("passWord", sp.getString("passwordStr", ""));
				xIntent.putExtra("conpanyCode",sp.getString("conpanyCode", ""));
				startActivity(xIntent);
				break;
			case "问题件/留仓原件[3]":
				Intent ProIntent=new Intent(AdminSettingActivity.this,ProblemListActivity.class);
				ProIntent.putExtra("userID", sp.getString("cardNumStr", ""));
				ProIntent.putExtra("passWord", sp.getString("passwordStr", ""));
				ProIntent.putExtra("conpanyCode",sp.getString("conpanyCode", ""));
				startActivity(ProIntent);
				break;	
			case "异常签收类型[4]":
				Intent AbnIntent=new Intent(AdminSettingActivity.this,AbnormalActivity.class);
				AbnIntent.putExtra("userID", sp.getString("cardNumStr", ""));
				AbnIntent.putExtra("passWord", sp.getString("passwordStr", ""));
				AbnIntent.putExtra("conpanyCode",sp.getString("conpanyCode", ""));
				startActivity(AbnIntent);
				break;
			case"欠费公司列表[5]":
				Intent arrIntent=new Intent(AdminSettingActivity.this,ArrearsActivity.class);
				arrIntent.putExtra("userID", sp.getString("cardNumStr", ""));
				arrIntent.putExtra("passWord", sp.getString("passwordStr", ""));
				arrIntent.putExtra("conpanyCode",sp.getString("conpanyCode", ""));
				startActivity(arrIntent);
				break;
			}
            T.showShort(AdminSettingActivity.this, stringItems[position]);
            dialog.dismiss();
        }
    });
}
}
