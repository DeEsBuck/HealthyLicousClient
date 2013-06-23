package com.eis.healthylicous;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


//Klassennamem und als passende Menupunkt
public class Einstellungen extends ListActivity {
	String classes[] = { "Profil" , "Strecken" , "AGBs" , "Hilfe" }; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.einstellungen);
	   
		setListAdapter(new ArrayAdapter<String>(Einstellungen.this,
				android.R.layout.simple_list_item_1, classes));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		// cheese listet die klassen auf

		super.onListItemClick(l, v, position, id);
		String auswahl = classes[position];
		try {
			Class ourClass = Class.forName("com.eis.healthylicous." + auswahl);
			Intent ourIntent = new Intent(Einstellungen.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}