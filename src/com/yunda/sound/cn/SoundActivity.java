package com.yunda.sound.cn;
import com.example.yunda_app_2016_3_18.R;
import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
public class SoundActivity extends Activity {
	public SeekBar sound_seekBar;
	public AudioManager mAudioManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		this.setContentView(R.layout.sound_view);
		mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
		int current = mAudioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
		sound_seekBar = (SeekBar) findViewById(R.id.sound_seekBar);
		sound_seekBar.setMax(max);
		sound_seekBar.setProgress(current);
		seekBarListener();
	}
	public void seekBarListener(){
		sound_seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mAudioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, progress, AudioManager.FLAG_PLAY_SOUND );
			}
		});
	}
}
