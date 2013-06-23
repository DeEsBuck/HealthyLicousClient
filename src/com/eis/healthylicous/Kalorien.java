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

import com.eis.healthylicous.communication.ConnectionHandler;
import com.eis.healthylicous.communication.ConnectionTask;
import com.eis.healthylicous.communication.DataHandler;
import com.eis.healthylicous.communication.InitPubSub;

public class Kalorien extends Activity {

	public static final String TOPIC = "Kalories";

	Button btn_pubkal;
	TextView txt_items;
	EditText etKalorien;
//	Intent intent;
	boolean refresh = false;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.kalorien);
		Log.d("KALORIEN" , "ANSICHT");
		InitPubSub.init();

		btn_pubkal = (Button) findViewById(R.id.btn_pubkal);
		etKalorien = (EditText) findViewById(R.id.etKalorien);
		txt_items = (TextView) findViewById(R.id.txt_items);
		txt_items.setEnabled(false);
		txt_items.setText("");

		// Publish Kalories
		btn_pubkal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				btn_pubkal.setText("simuliert");
				try {
//					if(MainActivity.connection.isconnected()){ 	
						MainActivity.connection.publishPayload("Kalories", new DataHandler().setKalories(etKalorien.getText().toString(), MainActivity.connection.getUser()));
						Log.d("HEALTHYCONTROLLER", "Set Kalories succeeded");
						txt_items.setText(MainActivity.connection.getUser());
//						}else {
//							MainActivity.connection.connect();
//						}
				} catch (XMPPException e) {
					e.printStackTrace();
				}
//				MainActivity.connection.disconnect();
//				Log.d("Connection" , "disconnected");
			}
		});		
		
	}
}	