package com.eis.healthylicous.communication;

import org.jivesoftware.smack.XMPPException;

import android.os.AsyncTask;
import android.util.Log;

import com.eis.healthylicous.MainActivity;

	public class ConnectionTask extends AsyncTask<String, Void, String> {
	//die connection muss im hintergrund laufen
		
		
		@Override
		protected String doInBackground(String... params) {
			try {				
				MainActivity.connection.connect();
				MainActivity.connection.login();
				Log.d("execute" , "Connection");
				MainActivity.connection.subscribe("Vorschlag", MainActivity.connection.getUser());
				MainActivity.connection.listener("Vorschlag");
			} catch (XMPPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			if(MainActivity.connection.isconnected()) {
////				MainActivity.connection.disconnect();
//			}else{
//				MainActivity.connection.login();
//			}
			return null;
		}
	}
