package com.eis.healthylicous;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.ItemPublishEvent;
import org.jivesoftware.smackx.pubsub.listener.ItemEventListener;

import com.eis.healthylicous.communication.ConnectionTask;
import com.eis.healthylicous.communication.DataHandler;
import com.eis.healthylicous.communication.InitPubSub;
import com.eis.healthylicous.communication.ItemEventCoordinator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Ernaehrungsplan extends Activity{
	
	Button btn_kalorien, btn_akt;
	TextView txt_vorschlag, txt_testtest;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ernaehrungstagesplan);
        Log.d("Tagesplan", "gestartet");
         
        InitPubSub.init();
        Log.d("test", "InitPubSub Success!");
        
        btn_akt = (Button) findViewById(R.id. btn_akt);
        btn_kalorien = (Button) findViewById(R.id.btn_kalorien);
        txt_vorschlag = (TextView) findViewById(R.id.txt_vorschlag);

        
        btn_akt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					if(MainActivity.connection.isconnected()){ 
//					MainActivity.connection.connect();
					Log.d("IFrefresh", "angewählt");
//					MainActivity.connection.subscribe("Vorschlag", MainActivity.connection.getUser());
//					Log.d("subscibe","success");
//					txt_vorschlag.setText(MainActivity.connection.getTopicID());
					txt_vorschlag.setText(MainActivity.connection.getItem());
					}
//					MainActivity.connection.listener("Vorschlag");
//					ItemEventCoordinator eventListener = new ItemEventCoordinator();
//					eventListener.getTag();
//					eventListener.getGewicht();
//					eventListener.getName();
					
//					txt_vorschlag.setText(MainActivity.connection.getSubscriptions());
//					Log.d("EVENTLISTENER", "connect");
//					}
//					else if(!MainActivity.connection.isconnected()) {
//						new ConnectionTask().execute();
//						Log.d("ELSErefresh", "angewählt");
//						MainActivity.connection.subscribe("Vorschlag", MainActivity.connection.getUser());
//						MainActivity.connection.listener("Vorschlag");
//						ItemEventCoordinator eventListener = new ItemEventCoordinator();
//						eventListener.getTag();
//						eventListener.getGewicht();
//						eventListener.getName();
//						txt_vorschlag.setText(eventListener.getTag());
//						Log.d("EVENTLISTENER", "connect");
//					}

				} catch (XMPPException e) {
					e.printStackTrace();
				}
//				MainActivity.connection.disconnect();
//				Log.d("Connection" , "disconnected");
			}
		});	
      
	}		  
	
		      	
    public void onNavButtonClick(final View view) throws XMPPException {
    	switch (view.getId()) {
    	case R.id.btn_kalorien:
    		startActivity(new Intent(this, Kalorien.class));
    		break;
    	case R.id.nbt_einstellungen:
    		startActivity(new Intent(this, Einstellungen.class));
    		break;
    	case R.id.nbt_Tag:
    		startActivity(new Intent(this, Ernaehrungsplan.class));
    		break;
    	case R.id.nbt_Zweitage:
    		startActivity(new Intent(this, Zweitagesplan.class));
    		break;
    	case R.id.nbt_Woche:
    		startActivity(new Intent(this, Wochenplan.class));
    		break;
    		
    	}
    }
//    	case R.id.btn_syn:
//    		try{
//    		Log.d("refresh", "angewählt");
//			MainActivity.connection.subscribe("Vorschlag", MainActivity.connection.getUser());
//			MainActivity.connection.listener("Vorschlag");
//			ItemEventCoordinator eventListener = new ItemEventCoordinator();
//			eventListener.getTag();
//			eventListener.getGewicht();
//			eventListener.getName();
//			txt_vorschlag.setText(eventListener.getTag());
//			Log.d("EVENTLISTENER", "connect");
//    		}finally{
//			MainActivity.connection.disconnect();
//			Log.d("Connection" , "disconnected");
//			}
//			break;
//		}
//    }   
}
    
	
	//damm service
//	public void run() {
//			// Service starten
//			startService(new Intent(this, ConnectionTest.class));
//	
	
	
	
//
//public class Ernaehrungsplan extends Activity{
//
//	/* (non-Javadoc)
//	 * @see android.app.Activity#onCreate(android.os.Bundle)
//	 */
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.daten_listen_adapter);
//	} 
//	
//	
//}
//	
//	
//	
////	Button bt_einstellungen;
////
////		public class Manno extends Activity{
////			@Override
////			public void onCreate(Bundle savedInstanceState) {
////			super.onCreate(savedInstanceState);
////			setContentView(R.layout.plan);
////		
////		
////		bt_einstellungen = (Button) findViewById(R.id.bt_einstellungen);
////	
////			bt_einstellungen.setOnClickListener(new OnClickListener() {
////				@Override
////				public void onClick(View v) {
////					switch (v.getId()) { 
////					case R.id.bt_einstellungen:
////						Intent k = new Intent(Ernaehrungsplan.this, Einstellungen.class);
////						startActivity(k);
////						//finish();
////						break;
////						}
////					}
////			});
////			}
////		}
////		public class Grummel extends ListActivity {
//
//	
//	//	     Hier funktional service fehlt :,(
//		String lista[] = { "Luft" , "Drachenei" , "Heiltrank" , "Manabombom" , "blutiges Herz" , "Knochenmehl" }; 
//		
//		@Override
//		protected void onCreate(Bundle savedInstanceState) {
//			
//			super.onCreate(savedInstanceState);
//			setContentView(R.layout.plan);
//		   
//			setListAdapter(new ArrayAdapter<String>(Ernaehrungsplan.this,
//			android.R.layout.simple_list_item_1, lista));
//		}
//					
//	}
