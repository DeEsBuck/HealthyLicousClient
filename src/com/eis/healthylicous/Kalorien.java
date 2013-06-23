package com.eis.healthylicous;

import org.jivesoftware.smack.XMPPException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.eis.healthylicous.communication.DataHandler;
import com.eis.healthylicous.communication.InitPubSub;

public class Kalorien extends Activity {

	public static final String TOPIC = "Kalories";

	Button btn_pubkal;
	TextView txt_items;
	EditText etKalorien;
	boolean refresh = false;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.kalorien);
		Log.d("KALORIEN" , "ANSICHT");
		InitPubSub.init();

		btn_pubkal = (Button) findViewById(R.id.btn_pubkal);
		etKalorien = (EditText) findViewById(R.id.etKalorien);

		// Publish Kalories
		btn_pubkal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btn_pubkal.setText("gesendet");
				try {
						MainActivity.connection.publishPayload("Kalories", new DataHandler().setKalories(etKalorien.getText().toString(), MainActivity.connection.getUser()));
						Log.d("HEALTHYCONTROLLER", "Set Kalories succeeded");					    
				} catch (XMPPException e) {
					e.printStackTrace();
				}
			}
		});		
		
	}
    public void onNavButtonClick(final View view) throws XMPPException {
    	switch (view.getId()) {
    	case R.id.nbt_einstellungen:
    		startActivity(new Intent(this, Einstellungen.class));
    		break;
    	}
    }	
}	