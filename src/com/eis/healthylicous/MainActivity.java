package com.eis.healthylicous;

import org.jivesoftware.smack.ConnectionConfiguration;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.eis.healthylicous.communication.ConnectionHandler;
import com.eis.healthylicous.communication.ConnectionTask;
import com.eis.healthylicous.communication.InitPubSub;

	
	public class MainActivity extends ListActivity{
		//Liste der Klassen
		String classes[] = { "Ernaehrungsplan" ,  "Statistiken" , "Strecken" , "Einstellungen" };
		
		private static final String HOST = "192.168.178.89";
		private static final int PORT = 5222;

		static ConnectionConfiguration config = new ConnectionConfiguration(HOST, PORT);
		public static ConnectionHandler connection = new ConnectionHandler(config);
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {

			super.onCreate(savedInstanceState);
			setContentView(R.layout.menu);
			InitPubSub.init();
			
			new ConnectionTask().execute();
			Log.d("LALALALALA" , "BAJBKABKABKABHIUAGUI");
			
			setListAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, classes));
			Log.d("Hauptmenu", "aktiv");
		}
		
		/* (non-Javadoc)
		 * @see android.app.ListActivity#onDestroy()
		 */
		@Override
		protected void onDestroy() {
			new ConnectionTask().execute();
			super.onDestroy();
		}
		
		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			
			// auflisten =+ der klassennamen
			Log.d("Menupunkt", "angewählt");
			super.onListItemClick(l, v, position, id);
			String auflisten = classes[position];
			try{
			Class ourClass = Class.forName("com.eis.healthylicous." + auflisten);
			Intent ourIntent = new Intent(MainActivity.this, ourClass);
			startActivity(ourIntent);
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}
}
