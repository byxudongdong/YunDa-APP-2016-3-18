package com.yunda.untils;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.yunda.activity.cn.AbnormalActivity;
import com.yunda.activity.cn.AdminSettingActivity;
import com.yunda.activity.cn.ArrearsActivity;
import com.yunda.activity.cn.CompangListActivity;
import com.yunda.activity.cn.LoadingScanActivity;
import com.yunda.activity.cn.MainmenueActivity;
import com.yunda.activity.cn.PacketScanningActivity;
import com.yunda.activity.cn.ProblemListActivity;
import com.yunda.activity.cn.ReturnPartActivity;
import com.yunda.activity.cn.ReturnPartDatauploadActivity;
import com.yunda.activity.cn.ScanningActivity;
import com.yunda.activity.cn.SearchrecrodActivity;
import com.yunda.activity.cn.StaffListActivity;
import com.yundadialog.BounceTopEnter;
import com.yundadialog.DialogMenuItem;
import com.yundadialog.OnOperItemClickL;
import com.yundadialog.SlideBottomExit;
import com.yundadialog.T;
public class NormalListDialog {
	private String userID;//用户id
	private String profession;//工种名称
	private String professionID;//工种id
	private String passWord;//用户密码
	private String conpanyCode;//当前站点
	private String name;//登录人
	private String [] packetItems={"集包扫描[1]","集包扫描(效验)[2]","集包扫描(自动)[3]"};
	ArrayList<DialogMenuItem> testItems;
public NormalListDialog(){
	
}
public NormalListDialog(String userID, String profession,
			String professionID, String passWord, String conpanyCode,
			String name) {
		super();
		this.userID = userID;
		this.profession = profession;
		this.professionID = professionID;
		this.passWord = passWord;
		this.conpanyCode = conpanyCode;
		this.name = name;
		testItems=new ArrayList<>();
		testItems.add(new DialogMenuItem("集包扫描[1]"));	
		testItems.add(new DialogMenuItem("集包扫描(效验)[2]"));	
		testItems.add(new DialogMenuItem("集包扫描(自动)[3]"));	
	}
public  void Dialogs(String[] stringItems, final ArrayList<DialogMenuItem> testItems, final Context context ) {
	    final com.yundadialog.NormalListDialog dialog = new com.yundadialog.NormalListDialog(context, stringItems);
	    dialog.title("请选择")//
	            .showAnim(new BounceTopEnter())//
	            .dismissAnim(new SlideBottomExit())//
	            .show();
	    dialog.setOnOperItemClickL(new OnOperItemClickL() {
			@Override
			public void onOperItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				switch (testItems.get(position).operName) {
				case "集包扫描[1]":
					Intent mIntent=new Intent(context,SearchrecrodActivity.class);
					context.startActivity(mIntent);	
					break;
				case "集包扫描(效验)[2]":
					Toast.makeText(context, "此功能暂未实现", Toast.LENGTH_SHORT).show();
					break;
				case "集包扫描(自动)[3]":
					Toast.makeText(context, "此功能暂未实现", Toast.LENGTH_SHORT).show();
					break;
				}
				 T.showShort(context, testItems.get(position).operName);
			        dialog.dismiss();
				
			}
	    });
	    
	}
// 
public void ActionSheetDialog(final String[] stringItems,final Context context,String operationname) {
    final com.yundadialog.ActionSheetDialog dialog = new com.yundadialog.ActionSheetDialog(context, stringItems, null);
    dialog.title("下面是"+operationname+"全部业务请选择")//
            //.titleTextSize_SP(14.5f)//
            .titleTextSize_SP(20.0f)
            .titleTextColor(Color.parseColor("#44A2FF"))
            .show();
    	
    dialog.setOnOperItemClickL(new OnOperItemClickL() {
        @Override
        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {	
    		switch (stringItems[position].trim()) {
    		case"回流件扫描[1]":
    			Intent sIntent=new Intent(context,ReturnPartActivity.class);
				sIntent.putExtra("userID", userID);
				sIntent.putExtra("profession", profession);
				sIntent.putExtra("professionID", professionID);
				sIntent.putExtra("passWord", passWord);
				sIntent.putExtra("conpanyCode", conpanyCode);
				sIntent.putExtra("name", name);
				context.startActivity(sIntent);	
    			break;
    		case "业绩统计[1]":
				Toast.makeText(context, "此功能暂未实现", Toast.LENGTH_SHORT).show();
				break;
    		case "回流件扫描[2]":
    			Intent mIntent=new Intent(context,ReturnPartDatauploadActivity.class);
				mIntent.putExtra("userID", userID);
				mIntent.putExtra("profession", profession);
				mIntent.putExtra("professionID", professionID);
				mIntent.putExtra("passWord", passWord);
				mIntent.putExtra("conpanyCode", conpanyCode);
				context.startActivity(mIntent);
				break;
    		case "集包扫描[1]":
				Intent jIntent=new Intent(context,ScanningActivity.class);
				jIntent.putExtra("userID", userID);
				jIntent.putExtra("profession", profession);
				jIntent.putExtra("professionID", professionID);
				jIntent.putExtra("passWord", passWord);
				jIntent.putExtra("conpanyCode", conpanyCode);
				context.startActivity(jIntent);	
				break;
			case "集包扫描(效验)[2]":
				Toast.makeText(context, "此功能暂未实现", Toast.LENGTH_SHORT).show();
				break;
			case "集包扫描(自动)[3]":
				Toast.makeText(context, "此功能暂未实现", Toast.LENGTH_SHORT).show();
				break;
			case "装车扫描[1]":
				Intent loadIntent=new Intent(context,LoadingScanActivity.class);
//				loadIntent.putExtra("userID", userID);
//				loadIntent.putExtra("profession", profession);
//				loadIntent.putExtra("professionID", professionID);
//				loadIntent.putExtra("passWord", passWord);
//				loadIntent.putExtra("conpanyCode", conpanyCode);
				context.startActivity(loadIntent);
				break;
			case "装车结束[2]":
				Toast.makeText(context, "此功能暂未实现", Toast.LENGTH_SHORT).show();
				break;
			}
            T.showShort(context, stringItems[position]);
            dialog.dismiss();
        }
    });
}
public void packetSheetDialog(final String[] stringItems,final Context context) {
    final com.yundadialog.ActionSheetDialog dialog = new com.yundadialog.ActionSheetDialog(context, stringItems, null);
    dialog.title("下面是回流件全部业务，请选择")//
            //.titleTextSize_SP(14.5f)//
            .titleTextSize_SP(20.0f)
            .titleTextColor(Color.parseColor("#44A2FF"))
            .show();
    	
    dialog.setOnOperItemClickL(new OnOperItemClickL() {
        @Override
        public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {	
    		switch (stringItems[position].trim()) {
    		case"业绩统计[1]":
    			
    			break;
    		case "集包扫描[2]":
    			//testItems
    			Dialogs(packetItems,testItems,context);
				break;
			
			}
            T.showShort(context, stringItems[position]);
            dialog.dismiss();
        }
    });
}
}
