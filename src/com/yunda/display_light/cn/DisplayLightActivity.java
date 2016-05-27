package com.yunda.display_light.cn;
import com.example.yunda_app_2016_3_18.R;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
public class DisplayLightActivity extends Activity {
	public SeekBar light_seekBar;
	public AudioManager mAudioManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		this.setContentView(R.layout.display_light_view);
		
		light_seekBar = (SeekBar) findViewById(R.id.light_seekBar);
		
		int ScreenBrightness=(int)(android.provider.Settings.System.getInt(getContentResolver(),
	              android.provider.Settings.System.SCREEN_BRIGHTNESS,
	              255));
	     //文本、进度条显示
		light_seekBar.setMax(255);
		light_seekBar.setProgress(ScreenBrightness);

		seekBarListener();
		
	}
	
	
	public void seekBarListener(){
		light_seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				light_seekBar.setProgress(progress);
				setScreenBritness(progress);
			}
		});
	}
	


	//屏幕亮度
	     private void setScreenBritness(int brightness)
	     {
	          //不让屏幕全暗
	          if(brightness<=10){
	              brightness=10;
	          }
	          //设置当前activity的屏幕亮度
	          WindowManager.LayoutParams lp = this.getWindow().getAttributes();
	          //0到1,调整亮度暗到全亮
	          lp.screenBrightness = Float.valueOf(brightness/255f); 
	          this.getWindow().setAttributes(lp);
	     
	          //保存为系统亮度方法1
	          android.provider.Settings.System.putInt(getContentResolver(),
	                   android.provider.Settings.System.SCREEN_BRIGHTNESS,
	                   brightness);
	          
	     }
	
}
