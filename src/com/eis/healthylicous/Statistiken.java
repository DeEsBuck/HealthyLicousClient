package com.eis.healthylicous;

import org.jivesoftware.smack.XMPPException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Statistiken extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistik);
}
    public void onNavButtonClick(final View view) throws XMPPException {
    	switch (view.getId()) {
    	case R.id.nbt_einstellungen:
    		startActivity(new Intent(this, Einstellungen.class));
    		break;
    		
    	}
    }	  
}
