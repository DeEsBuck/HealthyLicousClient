package com.eis.healthylicous;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Splash extends Activity{
	//Intro
protected void onCreate(Bundle CandyLand) {
		
		super.onCreate(CandyLand);
		setContentView(R.layout.splash);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					Log.d("SPLASH", "gestartet");
					sleep(2000);
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openMainActivity = new Intent("com.eis.healthylicous.MAINACTIVITY");
					startActivity(openMainActivity);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		Log.d("SPLASH", "abgeschlossen");
		super.onPause();
		finish();
	}

}
