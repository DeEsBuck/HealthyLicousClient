package com.eis.healthylicous;

import java.io.File;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.pubsub.PayloadItem;
import org.jivesoftware.smackx.pubsub.SimplePayload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.eis.healthylicous.communication.DataHandler;

public class Profil extends Activity {

		private static final String TAG = Profil.class.getSimpleName();
		private final String DATEINAME = "test.txt";
		private final String VERZEICHNIS = "testdir";
		private File verzeichnis;
		private File datei;
		public EditText name; 
		public EditText alter;
		public EditText gewicht;
		public EditText groesse;
		public RadioGroup geschlecht;
		String gender = "";
		String ausgabe = "";
		DataHandler pub;
		public static final String TOPIC = "Kalories";
		Button bSave;

	
		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.profil);
			
			// das Eingabefeld
			name = (EditText) findViewById(R.id.etName);
			alter = (EditText) findViewById(R.id.etAlter);
			gewicht = (EditText) findViewById(R.id.etGewicht);
			groesse = (EditText) findViewById(R.id.etGroesse);
			
			// Profildaten
			final Button bSave = (Button) findViewById(R.id.bSave);
			bSave.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {	
					 //get selected radio button from radioGroup
					   RadioGroup radioGroup = (RadioGroup) findViewById(R.id.geschlecht);
					   int selectedId = radioGroup.getCheckedRadioButtonId();
					 //find the radio button by returned id
					   RadioButton osButton = (RadioButton) findViewById(selectedId);
					   gender = osButton.getText().toString();
					   
					ausgabe += "<profil xmlns=\"http://www.example.org/profil\" ";	
					ausgabe += "user='" + name.getText() + "' >";
					ausgabe += "<alter>" + alter.getText() + "</alter> \n";
					ausgabe += "<gewicht metric='kg'>" + gewicht.getText() + "</gewicht> \n";
					ausgabe += "<groesse metric='cm'>" + groesse.getText() + "</groesse> \n";
					ausgabe += "<geschlecht>" + osButton.getText() + "</geschlecht> \n";
					ausgabe += "</profil>";
					Log.d("Profil", ausgabe);

					SimplePayload payload = new SimplePayload("profil",	"http://www.example.org/profil", "dies ist ein Test");
					PayloadItem<SimplePayload> payloaditem = new PayloadItem<SimplePayload>(null, payload);
					try {
						MainActivity.connection.publishPayload( "Profile" , payloaditem);
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
	