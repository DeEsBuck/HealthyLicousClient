package com.eis.healthylicous.communication;

import org.jivesoftware.smack.XMPPException;

import android.os.AsyncTask;
import android.util.Log;

import com.eis.healthylicous.MainActivity;

	public class ConnectionTask extends AsyncTask<String, Void, String> {
				// für eine Server Verbindung, wird die Verbindung im Hintergrund Aufgebaut
		@Override
		protected String doInBackground(String... params) {
			try {				
				MainActivity.connection.connect();
				MainActivity.connection.login();
				Log.d("execute" , "Connection");
				MainActivity.connection.subscribe("Vorschlag", MainActivity.connection.getUser());
				Log.d("ASYNCTASK" , "subscrib");
				MainActivity.connection.listener("Vorschlag");
			} catch (XMPPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
		}
	}
