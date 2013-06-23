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
import com.eis.healthylicous.communication.ItemEventCoordinator;


public class Ernaehrungsplan extends Activity{
	
	Button btn_akt;
	TextView txt_vorschlag, txt_testtest;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ernaehrungstagesplan);
        Log.d("Tagesplan", "gestartet");         
        //Textfeld Ernährungsvorschläge
        txt_vorschlag = (TextView) findViewById(R.id.txt_vorschlag);
		
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
}
    
